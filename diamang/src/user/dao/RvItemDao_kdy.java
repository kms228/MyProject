package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import user.vo.RvItemVo_kdy;

public class RvItemDao_kdy {
	//�̹��� ���, ��ǰ �̸�, ��ǰ ������ �������� �޼ҵ�
	public ArrayList<RvItemVo_kdy> itemInfo(int mnum){
		Connection con=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "";
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally{
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
}
