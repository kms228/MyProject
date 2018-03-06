package user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import user.vo.QnaCommVo_kdy;
import user.vo.RvCommVo_kdy;

public class QnaCommDao_kdy {
	
	public int updateOk(int num, String comment) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "update qnacomm set comments=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comment);
			pstmt.setInt(2, num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	
	public int update(int num, int qnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			con = DbcpBean.getConn();
			String sql = "select count(rownum) cnt from qnacomm where num<=? and qnum=? order by num asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, qnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int index = rs.getInt("cnt");
				return index;
			}else {
				return -1;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con= DbcpBean.getConn();
			String sql = "delete from qnacomm where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public ArrayList<QnaCommVo_kdy> list(int qnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnaCommVo_kdy> list = new ArrayList<>();
		try {
			con=DbcpBean.getConn();
			String sql = "select num,id,comments,to_char(regdate,'yyyy/mm/dd hh24:mi:ss')"
					+ " reg from qnacomm where qnum=? order by num";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String commId = rs.getString("id");
				String comments = rs.getString("comments");
				String regdate = rs.getString("reg");
				QnaCommVo_kdy vo = new QnaCommVo_kdy(num,commId,null,comments,regdate,qnum);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public int insert(QnaCommVo_kdy vo) {
		Connection con =null;
		PreparedStatement pstmt=null;
		
		try {
			con=DbcpBean.getConn();
			String sql = "insert into qnacomm values(qnacomm_seq.nextval,?,?,?,sysdate,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getComments());
			pstmt.setInt(4, vo.getQnum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
}
