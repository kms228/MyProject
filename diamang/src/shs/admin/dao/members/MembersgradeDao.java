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
			String sql="SELECT G.GNUM, G.GRADE, G.DRATE, GNUMCNT " + 
					"FROM GRADE G,(SELECT GNUM, COUNT(GNUM) GNUMCNT FROM MEMBERS GROUP BY GNUM) M " + 
					"WHERE G.GNUM = M.GNUM"; 
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MembersgradeVo> list = new ArrayList<>();
			while(rs.next()) {
				int gnum=rs.getInt("gnum");
				String grade = rs.getString("grade");				
				int drate=rs.getInt("drate");				
				int gnumcnt=rs.getInt("gnumcnt");				
				MembersgradeVo gradeVo = new MembersgradeVo(gnum, grade, drate, gnumcnt);
				list.add(gradeVo);
				return list;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}	
}
