<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;   
}
caption {
	text-align: left;
}
</style>
</head>
<body>
<div class="article">
<table border="1" width="100%">
	<thead>
	<tr>
		<th colspan="2">회원현황</th>
	</tr>
	<tr>
		<th>신규회원(한달내)</th><th>총회원</th></tr>
	</thead>
	<tbody>
	<tr>
		<td>${vo1.recent}</td><td>${vo1.allmembers }</td>
	</tr>
	</tbody>
</table>
<table border="1" width="100%">
<caption>-최근 가입 회원</caption>
	<thead>
	<tr>
		<th>가입일시</th><th>아이디</th><th>이메일</th><th>이름</th><th>등급</th>
	</tr>
	</thead>
	<tbody>		
	<c:forEach var="vo2" items="${list }">
	<tr>
		<td>${vo2.joindate}</td><td>${vo2.id}</td><td>${vo2.email}</td><td>${vo2.name}</td><td>${vo2.grade}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>