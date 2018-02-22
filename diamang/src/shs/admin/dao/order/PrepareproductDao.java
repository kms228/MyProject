package shs.admin.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.order.PrepProdVo;
import shs.admin.vo.order.SearchOptionVo;
import shs.admin.vo.paging.PagingVo;

public class PrepareproductDao {
	// 전체 글의 갯수 구하기
	public int getCount(SearchOptionVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 1;
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT NVL(COUNT(ORDER_NUM),0) CNT "
					+ "FROM ORDERLIST O, ITEM I, BUYBOARD B, MEMBERS M "
					+ "WHERE O.PNUM=I.PNUM AND O.BUY_NUM=B.BUY_NUM AND B.MNUM=M.MNUM AND STATE='상품준비중'";			
			if(!vo.getItem_name().equals("")) {
				sql = sql + " AND ITEM_NAME =?";				
			}
			if(!vo.getOptValue().equals("")) {
				sql = sql +" AND "+ vo.getOptName()+" =?" ;
			}
			if(!vo.getBuy_date().equals("nothing")) {
				sql = sql +" AND BUY_DATE >= SYSDATE - ?" ;				
			}
			pstmt = con.prepareStatement(sql);			
			if(!vo.getItem_name().equals("")) {
				pstmt.setString(n++, vo.getItem_name());
			}
			if(!vo.getOptValue().equals("")) {				
				pstmt.setString(n++, vo.getOptValue());
			}
			if(!vo.getBuy_date().equals("nothing")) {				
				pstmt.setInt(n++, Integer.parseInt(vo.getBuy_date()));
			}					
			rs = pstmt.executeQuery();
			int cnt = -1;
			if(rs.next()) {
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
	//PrepareProduct
	public ArrayList<PrepProdVo> search(SearchOptionVo vo, PagingVo pagingVo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PrepProdVo> list = new ArrayList<>();
		int n = 1;
		try {
			con = DbcpBean.getConn();
			String bodySql = "SELECT * " + 
							 "FROM (SELECT AA.*, ROWNUM RNUM " + 
							 	   "FROM(SELECT B.BUY_NUM, O.ORDER_NUM, O.AMOUNT, O.PNUM, O.PRICE, I.ITEM_NAME, TO_CHAR(B.BUY_DATE, 'YYYY\"년\" MM\"월\" DD\"일\" HH24:MI:SS') BUY_DATE, B.ACCPRICE, M.NAME " + 
							 	   		"FROM ORDERLIST O, ITEM I, BUYBOARD B, MEMBERS M " + 
							 	   		"WHERE O.PNUM=I.PNUM AND O.BUY_NUM=B.BUY_NUM AND B.MNUM=M.MNUM AND STATE='상품준비중'";
			String desinenceSql = 	   " ORDER BY O.BUY_NUM) AA) WHERE RNUM>=? AND RNUM<=?";
			if(!vo.getItem_name().equals("")) {
				bodySql = bodySql + " AND ITEM_NAME =?";				
			}
			if(!vo.getOptValue().equals("")) {
				bodySql = bodySql +" AND "+ vo.getOptName()+" =?" ;			
			}
			if(!vo.getBuy_date().equals("nothing")) {
				bodySql = bodySql +" AND BUY_DATE >= SYSDATE - ?" ;				
			}
			pstmt = con.prepareStatement(bodySql+desinenceSql);
			if(!vo.getItem_name().equals("")) {
				pstmt.setString(n++, vo.getItem_name());
			}
			if(!vo.getOptValue().equals("")) {				
				pstmt.setString(n++, vo.getOptValue());
			}
			if(!vo.getBuy_date().equals("nothing")) {				
				pstmt.setInt(n++, Integer.parseInt(vo.getBuy_date()));
			}
			pstmt.setInt(n++, pagingVo.getStartRow());
			pstmt.setInt(n++, pagingVo.getEndRow());
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				int buy_num = rs.getInt("buy_num");
				int order_num = rs.getInt("order_num");
				int amount = rs.getInt("amount");
				int pnum = rs.getInt("pnum");
				int price = rs.getInt("price");
				String item_name = rs.getString("item_name");
				String buy_date = rs.getString("buy_date");
				int accprice = rs.getInt("accprice");
				String name = rs.getString("name");
				PrepProdVo prepVo = new PrepProdVo(buy_num, order_num, amount, pnum, price, item_name, buy_date, accprice, name);
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