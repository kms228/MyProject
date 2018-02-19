package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
