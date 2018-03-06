package user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import user.controller.ItemDetailController_kdy;
import user.vo.BuyBoardVo_kdy;
import user.vo.ItemListVo_kdy;
import user.vo.OrderListVo_kdy;
import user.vo.OrderVo_kdy;

public class OrderDao_kdy {
	
	//주문 번호 당 몇 개의 상품 샀는지 알아보는 메소드
	public int orderlistCnt(int mnum, int buy_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=DbcpBean.getConn();
			String sql = "select count(order_num) cnt from orderlist where buy_num in(select buy_num from buyboard where mnum=? and buy_num=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			pstmt.setInt(2, buy_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}else {
				return -1;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	//주문 배송 조회 중 아이템 이름 가져오기
	public String orderlistInfo(int mnum,int buy_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from(select item_name from item where pnum in(select pnum from orderlist"
					+ " where buy_num in(select buy_num from buyboard where mnum=? and buy_num=?)))where rownum=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			pstmt.setInt(2, buy_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String item_name = rs.getString("item_name");
				return item_name;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	//주문,배송 조회하기 위한 메소드
	public ArrayList<OrderListVo_kdy> buyBoardInfo(int mnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderListVo_kdy> list =new ArrayList<>();
		try {
			con=DbcpBean.getConn();
			String sql = "select buy_num,buy_date,state,accprice from buyboard b where mnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int buy_num = rs.getInt("buy_num");
				Date buy_date = rs.getDate("buy_date");
				String state = rs.getString("state");
				int accprice = rs.getInt("accprice");
				
				String item_name = orderlistInfo(mnum,buy_num);
				int cnt = orderlistCnt(mnum, buy_num);
				OrderListVo_kdy vo = new OrderListVo_kdy(buy_date,buy_num,item_name,accprice,state,cnt);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	
	
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
			pstmt.setString(2,vo.getAddr());
			pstmt.setString(3, vo.getCaddr());
			pstmt.setInt(4, vo.getAccprice());
			pstmt.setInt(5, vo.getDrate());
			pstmt.setString(6,vo.getName());
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
}
