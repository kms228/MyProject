<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="bodyimg">
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<h3>로그인후 이용하십시오.</h3>		
		</c:when>
		<c:otherwise>
			<h3>${sessionScope.id }님 환영합니다.</h3>
		</c:otherwise>
	</c:choose>
</div>