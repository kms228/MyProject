<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h1>header</h1>
	<c:choose >
		<c:when test="${empty sessionScope.id }">
			<a href="<c:url value='/login.do?cmd=loginForm'/>">로그인</a>
		</c:when>
		<c:otherwise>
			<%-- 로그아웃 --%>
			${id }님 반갑습니다.<a href="<c:url value='login?cmd=logout'/>">로그아웃</a> 
		</c:otherwise>
	</c:choose>
	
</div>