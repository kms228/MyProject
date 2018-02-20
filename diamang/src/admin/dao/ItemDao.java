package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.ItemVo;
import diamang.dbcp.DbcpBean;

public class ItemDao {
	private static ItemDao instance = new ItemDao();
	private ItemDao() {}
	public static ItemDao getInstance() {
		return instance;
	}
	
	//상품추가
	public int insert(ItemVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into item values(item_sq.nextval,?,?,sysdate,?,?,?,?)";
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getItem_name());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getStock());
			pstmt.setString(4, vo.getSavename());
			pstmt.setString(5, vo.getOrgname());
			pstmt.setInt(6, vo.getFieldnum());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	//상품전체리스트보기
	public ArrayList<ItemVo> listAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from item";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ItemVo> list = new ArrayList<>();
			while(rs.next()) {
				int pnum = rs.getInt(1);
				String item_name = rs.getString(2);
				int price = rs.getInt(3);
				Date regdate = rs.getDate(4);
				int stock = rs.getInt(5);
				String savename = rs.getString(6);
				String orgname = rs.getString(7);
				int fieldnum = rs.getInt(8);
				ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, savename, orgname, fieldnum);
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
	//상품삭제(상품번호)
	public int delete(int pnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "delete from item where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	//상품검색(상품번호)
	public ItemVo getinfo(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from item where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int pnum = rs.getInt(1);
				String item_name = rs.getString(2);
				int price = rs.getInt(3);
				Date regdate = rs.getDate(4);
				int stock = rs.getInt(5);
				String savename = rs.getString(6);
				String orgname = rs.getString(7);
				int fieldnum = rs.getInt(8);
				ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, savename, orgname, fieldnum);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	//상품정보수정
	public int update(ItemVo vo) {
		System.out.println(vo.toString());
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "update item set item_name=?,price=?,regdate=sysdate,stock=?,savename=?,orgname=?,fieldnum=? where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getItem_name());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getStock());
			pstmt.setString(4, vo.getSavename());
			pstmt.setString(5, vo.getOrgname());
			pstmt.setInt(6, vo.getFieldnum());
			pstmt.setInt(7,vo.getPnum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
}
