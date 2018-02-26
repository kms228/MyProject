package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.ItemImg1Vo;
import diamang.dbcp.DbcpBean;

public class ItemImg1Dao {
	
	private static ItemImg1Dao instance = new ItemImg1Dao();
	private ItemImg1Dao() {}
	public static ItemImg1Dao getInstance() {
		return instance;
	}
	
	//상품추가
	public int itemImg1Insert(ItemImg1Vo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into itemImg1 values(itemImg_sq.nextval,?,?)";
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			pstmt.setString(2, vo.getSavefilename());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	//상품전체리스트보기
	public ArrayList<ItemImg1Vo> listAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from itemImg1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ItemImg1Vo> list = new ArrayList<>();
			while(rs.next()) {
				int num = rs.getInt(1);
				int pnum = rs.getInt(2);
				String savefilename = rs.getString(3);
				
				ItemImg1Vo vo = new ItemImg1Vo(num, pnum, savefilename);
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
	//상품이미지삭제(상품이미지번호)
	public int delete(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "delete from itemImg1 where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	//상품검색(상품번호)
	public ItemImg1Vo getinfo(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from itemImg1 where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int pnum = rs.getInt(2);
				String savefilename = rs.getString(3);
				ItemImg1Vo vo = new ItemImg1Vo(num, pnum, savefilename);
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
	public int update(ItemImg1Vo vo) {
		System.out.println(vo.toString());
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "update itemImg1 set pnum=?,savefilename=? where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			pstmt.setString(2, vo.getSavefilename());
			
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
}

