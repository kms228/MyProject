<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<h1>body</h1>
			<h4>로그인(세션)안하면 뜨는기본화면</h4>			
		</c:when>
		<c:otherwise>
			<h3>${sessionScope.id }님 환영합니다.</h3>
		</c:otherwise>
	</c:choose>
</div>