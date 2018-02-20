package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.members.MembersVo;
import user.controller.JoinController_hhj;
import user.vo.MemversVo;
public class MembersDao_hhj {
	
	public int update(MemversVo update) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update members set pwd=?,email=?,address=?,phone=? where id=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,update.getPwd());
			pstmt.setString(2,update.getEmail());
			pstmt.setString(3,update.getAddress());
			pstmt.setString(4,update.getPhone());
			pstmt.setString(5,update.getId());
			
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public MemversVo getinfo(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql = "select * from members where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int mnum=rs.getInt("mnum");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String birthday=rs.getString("birthday");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String phone=rs.getString("phone");
				String gnum=rs.getString("gnum");
				String joindate=rs.getString("joindate");
				MemversVo user = new MemversVo(mnum, id, pwd, name, birthday, email, address, phone, gnum, joindate);
				return user;
			}
			return null;
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	
	
	public int insert(MemversVo user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into members values(mnum.nextval,?,?,?,?,?,?,?,1,SYSDATE)";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,user.getId());
			pstmt.setString(2,user.getPwd());
			pstmt.setString(3,user.getName());
			pstmt.setString(4,user.getBirthday());
			pstmt.setString(5,user.getEmail());
			pstmt.setString(6,user.getAddress());
			pstmt.setString(7,user.getPhone());
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	public int login(HashMap<String, String> map) {
		String id=map.get("id");
		String pwd=map.get("pwd");
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql = "select * from members where id=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1; //회원 1
			}else {
				return 0; //정보없으면 0
			}
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1; //오류
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	
	
}//class









