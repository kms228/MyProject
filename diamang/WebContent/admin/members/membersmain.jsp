<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    



<div class="article">
<h2>회원관리 > 회원관리 메인</h2><br>
	<div class="sectionBar">
		<h3>회원현황</h3>
	</div>
<table border="1" class="mem3" >
	<thead>
	
	<tr>
		<th>신규회원(한달내)</th><th>총회원</th></tr>
	</thead>
	<tbody>
	<tr>
		<td>${vo1.recent}</td><td>${vo1.allmembers }</td>
	</tr>
	</tbody>
</table>
<br>
<div class="sectionBar">
	<h3>-최근 가입 회원</h3>
</div>
<table border="1" class="mem3">
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
