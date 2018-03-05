<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${result eq 'success' }">
		<h2>주문이 완료되었습니다.</h2>
	</c:when>
	<c:otherwise>
		<h2>오류 발생</h2>
	</c:otherwise>
</c:choose>

