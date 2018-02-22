package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.ItemVo;
import diamang.dbcp.DbcpBean;

public class ItemDao {
	private static ItemDao instance = new ItemDao();
	private ItemDao() {}
	public static ItemDao getInstance() {
		return instance;
	}
	
	//상품추가
	public int itemInsert(ItemVo vo) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			String sql1 = "insert into item values(item_sq.nextval,?,?,sysdate,?,?)";
			con = DbcpBean.getConn();
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setString(1, vo.getItem_name());
			pstmt1.setInt(2, vo.getPrice());
			pstmt1.setInt(3, vo.getStock());
			pstmt1.setInt(4, vo.getFieldnum());
			pstmt1.executeUpdate();
			
			String sql2 = "select pnum from item where item_name=?";
			pstmt2 = con.prepareStatement(sql2);
			rs = pstmt2.executeQuery();
			rs.next();
			return rs.getInt(1);
			
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt1, null);
			DbcpBean.closeConn(null, pstmt2, rs);
		}
	}
/*	
	public int getNum(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pnum=0;
		try {
			String sql = "select pnum from item where item_name=?";
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pnum = rs.getInt(1);
			}
			return pnum;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
*/	
	//상품전체리스트보기
	public ArrayList<ItemVo> listAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from item";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ItemVo> list = new ArrayList<>();
			while(rs.next()) {
				int pnum = rs.getInt(1);
				String item_name = rs.getString(2);
				int price = rs.getInt(3);
				Date regdate = rs.getDate(4);
				int stock = rs.getInt(5);
				int fieldnum = rs.getInt(6);
				ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, fieldnum);
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
	//상품삭제(상품번호)
	public int delete(int pnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "delete from item where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	//상품검색(상품번호)
	public ItemVo getinfo(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from item where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int pnum = rs.getInt(1);
				String item_name = rs.getString(2);
				int price = rs.getInt(3);
				Date regdate = rs.getDate(4);
				int stock = rs.getInt(5);
				int fieldnum = rs.getInt(6);
				ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, fieldnum);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	//상품정보수정
	public int update(ItemVo vo) {
		System.out.println(vo.toString());
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "update item set item_name=?,price=?,regdate=sysdate,stock=?,fieldnum=? where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getItem_name());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getStock());
			pstmt.setInt(4, vo.getFieldnum());
			pstmt.setInt(5, vo.getPnum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
}
