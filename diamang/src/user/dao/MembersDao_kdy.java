package user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.vo.MembersVo_kdy;
import user.vo.RvBoardVo_kdy;

public class MembersDao_kdy {
	//회원 번호로 회원 모든 정보 조회하는 메소드
	public MembersVo_kdy MembersInfo(int mnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			con=DriverManager.getConnection(url,"scott","tiger");
			
			String sql = "select * from members where mnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs= pstmt.executeQuery();
			if(rs.next()) { 
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				int gradeNum = rs.getInt("gradeNum");
				int accAmt = rs.getInt("accAmt");
				MembersVo_kdy vo = new MembersVo_kdy(mnum, id, pwd, name, birthday, email, address, phone, gradeNum, accAmt);
				return vo;
			}else {
				return null;
			}
		}catch (ClassNotFoundException ce) {
			System.out.println((ce.getMessage()));
			return null;
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
			}
			//DbcpBean.closeConn(con,pstmt,rs);
		}
	}
	
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//	try {
//		con = DbcpBean.getConn();
//		String sql = "select mnum from members where id = ?";
//		pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, id);
//		rs= pstmt.executeQuery();
//		if(rs.next()) {
//			int mnum = rs.getInt("mnum");
//			return mnum;
//		}else {
//			return -1;
//		}
//	}catch(SQLException se) {
//		System.out.println(se.getMessage());
//		return -1;
//	}finally {
//		DbcpBean.closeConn(con, pstmt, rs);
//	}
//}
}
