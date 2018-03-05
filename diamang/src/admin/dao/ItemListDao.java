package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.ItemVo;
import diamang.dbcp.DbcpBean;

public class ItemListDao {
	// 제일 큰 글번호 얻어오기
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(max(pnum),0) maxnum from item";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int maxnum = rs.getInt("maxnum");
			return maxnum;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}

	// 전체 글의 갯수 구하기
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select NVL(count(pnum),0) cnt from item";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			return cnt;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}

	public int getCount(int fnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(fnum==10) {
			try {
				con = DbcpBean.getConn();
				String sql = "select NVL(count(pnum),0) cnt from item where fieldnum>=10 and fieldnum<=19";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				rs.next();
				int cnt = rs.getInt("cnt");
				return cnt;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		}else if(fnum==20) {
			try {
				con = DbcpBean.getConn();
				String sql = "select NVL(count(pnum),0) cnt from item where fieldnum>=20 and fieldnum<=29";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				rs.next();
				int cnt = rs.getInt("cnt");
				return cnt;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		}else if(fnum==30) {
			try {
				con = DbcpBean.getConn();
				String sql = "select NVL(count(pnum),0) cnt from item where fieldnum>=30 and fieldnum<=39";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				rs.next();
				int cnt = rs.getInt("cnt");
				return cnt;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		}else if(fnum==40) {
			try {
				con = DbcpBean.getConn();
				String sql = "select NVL(count(pnum),0) cnt from item where fieldnum>=40 and fieldnum<=49";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				rs.next();
				int cnt = rs.getInt("cnt");
				return cnt;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		}else {
			try {
				con = DbcpBean.getConn();
				String sql = "select NVL(count(pnum),0) cnt from item where fieldnum=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, fnum);
				rs = pstmt.executeQuery();
				rs.next();
				int cnt = rs.getInt("cnt");
				return cnt;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		}
	
	}

	public ArrayList<ItemVo> list(int startRow, int endRow, int fnum) {
		String sql = "";
		if (fnum == 10) {
			sql = "select * from\r\n" + "    (\r\n" + "    select a.*,rownum rnum  from \r\n" + "        (\r\n"
					+ "            select pnum,item_name,price,regdate,stock,fieldnum\r\n"
					+ "            from item \r\n" + "            where fieldnum>=10 and fieldnum<=19"
					+ "            order by pnum desc\r\n" + "        )a\r\n" + "    )\r\n"
					+ "where rnum>=? and rnum<=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DbcpBean.getConn();
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				ArrayList<ItemVo> list = new ArrayList<>();
				while (rs.next()) {
					int pnum = rs.getInt(1);
					String item_name = rs.getString(2);
					int price = rs.getInt(3);
					Date regdate = rs.getDate(4);
					int stock = rs.getInt(5);
					int fieldnum = rs.getInt(6);
					ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, fieldnum);
					list.add(vo);
				}
				System.out.println(list.toString());
				return list;

			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		} else if (fnum == 20) {
			sql = "select * from\r\n" + "    (\r\n" + "    select a.*,rownum rnum  from \r\n" + "        (\r\n"
					+ "            select pnum,item_name,price,regdate,stock,fieldnum\r\n"
					+ "            from item \r\n" + "            where fieldnum>=20 and fieldnum<=29"
					+ "            order by pnum desc\r\n" + "        )a\r\n" + "    )\r\n"
					+ "where rnum>=? and rnum<=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DbcpBean.getConn();
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				ArrayList<ItemVo> list = new ArrayList<>();
				while (rs.next()) {
					int pnum = rs.getInt(1);
					String item_name = rs.getString(2);
					int price = rs.getInt(3);
					Date regdate = rs.getDate(4);
					int stock = rs.getInt(5);
					int fieldnum = rs.getInt(6);
					ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, fieldnum);
					list.add(vo);
				}
				System.out.println(list.toString());
				return list;

			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		} else if (fnum == 30) {
			sql = "select * from\r\n" + "    (\r\n" + "    select a.*,rownum rnum  from \r\n" + "        (\r\n"
					+ "            select pnum,item_name,price,regdate,stock,fieldnum\r\n"
					+ "            from item \r\n" + "            where fieldnum>=30 and fieldnum<=39"
					+ "            order by pnum desc\r\n" + "        )a\r\n" + "    )\r\n"
					+ "where rnum>=? and rnum<=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DbcpBean.getConn();
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				ArrayList<ItemVo> list = new ArrayList<>();
				while (rs.next()) {
					int pnum = rs.getInt(1);
					String item_name = rs.getString(2);
					int price = rs.getInt(3);
					Date regdate = rs.getDate(4);
					int stock = rs.getInt(5);
					int fieldnum = rs.getInt(6);
					ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, fieldnum);
					list.add(vo);
				}
				System.out.println(list.toString());
				return list;

			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		} else if (fnum == 40) {
			sql = "select * from\r\n" + "    (\r\n" + "    select a.*,rownum rnum  from \r\n" + "        (\r\n"
					+ "            select pnum,item_name,price,regdate,stock,fieldnum\r\n"
					+ "            from item \r\n" + "            where fieldnum>=40 and fieldnum<=49"
					+ "            order by pnum desc\r\n" + "        )a\r\n" + "    )\r\n"
					+ "where rnum>=? and rnum<=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DbcpBean.getConn();
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				ArrayList<ItemVo> list = new ArrayList<>();
				while (rs.next()) {
					int pnum = rs.getInt(1);
					String item_name = rs.getString(2);
					int price = rs.getInt(3);
					Date regdate = rs.getDate(4);
					int stock = rs.getInt(5);
					int fieldnum = rs.getInt(6);
					ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, fieldnum);
					list.add(vo);
				}
				System.out.println(list.toString());
				return list;

			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		} else {
			sql = "select * from\r\n" + "    (\r\n" + "    select a.*,rownum rnum  from \r\n" + "        (\r\n"
					+ "            select pnum,item_name,price,regdate,stock,fieldnum\r\n"
					+ "            from item \r\n" + "            where fieldnum=?"
					+ "            order by pnum desc\r\n" + "        )a\r\n" + "    )\r\n"
					+ "where rnum>=? and rnum<=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DbcpBean.getConn();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, fnum);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				ArrayList<ItemVo> list = new ArrayList<>();
				while (rs.next()) {
					int pnum = rs.getInt(1);
					String item_name = rs.getString(2);
					int price = rs.getInt(3);
					Date regdate = rs.getDate(4);
					int stock = rs.getInt(5);
					int fieldnum = rs.getInt(6);
					ItemVo vo = new ItemVo(pnum, item_name, price, regdate, stock, fieldnum);
					list.add(vo);
				}
				System.out.println(list.toString());
				return list;

			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			} finally {
				DbcpBean.closeConn(con, pstmt, rs);
			}
		}

	}

	public ItemVo getinfo(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from item where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				Date regdate = rs.getDate("regdate");
				int stock = rs.getInt("stock");
				int fieldnum = rs.getInt("fieldnum");
				ItemVo vo = new ItemVo(num, item_name, price, regdate, stock, fieldnum);
				return vo;
			} else {
				return null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
}
