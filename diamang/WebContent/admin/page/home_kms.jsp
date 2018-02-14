<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<%
	String id = (String)session.getAttribute("id");
	System.out.println(id);
	if(id==null){
%>
		<h3>admin페이지</h3>
		<h4>로그인 ㄱㄱ</h4>			
<%
	}else{
%>
		<h3><%=id%> 님 환영합니다.</h3>
<%
	}
%>
</div>