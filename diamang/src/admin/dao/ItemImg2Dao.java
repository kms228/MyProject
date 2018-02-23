package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.ItemImg2Vo;
import diamang.dbcp.DbcpBean;

public class ItemImg2Dao {
	
	private static ItemImg2Dao instance = new ItemImg2Dao();
	private ItemImg2Dao() {}
	public static ItemImg2Dao getInstance() {
		return instance;
	}
	
	//상품추가
	public int itemImg2Insert(ItemImg2Vo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into itemImg2 values(itemImg_sq.nextval,?,?)";
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
	public ArrayList<ItemImg2Vo> listAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from itemImg2";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ItemImg2Vo> list = new ArrayList<>();
			while(rs.next()) {
				int num = rs.getInt(1);
				int pnum = rs.getInt(2);
				String savefilename = rs.getString(3);
				
				ItemImg2Vo vo = new ItemImg2Vo(num, pnum, savefilename);
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
			String sql = "delete from itemImg2 where pnum=?";
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
	public ItemImg2Vo getinfo(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from itemImg2 where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int pnum = rs.getInt(2);
				String savefilename = rs.getString(3);
				ItemImg2Vo vo = new ItemImg2Vo(num, pnum, savefilename);
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
	public int update(ItemImg2Vo vo) {
		System.out.println(vo.toString());
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "update itemImg2 set pnum=?,savefilename=? where pnum=?";
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

