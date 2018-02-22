package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.ItemImageVo;
import diamang.dbcp.DbcpBean;

public class IteamImageDao {
	
	private static IteamImageDao instance = new IteamImageDao();
	private IteamImageDao() {}
	public static IteamImageDao getInstance() {
		return instance;
	}
	
	//상품추가
	public int itemImageInsert(ItemImageVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into itemImage values(itemImg_sq.nextval,?,?)";
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
	public ArrayList<ItemImageVo> listAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from itemImage";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ItemImageVo> list = new ArrayList<>();
			while(rs.next()) {
				int num = rs.getInt(1);
				int pnum = rs.getInt(2);
				String savefilename = rs.getString(3);
				
				ItemImageVo vo = new ItemImageVo(num, pnum, savefilename);
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
			String sql = "delete from itemImage where num=?";
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
	public ItemImageVo getinfo(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from itemImage where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int pnum = rs.getInt(2);
				String savefilename = rs.getString(3);
				ItemImageVo vo = new ItemImageVo(num, pnum, savefilename);
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
	public int update(ItemImageVo vo) {
		System.out.println(vo.toString());
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "update itemImage set pnum=?,savefilename=? where num=?";
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

