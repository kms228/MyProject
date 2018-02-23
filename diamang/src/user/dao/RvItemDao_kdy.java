package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.ItemVo;
import diamang.dbcp.DbcpBean;
import user.vo.RvItemVo_kdy;

public class RvItemDao_kdy {
	//이미지 경로, 상품 번호, 상품 이름, 상품 가격을 가져오는 메소드
	public ArrayList<RvItemVo_kdy> itemInfo(int mnum){
		Connection con=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select i.pnum, i.item_name, i.price,ii.savename from item i ,itemimg1 ii "
					+ "where i.pnum in(select pnum from orderlist "
					+ "where buy_num in(select buy_num from buyboard where mnum=?) and review=0) and i.pnum=ii.pnum";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs=pstmt.executeQuery();
			ArrayList<RvItemVo_kdy> list = new ArrayList<>();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				System.out.println(pnum);
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				String savename = rs.getString("savename");
				RvItemVo_kdy vo=new RvItemVo_kdy(pnum, item_name, price, savename);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally{
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public RvItemVo_kdy itemInfo2(int pnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select i.item_name, i.price, ii.savename from item i, itemimg1 ii where i.pnum=? and i.pnum=ii.pnum";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				String savename = rs.getString("savename");
				RvItemVo_kdy vo = new RvItemVo_kdy(pnum, item_name, price, savename);
				return vo;
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
