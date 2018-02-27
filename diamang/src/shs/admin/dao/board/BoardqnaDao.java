package shs.admin.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import shs.admin.vo.board.BoardqnaVo;

public class BoardqnaDao {
	// 전체 글의 갯수 구하기
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(count(num),0) cnt from qna";
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
	public ArrayList<BoardqnaVo> getList(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="SELECT * FROM QNA";
			pstmt=con.prepareStatement(sql);
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
				BoardqnaVo vo = new BoardqnaVo(mnum,qnum,title,content,regdate,hit,refer,lev,step);
				list.add(vo);
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
