package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import diamang.dbcp.DbcpBean;
import user.controller.ItemDetailController_kdy;
import user.vo.BuyBoardVo_kdy;
import user.vo.ItemListVo_kdy;

public class OrderDao_kdy {
	
	public int getBuyNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql = "select max(buy_num) max from buyboard";
			pstmt =con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int buy_num = rs.getInt("max");
				return buy_num;
			}else {
				return -1;
			}
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public int orderList(int pnum, int buy_num, int amount, int price) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql = "insert into orderlist values(orderlist_seq.nextval,?,?,?,?,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, price);
			pstmt.setInt(3, pnum);
			pstmt.setInt(4, buy_num);
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
		
	}
	
	public int orderOk(BuyBoardVo_kdy vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql = "insert into buyboard values(buyboard_seq.nextval,?,sysdate,'상품준비중',?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,vo.getMnum());
			pstmt.setString(2,vo.getName());
			pstmt.setString(3,vo.getAddr());
			pstmt.setString(4, vo.getCaddr());
			pstmt.setInt(5, vo.getAccprice());
			pstmt.setInt(6, vo.getDrate());
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
}
