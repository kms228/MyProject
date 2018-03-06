package user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import user.vo.MembersVo_kdy;
import user.vo.QnaBoardVo_kdy;
import user.vo.QnaImgVo_kdy;
import user.vo.RvBoardVo_kdy;
import user.vo.RvImageVo_kdy;

public class QnaBoardDao_kdy {
	
	// 전체 글 번호 중 가장 큰 수 구하는 메소드
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(max(qnum),0) maxnum from qna";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxnum=rs.getInt("maxnum");
			return maxnum;
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con,pstmt,rs);
		}
	}
	
	//전체 글 갯수 구하는 메소드
	public int getCount() {			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(count(qnum),0) cnt from qna";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt("cnt");
			return cnt;
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con,pstmt,rs);
		}
	}
	
	public int update (QnaBoardVo_kdy vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DbcpBean.getConn();
			String sql = "update qna set title=?,content=? where qnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getQnum());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public void delete(int qnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "delete from qna where qnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qnum);
			pstmt.executeQuery();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public void commDelete(int qnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "delete from qnacomm where qnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qnum);
			pstmt.executeQuery();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	//리스트 보여주는 메소드
	public ArrayList<QnaBoardVo_kdy> list(int startRow, int endRow){

		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from(" + 
					"select aa.*, rownum rnum from(" + 
					"select * from qna order by ref desc,step asc)aa" + 
					") where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();	
			ArrayList<QnaBoardVo_kdy> list=new ArrayList<>();
			while(rs.next()) {
				int qnum=rs.getInt("qnum");
				int mnum = rs.getInt("mnum");
				MembersDao_kdy dao = new MembersDao_kdy();
				MembersVo_kdy vo = dao.MembersInfo(mnum);
				String writer = vo.getName();
				String title=rs.getString("title");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				
				QnaBoardVo_kdy vo2 = new QnaBoardVo_kdy(qnum,mnum,title,writer,content,regdate,hit,ref,lev,step);
				list.add(vo2);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con,pstmt,rs);
		}
	}
	
	//업로드할 글 내용 DB에 삽입하는 메소드
	public int insert(QnaBoardVo_kdy vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = DbcpBean.getConn();
			int boardNum=getMaxNum()+1;
			int qnum=vo.getQnum();
			int ref=vo.getRef();
			int lev=vo.getLev();
			int step=vo.getStep();
			if(qnum==0) {//새글인 경우
				ref=boardNum;
			}else{
			String sql1 = "update qna set step=step+1 where ref=? and step>?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, step);
			pstmt.executeQuery();
			lev = lev+1;
			step = step+1;
			}
			String sql2="insert into qna  values(?,?,?,?,sysdate,?,?,?,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, boardNum);
			pstmt2.setInt(2, vo.getMnum());
			pstmt2.setString(3, vo.getTitle());
			pstmt2.setString(4, vo.getContent());
			pstmt2.setInt(5, vo.getHit());
			pstmt2.setInt(6, ref);
			pstmt2.setInt(7, lev);
			pstmt2.setInt(8, step);
			return pstmt2.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt2, null);
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	//조회수
	public void updateHit(int qnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "update qna set hit=hit+1 where qnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qnum);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	//이미지 업로드할 때 db에 저장하는 메소드
	public void imgUpload(QnaImgVo_kdy vo){
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "insert into qnaimage values(qnaimage_seq.nextval,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getQnum());
			pstmt.setString(2, vo.getSavename());
			pstmt.executeQuery();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
		
	}
	
	//qnum 으로 모든 정보를 조회하는 메소드
	public QnaBoardVo_kdy getInfo(int qnum) {
		Connection con =null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs= null;
		ResultSet rs2= null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select * from qna q,qnaimage i where q.qnum=? and q.qnum=i.qnum";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int mnum = rs.getInt("mnum");
				MembersDao_kdy dao =new MembersDao_kdy();
				MembersVo_kdy vo = dao.MembersInfo(mnum);
				String writer = vo.getName();
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				int ref = rs.getInt("ref");
				int lev = rs.getInt("lev");
				int step = rs.getInt("step");
				String savename = rs.getString("savename");
				QnaBoardVo_kdy vo2 = new QnaBoardVo_kdy(qnum, mnum, title, writer, content, regdate, hit, ref, lev, step, savename);
				return vo2;
			}else {
				String sql2 ="select * from qna where qnum=?";
				pstmt2= con.prepareStatement(sql2);
				pstmt2.setInt(1, qnum);
				rs2=pstmt2.executeQuery();
				if(rs2.next()) {
					int mnum = rs2.getInt("mnum");
					MembersDao_kdy dao =new MembersDao_kdy();
					MembersVo_kdy vo = dao.MembersInfo(mnum);
					String writer = vo.getName();
					String title = rs2.getString("title");
					String content = rs2.getString("content");
					Date regdate = rs2.getDate("regdate");
					int hit = rs2.getInt("hit");
					int ref = rs2.getInt("ref");
					int lev = rs2.getInt("lev");
					int step = rs2.getInt("step");
					QnaBoardVo_kdy vo2 = new QnaBoardVo_kdy();
					return vo2;
				}
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
			DbcpBean.closeConn(con, pstmt2, rs2);
		}
	}
	
}
