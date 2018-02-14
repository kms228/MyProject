<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<h2>로그인</h2>
	<form action="<c:url value='/login.do?cmd=login'/>" method="post">
		<table>
			<tr><th>아이디</th><td><input type="text" name="id"></td></tr>
			<tr><th>비밀번호</th><td><input type="text" name="pwd"></td></tr>	
		</table>
		<input type="submit" value="로그인"><input type="button" value="취소" onclick="javascript:history.go(-1)">
	</form>
</div>
    