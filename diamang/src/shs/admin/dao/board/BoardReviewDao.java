package shs.admin.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.board.BoardReviewVo;
import shs.admin.vo.paging.PagingVo;

public class BoardReviewDao {
	// 전체 글의 갯수 구하기
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT NVL(COUNT(*),0) CNT FROM REVIEW WHERE MNUM != 10000 AND REGDATE >= SYSDATE -5";									
			pstmt = con.prepareStatement(sql);													
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
	public ArrayList<BoardReviewVo> getTop5Today() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardReviewVo> list = new ArrayList<>(); 
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT RV_NUM, TITLE, HIT " + 
						 "FROM REVIEW R " + 
						 "WHERE TO_CHAR(R.REGDATE,'YYYYMMDD')=TO_CHAR(SYSDATE,'YYYYMMDD') AND ROWNUM <= 5 " + 
						 "ORDER BY HIT DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				int rv_num = rs.getInt("rv_num");
				int hit = rs.getInt("hit");
				list.add(new BoardReviewVo(rv_num, title, hit));
			};			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}				
	}
	public ArrayList<BoardReviewVo> getTop5Sevendays() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardReviewVo> list = new ArrayList<>(); 
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT RV_NUM, TITLE, HIT " + 
						 "FROM REVIEW R " + 
						 "WHERE R.REGDATE >= (SYSDATE-8) AND ROWNUM <= 5 " + 
						 "ORDER BY HIT DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				int rv_num = rs.getInt("rv_num");
				int hit = rs.getInt("hit");
				list.add(new BoardReviewVo(rv_num, title, hit));
			};			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public ArrayList<BoardReviewVo> getTop5Reviewcnt(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardReviewVo> list = new ArrayList<>(); 
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT ID, R.RVCNT " + 
						 "FROM MEMBERS M, (SELECT MNUM, COUNT(MNUM) RVCNT FROM REVIEW GROUP BY MNUM) R " + 
						 "WHERE R.MNUM = M.MNUM AND ROWNUM <= 5 AND R.MNUM != 10000";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				int rvcnt = rs.getInt("rvcnt");
				list.add(new BoardReviewVo(id, rvcnt));
			};			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public ArrayList<BoardReviewVo> getReview(PagingVo pagingVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardReviewVo> list = new ArrayList<>(); 
		try {
			con = DbcpBean.getConn();
			String sql = "SELECT * " + 
					 "FROM (SELECT R4.*, ROWNUM RNUM " + 
					 "      FROM (SELECT R.RV_NUM, TITLE, TO_CHAR(R.REGDATE, 'YYYY\"-\"MM\"-\"DD') REGDATE, STAR, ID, NVL(RVCNT,0) RVCNT, R.REF, R.LEV, R.STEP, R.CONTENT " + 
					 			" FROM REVIEW R, MEMBERS M, (SELECT R1.RV_NUM, COUNT(*) RVCNT " + 
					 										"FROM REVIEW R1, REVIEW R2 " + 
					 										"WHERE R1.RV_NUM = R2.REF AND R2.MNUM = 10000 " + 
					 										"GROUP BY R1.RV_NUM) R3 " + 
					 			 "WHERE R.MNUM=M.MNUM AND R.RV_NUM=R3.RV_NUM(+) AND R.MNUM != 10000 AND REGDATE >= SYSDATE -5 " + 
					 			 "ORDER BY REF DESC, STEP ASC"
					 		   + ") R4 " + 
					 	   ") " + 
					 "WHERE RNUM>=? AND RNUM<=?";			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pagingVo.getStartRow());
			pstmt.setInt(2, pagingVo.getEndRow());
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				int rv_num = rs.getInt("rv_num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				String id = rs.getString("id");
				int star = rs.getInt("star");
				int rvcnt = rs.getInt("rvcnt");
				int ref = rs.getInt("ref"); 
				int lev = rs.getInt("lev");
				int step = rs.getInt("step");
				//new BoardReviewVo(rv_num, title, content, regdate, hit, star, savename, id, item_name, rvcnt)
				list.add(new BoardReviewVo(rv_num, title, content, regdate, 0, star, null, id, null, rvcnt, ref, lev, step));
			};			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public int fillupReview(BoardReviewVo vo) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = DbcpBean.getConn();
			String sql = "UPDATE REVIEW SET STEP=STEP+1 WHERE REF=? AND STEP>?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, vo.getRef());
			pstmt1.setInt(2, vo.getStep());
			pstmt1.executeUpdate();
			int lev = vo.getLev()+1;
			int step = vo.getStep()+1;
			sql = "INSERT INTO REVIEW VALUES(REVIEW_SEQ.NEXTVAL,10000,?,?,SYSDATE,0,?,?,?,5,?,NULL)";			
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, vo.getTitle());
			pstmt2.setString(2, vo.getContent());
			pstmt2.setInt(3, vo.getRef());
			pstmt2.setInt(4, lev);
			pstmt2.setInt(5, step);
			pstmt2.setString(6, vo.getPwd());
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
