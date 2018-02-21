package shs.admin.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.order.PrepProdVo;
import shs.admin.vo.order.SearchOptionVo;

public class PrepareproductDao {
	// 전체 글의 갯수 구하기
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(count(num),0) cnt from ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	//PrepareProduct
	public ArrayList<PrepProdVo> search(SearchOptionVo vo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PrepProdVo> list = new ArrayList<>();
		int n = 1;
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT B.BUY_NUM, O.ORDER_NUM, O.AMOUNT, O.PNUM, O.PRICE, I.ITEM_NAME, B.BUY_DATE, B.ACCPRICE, M.NAME " + 
					"FROM ORDERLIST O, ITEM I, BUYBOARD B, MEMBERS M " + 
					"WHERE O.PNUM=I.PNUM AND O.BUY_NUM=B.BUY_NUM AND B.MNUM=M.MNUM AND STATE='상품준비중'";
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
				pstmt.setString(n, vo.getItem_name());
				n++;
			}
			if(!vo.getOptValue().equals("")) {				
				pstmt.setString(n, vo.getOptValue());
				n++;
			}
			if(!vo.getBuy_date().equals("nothing")) {				
				pstmt.setInt(n, Integer.parseInt(vo.getBuy_date()));
				n++;
			}
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
				return list;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
}