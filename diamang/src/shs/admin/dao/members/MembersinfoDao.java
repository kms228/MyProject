package shs.admin.dao.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.members.MembersSearchVo;
import shs.admin.vo.members.MembersVo;
import shs.admin.vo.members.MembersgradeVo;
import shs.admin.vo.paging.PagingVo;

public class MembersinfoDao {
	
	public ArrayList<MembersgradeVo> getGrade() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="SELECT GNUM, GRADE, DRATE " + 
					"FROM GRADE WHERE GNUM != 4";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MembersgradeVo> list = new ArrayList<>();
			while(rs.next()) {
				int gnum=rs.getInt("gnum");
				String grade = rs.getString("grade");				
				int drate=rs.getInt("drate");							
				MembersgradeVo gradeVo = new MembersgradeVo(gnum, grade, drate, 0);
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
	// 전체 글의 갯수 구하기
	public int getCount(MembersSearchVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 1;
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT NVL(COUNT(*),0) CNT " + 
					"FROM MEMBERS M, GRADE G " + 
					"WHERE M.GNUM=G.GNUM AND M.ID != 'guest' ";						
			if(!vo.getOptValue().equals("")) {
				sql = sql +"AND "+ vo.getOptName()+" =? " ;
			}
			if(!vo.getGnum().equals("")) {
				sql = sql +"AND M.GNUM = ? " ;				
			}
			pstmt = con.prepareStatement(sql);						
			if(!vo.getOptValue().equals("")) {				
				pstmt.setString(n++, vo.getOptValue());
			}
			if(!vo.getGnum().equals("")) {				
				pstmt.setInt(n++, Integer.parseInt(vo.getGnum()));
			}					
			rs = pstmt.executeQuery();
			int cnt = -1;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}			
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public ArrayList<MembersVo> search(MembersSearchVo vo, PagingVo pagingVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MembersVo> list = new ArrayList<>();
		int n = 1;
		try {
			con = DbcpBean.getConn();
			String bodySql = "SELECT A2.MNUM, A2.ID, A2.PWD, A2.NAME, A2.BIRTHDAY, A2.EMAIL, A2.ADDRESS, A2.PHONE, A2.GNUM, TO_CHAR(A2.JOINDATE, 'YYYY\"년\" MM\"월\" DD\"일\"') JOINDATE, A2.GRADE, A2.RNUM " + 
					"FROM(SELECT A1.*, ROWNUM RNUM " + 
					"     FROM(SELECT M.*,G.GRADE" + 
					"          FROM MEMBERS M, GRADE G " + 
					"          WHERE M.GNUM=G.GNUM AND M.ID != 'guest' ";
			if(!vo.getOptValue().equals("")) {
				bodySql = bodySql +"AND "+ vo.getOptName()+" =? " ;
			}
			if(!vo.getGnum().equals("")) {
				bodySql = bodySql +"AND M.GNUM = ? " ;				
			}						
			String desinenceSql = " ORDER BY JOINDATE ASC) A1) A2 " + 
					"WHERE RNUM>=? AND RNUM<=? " + 
					"ORDER BY RNUM DESC ";
			pstmt = con.prepareStatement(bodySql + desinenceSql);
			if (!vo.getOptValue().equals("")) {
				pstmt.setString(n++, vo.getOptValue());
			}						
			if (!vo.getGnum().equals("")) {
				pstmt.setString(n++, vo.getGnum());
			}						
			pstmt.setInt(n++, pagingVo.getStartRow());
			pstmt.setInt(n++, pagingVo.getEndRow());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int mnum = rs.getInt("mnum");
				String id  = rs.getString("id");
				String name  = rs.getString("name"); 
				String birthday  = rs.getString("birthday"); 
				String email  = rs.getString("email"); 
				String address  = rs.getString("address");
				String phone  = rs.getString("phone"); 
				String gnum  = rs.getString("gnum"); 
				String joindate  = rs.getString("joindate"); 
				String grade  = rs.getString("grade");				
				MembersVo membersVo = new MembersVo(mnum, id, null, name, birthday, email, address,
						phone, gnum, joindate, grade);
				list.add(membersVo);				
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
}
