package shs.admin.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.board.BoardqnaVo;
import shs.admin.vo.paging.PagingVo;

public class BoardqnaDao {
	// 전체 글의 갯수 구하기
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT NVL(COUNT(*),0) CNT FROM QNA WHERE MNUM != 10000 AND REGDATE >= SYSDATE -20";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}	
	public ArrayList<BoardqnaVo> getList(PagingVo pageVo){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="SELECT * " + 
					"FROM (SELECT QNA.*, ROWNUM RNUM " + 
					"      FROM (SELECT Q.*, NVL(QCNT,0) QCNT, M.ID " + 
					"            FROM QNA Q, MEMBERS M, (SELECT Q1.QNUM, COUNT(*) QCNT " + 
					"                                    FROM QNA Q1, QNA Q2 " + 
					"                                    WHERE Q1.QNUM = Q2.REFER AND Q2.MNUM = 10000 " + 
					"                                    GROUP BY Q1.QNUM) Q3 " + 
					"            WHERE Q.MNUM = M.MNUM AND Q.QNUM=Q3.QNUM(+) AND Q.MNUM != 10000 AND REGDATE >= SYSDATE -20 " + 
					"            ORDER BY REFER DESC, STEP ASC) QNA " + 
					"      ) " + 
					"WHERE RNUM >=? AND RNUM <=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, pageVo.getStartRow());
			pstmt.setInt(2, pageVo.getEndRow());
			rs=pstmt.executeQuery();
			ArrayList<BoardqnaVo> list = new ArrayList<>();
			while(rs.next()) {
				int mnum = rs.getInt("mnum");				
				int qnum = rs.getInt("qnum");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int hit = rs.getInt("hit");				
				int refer = rs.getInt("refer");				
				int lev = rs.getInt("lev");				
				int step = rs.getInt("step");
				String id = rs.getString("id");
				int qcnt = rs.getInt("qcnt");
				BoardqnaVo vo = new BoardqnaVo(mnum,qnum,title,content,regdate,hit,refer,lev,step,id,qcnt);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public int fillupQna(BoardqnaVo vo) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = DbcpBean.getConn();
			String sql = "UPDATE QNA SET STEP=STEP+1 WHERE REFER=? AND STEP>?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, vo.getRefer());
			pstmt1.setInt(2, vo.getStep());
			pstmt1.executeUpdate();
			int lev = vo.getLev()+1;
			int step = vo.getStep()+1;
			sql = "INSERT INTO QNA VALUES(QNA_SEQ.NEXTVAL,10000,?,?,SYSDATE,0,?,?,?)";			
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, vo.getTitle());
			pstmt2.setString(2, vo.getContent());
			pstmt2.setInt(3, vo.getRefer());
			pstmt2.setInt(4, lev);
			pstmt2.setInt(5, step);
			return pstmt2.executeUpdate();												 							
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(null,pstmt1,null);
			DbcpBean.closeConn(con, pstmt2, null);
		}
	}
}
