<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>
<%@page import="jdk.nashorn.api.scripting.JSObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="diamang.dbcp.DbcpBean"%>
<%@page import="oracle.jdbc.driver.DBConversion"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String email = request.getParameter("email");
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	boolean using2 = false;
	try{
		con=DbcpBean.getConn();
		System.out.println(email);
		String sql = "select * from members where email=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,email);
		rs=pstmt.executeQuery();
		if(rs.next()){
			using2=true;
		}		
		System.out.println(rs.next());
	}catch(SQLException se){
		System.out.println(se.getMessage());
	}finally{
		DbcpBean.closeConn(con, pstmt, rs);
	}
	JSONObject json1 = new JSONObject();
	json1.put("using2",using2);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw = response.getWriter();
	pw.print(json1);
	pw.close();


%>