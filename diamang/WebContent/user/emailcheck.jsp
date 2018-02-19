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
	boolean email1 = false;
	try{
		con=DbcpBean.getConn();
		String sql = "select * from members where email=? ";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()){
			email1=true;
		}		
	}catch(SQLException se){
		System.out.println(se.getMessage());
	}finally{
		DbcpBean.closeConn(con, pstmt, rs);
	}
	JSONObject json = new JSONObject();
	json.put("email1",email1);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw = response.getWriter();
	pw.print(json);
	pw.close();


%>