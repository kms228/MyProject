package shs.admin.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.order.PrepProdVo;
import shs.admin.vo.order.OrderSearchVo;
import shs.admin.vo.paging.PagingVo;

public class OrderCancelDao {
	// 전체 글의 갯수 구하기
	public int getCount(OrderSearchVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 1;
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT NVL(COUNT(ORDER_NUM),0) CNT " + "FROM ORDERLIST O, ITEM I, BUYBOARD B, MEMBERS M "
					+ "WHERE O.PNUM=I.PNUM AND O.BUY_NUM=B.BUY_NUM AND B.MNUM=M.MNUM AND STATE='배송취소'";
			if (!vo.getItem_name().equals("")) {
				sql = sql + " AND ITEM_NAME =?";
			}
			if (!vo.getOptValue().equals("")) {
				sql = sql + " AND " + vo.getOptName() + " =?";
			}
			if (!vo.getBuy_date().equals("nothing")) {
				sql = sql + " AND BUY_DATE >= SYSDATE - ?";
			}
			pstmt = con.prepareStatement(sql);
			if (!vo.getItem_name().equals("")) {
				pstmt.setString(n++, vo.getItem_name());
			}
			if (!vo.getOptValue().equals("")) {
				pstmt.setString(n++, vo.getOptValue());
			}
			if (!vo.getBuy_date().equals("nothing")) {
				pstmt.setInt(n++, Integer.parseInt(vo.getBuy_date()));
			}
			rs = pstmt.executeQuery();
			int cnt = -1;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}

	public ArrayList<PrepProdVo> search(OrderSearchVo vo, PagingVo pagingVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PrepProdVo> list = new ArrayList<>();
		int n = 1;
		try {
			con = DbcpBean.getConn();
			String appendSql = "";
			if (!vo.getItem_name().equals("")) {
				appendSql = appendSql + "AND ITEM_NAME LIKE '%'||?||'%' ";
			}
			if (!vo.getOptValue().equals("")) {
				appendSql = appendSql + "AND " + vo.getOptName() + " =? ";
			}
			if (!vo.getBuy_date().equals("nothing")) {
				appendSql = appendSql + "AND BUY_DATE >= SYSDATE - ? ";
			}
			String bodySql = "SELECT A2.BUY_NUM, ORDER_NUM, AMOUNT, PNUM, PRICE, ITEM_NAME, BUY_DATE, ACCPRICE, NAME, ORDERCNT, ADDR, CADDR "
					+ "FROM (SELECT AA.*, ROWNUM RNUM "
					+ "FROM(SELECT B.BUY_NUM, O.ORDER_NUM, O.AMOUNT, O.PNUM, O.PRICE, I.ITEM_NAME, TO_CHAR(B.BUY_DATE, 'YYYY\"년\" MM\"월\" DD\"일\" HH24:MI:SS') BUY_DATE, B.ACCPRICE, M.NAME, B.ADDR, B.CADDR "
					+ "FROM ORDERLIST O, ITEM I, BUYBOARD B, MEMBERS M "
					+ "WHERE O.PNUM=I.PNUM AND O.BUY_NUM=B.BUY_NUM AND B.MNUM=M.MNUM AND STATE='배송취소' ";
			bodySql = bodySql + appendSql;
			bodySql = bodySql + "ORDER BY O.BUY_NUM DESC) AA) A3, " + "(SELECT COUNT(*) ORDERCNT, BUY_NUM "
					+ "FROM (SELECT A1.ORDER_NUM, ROWNUM RNUM, A1.BUY_NUM " + "FROM (SELECT B.BUY_NUM, O.ORDER_NUM "
					+ "FROM ORDERLIST O, ITEM I, BUYBOARD B, MEMBERS M "
					+ "WHERE O.PNUM=I.PNUM AND B.BUY_NUM=O.BUY_NUM AND B.MNUM=M.MNUM AND STATE='배송취소' ";
			bodySql = bodySql + appendSql;
			String desinenceSql = "ORDER BY O.BUY_NUM DESC " + ") A1 " + ") " + "WHERE RNUM>=? AND RNUM<=? "
					+ "GROUP BY BUY_NUM) A2 " + "WHERE A2.BUY_NUM=A3.BUY_NUM AND RNUM>=? AND RNUM<=? "
					+ "ORDER BY BUY_NUM DESC";
			pstmt = con.prepareStatement(bodySql + desinenceSql);
			if (!vo.getItem_name().equals("")) {
				pstmt.setString(n++, vo.getItem_name());
			}
			if (!vo.getOptValue().equals("")) {
				pstmt.setString(n++, vo.getOptValue());
			}
			if (!vo.getBuy_date().equals("nothing")) {
				pstmt.setInt(n++, Integer.parseInt(vo.getBuy_date()));
			}
			if (!vo.getItem_name().equals("")) {
				pstmt.setString(n++, vo.getItem_name());
			}
			if (!vo.getOptValue().equals("")) {
				pstmt.setString(n++, vo.getOptValue());
			}
			if (!vo.getBuy_date().equals("nothing")) {
				pstmt.setInt(n++, Integer.parseInt(vo.getBuy_date()));
			}
			pstmt.setInt(n++, pagingVo.getStartRow());
			pstmt.setInt(n++, pagingVo.getEndRow());
			pstmt.setInt(n++, pagingVo.getStartRow());
			pstmt.setInt(n++, pagingVo.getEndRow());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int buy_num = rs.getInt("buy_num");
				int order_num = rs.getInt("order_num");
				int amount = rs.getInt("amount");
				int pnum = rs.getInt("pnum");
				int price = rs.getInt("price");
				String item_name = rs.getString("item_name");
				String buy_date = rs.getString("buy_date");
				int accprice = rs.getInt("accprice");
				String name = rs.getString("name");
				int ordercnt = rs.getInt("ordercnt");
				String addr = rs.getString("addr");
				String caddr = rs.getString("caddr");
				PrepProdVo prepVo = new PrepProdVo(buy_num, order_num, amount, pnum, price, item_name, buy_date,
						accprice, name, ordercnt, addr, caddr);
				list.add(prepVo);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
}
