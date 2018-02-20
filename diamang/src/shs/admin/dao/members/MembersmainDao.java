package shs.admin.dao.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.members.MembersVo;
import shs.admin.vo.members.MembersmainVo;


public class MembersmainDao {

	public MembersmainVo getUserval() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="SELECT * FROM (SELECT COUNT(*) ALLMEMBERS FROM MEMBERS),"
					+ "				  (SELECT COUNT(*) RECENT FROM MEMBERS WHERE JOINDATE >= SYSDATE-30)"; 
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String recent=rs.getString("RECENT");
				String allmembers=rs.getString("ALLMEMBERS");				
				MembersmainVo mainVo = new MembersmainVo(recent, allmembers);
				return mainVo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}

	public ArrayList<MembersVo> getUserinfo() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="SELECT M.MNUM, M.ID, M.NAME, M.BIRTHDAY, M.EMAIL, M.ADDRESS, M.PHONE, M.GNUM, M.JOINDATE, G.GRADE " + 
					   "FROM MEMBERS M, GRADE G " + 
					   "WHERE M.GNUM = G.GNUM AND M.JOINDATE>=SYSDATE-30";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list=new ArrayList<>();
			while(rs.next()) {
				int mnum=rs.getInt("mnum");
				String id=rs.getString("id");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String joindate=rs.getString("joindate");
				String grade = rs.getString("grade");
				MembersVo members=new MembersVo(mnum,id,null,name,null,email,null,null,null,joindate,grade);
				list.add(members);
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
