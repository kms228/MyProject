package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.BasicInfoVo;
import diamang.dbcp.DbcpBean;

public class BasicInfoDao {
	private static BasicInfoDao instance = new BasicInfoDao();
	private BasicInfoDao() {}
	public static BasicInfoDao getInstance() {
		return instance;
	}
	
	public int insert(BasicInfoVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql1 = "insert into basicInfo values(basicInfo_sq.nextval,?)";
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, vo.getInfo());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
			
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int update(BasicInfoVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "update basicInfo set info=? where num=?";
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getInfo());
			pstmt.setInt(2, vo.getNum());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
			
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from basicInfo where num=?";
			con = DbcpBean.getConn();
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
	
	public ArrayList<BasicInfoVo> getInfo() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from basicInfo order by num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<BasicInfoVo> list = new ArrayList<>();
			while(rs.next()) {
				int num = rs.getInt(1);
				String info = rs.getString(2);
				BasicInfoVo vo = new BasicInfoVo(info, num);
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
}
