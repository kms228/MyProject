package user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import user.vo.ItemListVo_kdy;

public class ItemDao_kdy {
	
	public ItemListVo_kdy itemDetail(int pnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename,iii.savename detailimg "
					+ "from item i,itemimg1 ii,itemimg2 iii where i.pnum=? and i.pnum=ii.pnum and ii.pnum=iii.pnum";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				String detailImg = rs.getString("detailimg");
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum,item_name,price,regdate,stock,savename,detailImg);
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
	
	public ArrayList<ItemListVo_kdy> itemList(int item_num,int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con = DbcpBean.getConn();
			String sql = "select * from(select aa.*, rownum rnum from(select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from item i,itemimg1 ii" + 
					" where fieldnum in(select fieldnum from field where classnum=?) and i.pnum=ii.pnum)aa) where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum,item_name,price,regdate,stock,savename);
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
	
	public ArrayList<ItemListVo_kdy> itemOrder(int item_num,int fieldnum,String order){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con = DbcpBean.getConn();
			String sql = "select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from item i,itemimg1 ii "
					+ "where fieldnum in(select fieldnum from field where fieldnum=? and classnum=?) and i.pnum=ii.pnum order by "+order;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fieldnum);
			pstmt.setInt(2, item_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum,item_name,price,regdate,stock,savename);
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
	
	public ArrayList<ItemListVo_kdy> itemAllOrder(int item_num, String order){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con = DbcpBean.getConn();
			String sql = "select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from item i,itemimg1 ii "
					+ "where fieldnum in(select fieldnum from field where classnum=?) and i.pnum=ii.pnum order by "+order;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum,item_name,price,regdate,stock,savename);
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
	
	public int itemCount(int item_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select count(rownum) cnt from item where fieldnum in(select fieldnum from field where classnum=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public int itemFcount(int item_num,int fieldnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select count(rownum) cnt from item where fieldnum in(select fieldnum from field where fieldnum=? and classnum=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fieldnum);
			pstmt.setInt(2, item_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public ArrayList<ItemListVo_kdy> itemField(int item_num, int fieldnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con = DbcpBean.getConn();
			String sql = "select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from item i,itemimg1 ii" + 
					" where fieldnum in(select fieldnum from field where fieldnum=? and classnum=?) and i.pnum=ii.pnum";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fieldnum);
			pstmt.setInt(2, item_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum,item_name,price,regdate,stock,savename);
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
}
