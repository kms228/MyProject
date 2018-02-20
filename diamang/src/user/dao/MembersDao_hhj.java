package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import diamang.dbcp.DbcpBean;
import user.vo.MemversVo;
public class MembersDao_hhj {
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









