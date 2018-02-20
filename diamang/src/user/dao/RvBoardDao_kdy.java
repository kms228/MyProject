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
import user.vo.RvBoardVo_kdy;

public class RvBoardDao_kdy {
	
	// 전체 글 번호 중 가장 큰 수 구하는 메소드
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(max(rv_num),0) maxnum from review";
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
		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs= null;
//		
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			String url="jdbc:oracle:thin:@localhost:1521:xe";
//			con=DriverManager.getConnection(url,"scott","tiger");
//			
//			String sql = "select NVL(count(rv_num),0) cnt from review";
//			pstmt = con.prepareStatement(sql);
//			rs=pstmt.executeQuery();
//			rs.next();
//			int cnt=rs.getInt("cnt");
//			return cnt;
//		}catch (ClassNotFoundException ce) {
//			System.out.println((ce.getMessage()));
//			return -1;
//		}catch (SQLException se) {
//			System.out.println(se.getMessage());
//			return -1;
//		}finally {
//			try {
//				if(rs!=null) rs.close();
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			}catch(SQLException se) {
//				System.out.println(se.getMessage());
//			}
//		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(count(rv_num),0) cnt from review";
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
	
	//리스트 보여주는 메소드
	public ArrayList<RvBoardVo_kdy> list(int startRow, int endRow){

		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from(" + 
					"select aa.*, rownum rnum from(" + 
					"select * from review order by ref desc,step asc)aa" + 
					") where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();	
			ArrayList<RvBoardVo_kdy> list=new ArrayList<>();
			while(rs.next()) {
				int rv_num=rs.getInt("rv_num");
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
				int star=rs.getInt("star");
				RvBoardVo_kdy vo2 = new RvBoardVo_kdy(rv_num, mnum, title, writer, content, regdate, hit, ref, lev, step, star,null);
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
	
	//id로 회원 번호를 찾는 메소드
	public int searchMnum(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select mnum from members where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				int mnum = rs.getInt("mnum");
				return mnum;
			}else {
				return -1;
			}
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con,pstmt,rs);
		}
	}
	
	//업로드할 글 내용 DB에 삽입하는 메소드
	public int insert(RvBoardVo_kdy vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = DbcpBean.getConn();
			int boardNum=getMaxNum()+1;
			int Rv_num=vo.getRv_num();
			int ref=vo.getRef();
			int lev=vo.getLev();
			int step=vo.getStep();
			if(Rv_num==0) {//새글인 경우
				ref=boardNum;
			}else{
			String sql1 = "update review set step=step+1 where ref=? and step>?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, step);
			pstmt.executeQuery();
			lev = lev+1;
			step = step+1;
			}
			String sql2="insert into review  values(?,?,?,?,sysdate,?,?,?,?,?,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, boardNum);
			pstmt2.setInt(2, vo.getMnum());
			pstmt2.setString(3, vo.getTitle());
			pstmt2.setString(4, vo.getContent());
			pstmt2.setInt(5, vo.getHit());
			pstmt2.setInt(6, ref);
			pstmt2.setInt(7, lev);
			pstmt2.setInt(8, step);
			pstmt2.setInt(9, vo.getStar());
			pstmt2.setString(10, vo.getPwd());
			return pstmt2.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt2, null);
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public void updateHit(int rv_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "update review set hit=hit+1 where rv_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rv_num);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	
	//rv_num 으로 모든 정보를 조회하는 메소드
	public RvBoardVo_kdy getInfo(int rv_num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select * from review where rv_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rv_num);
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
				int star = rs.getInt("star");
				String pwd = rs.getString("pwd");
				RvBoardVo_kdy vo2=new RvBoardVo_kdy(rv_num, mnum, title, writer, content, regdate, hit, ref, lev,
						step, star, pwd);
				return vo2;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
}
