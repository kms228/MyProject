package shs.admin.dao.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.members.MembersgradeVo;

public class MembersgradeDao {

	public ArrayList<MembersgradeVo> getGradeinfo() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="SELECT G.GNUM, G.GRADE, G.DRATE, NVL(GNUMCNT, 0) GNUMCNT " + 
					   "FROM GRADE G,(SELECT GNUM, COUNT(GNUM) GNUMCNT FROM MEMBERS GROUP BY GNUM) M " + 
					   "WHERE G.GNUM = M.GNUM(+)"; 
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MembersgradeVo> list = new ArrayList<>();
			while(rs.next()) {
				int gnum=rs.getInt("gnum");
				String grade = rs.getString("grade");				
				int drate=rs.getInt("drate");				
				int gnumcnt=rs.getInt("gnumcnt");				
				MembersgradeVo gradeVo = new MembersgradeVo(gnum, grade, drate, gnumcnt);
				System.out.println(gnum);
				list.add(gradeVo);				
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}	
	public MembersgradeVo getGrade(String gnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="SELECT * FROM GRADE WHERE GNUM=?";			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, gnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int gnum1=rs.getInt("gnum");
				String grade = rs.getString("grade");
				int drate=rs.getInt("drate");								
				MembersgradeVo gradeVo = new MembersgradeVo(gnum1, grade, drate,0);
				return gradeVo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public int updateGrade(MembersgradeVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE GRADE SET GRADE=?, DRATE=? WHERE GNUM=?";
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getGrade());				
			pstmt.setInt(2, vo.getDrate());				
			pstmt.setInt(3, vo.getGnum());				
			return pstmt.executeUpdate();												 							
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}	
	}
	public int addGrade(MembersgradeVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO GRADE VALUES(GRADE_SEQ.NEXTVAL,?,?)";
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getGrade());				
			pstmt.setInt(2, vo.getDrate());								
			return pstmt.executeUpdate();												 							
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}	
	}
}
