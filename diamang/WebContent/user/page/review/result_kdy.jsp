<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${requestScope.result=='success' }">
		<h1>글쓰기 완료</h1>
	</c:when>
	<c:otherwise>
		<h1>글쓰기 실패</h1>
	</c:otherwise>
</c:choose>
<a href="<%=request.getContextPath() %>/">글 목록으로 이동</a>