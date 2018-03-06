package user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diamang.dbcp.DbcpBean;
import user.vo.ItemListVo_kdy;

public class MainPageDao_kdy {
	
	public ArrayList<ItemListVo_kdy> NewItem() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from (select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from"
					+ " item i,itemimg1 ii where i.pnum=ii.pnum order by i.regdate desc) where rownum<9";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum, item_name, price, regdate, stock, savename);
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
	
	public ArrayList<ItemListVo_kdy> earItem() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from (select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from"
					+ " item i,itemimg1 ii where i.pnum=ii.pnum and i.fieldnum>30 and i.fieldnum<40) where rownum<9";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum, item_name, price, regdate, stock, savename);
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
	
	public ArrayList<ItemListVo_kdy> neckItem() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from (select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from"
					+ " item i,itemimg1 ii where i.pnum=ii.pnum and i.fieldnum>20 and i.fieldnum<30) where rownum<9";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum, item_name, price, regdate, stock, savename);
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
	
	public ArrayList<ItemListVo_kdy> ringItem() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from (select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from"
					+ " item i,itemimg1 ii where i.pnum=ii.pnum and i.fieldnum>10 and i.fieldnum<20) where rownum<9";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum, item_name, price, regdate, stock, savename);
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
	
	public ArrayList<ItemListVo_kdy> coupleItem() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemListVo_kdy> list = new ArrayList<>();
		
		try {
			con=DbcpBean.getConn();
			String sql = "select * from (select i.pnum,i.item_name,i.price,i.regdate,i.stock,ii.savename from"
					+ " item i,itemimg1 ii where i.pnum=ii.pnum and i.fieldnum>40 and i.fieldnum<50) where rownum<9";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pnum = rs.getInt("pnum");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				String savename = rs.getString("savename");
				
				ItemListVo_kdy vo = new ItemListVo_kdy(pnum, item_name, price, regdate, stock, savename);
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
