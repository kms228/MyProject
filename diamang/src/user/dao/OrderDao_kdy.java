package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import diamang.dbcp.DbcpBean;
import user.vo.BuyBoardVo_kdy;

public class OrderDao_kdy {
	
	public int OrderOk(BuyBoardVo_kdy vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql = "insert into buyboard values(buyboard_seq.nextval,?,sysdate,'결제완료',?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,vo.getMnum());
			pstmt.setString(2,vo.getName());
			pstmt.setString(3,vo.getAddr());
			pstmt.setString(4, vo.getCaddr());
			pstmt.setInt(5, vo.getAccprice());
			pstmt.setInt(6, vo.getDrate());
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
}
