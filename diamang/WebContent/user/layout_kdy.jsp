<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="user/css/common_kdy.css?ver=1">
</head>
<body>
<div id="wrap">
	 
	<div id="ad">
	<input type="button" value="▲" id="butten1" name="up">
		<div id="ad1">
		<p>이곳은 사이드바 1</p>
		</div>
		<div id="ad2">
		<p>이곳은 사이드바 2</p>
		</div>
		<div id="ad3">
		<p>이곳은 사이드바 3</p>
		</div>
	<input type="button" value="▼" id="butten2" name="up">
	</div>

	<div id="header">
		<jsp:include page="page/header_kdy.jsp"></jsp:include>
	</div>
	

	<div id="content">
		<jsp:include page="${cmd }"></jsp:include>
		<% String a= (String)request.getAttribute("cmd");
			System.out.println(a);%>
	</div>
	
	<div id="footer">
		<jsp:include page="page/footer_kdy.jsp"></jsp:include>	
	</div>
</div>
</body>
</html>