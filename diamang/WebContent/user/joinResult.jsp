<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<c:choose>
<c:when test="${result=='success'}">
<h2>회원가입을 축하드립니다!!</h2>
<a href="<%=request.getContextPath()%>/move.do?cmd=login">로그인페이지</a>
</c:when>

<c:when test="${result=='fail'}">
<h2>회원가입을 실패하셨습니다. 다시시도해 주세요</h2> 
<a href="<%=request.getContextPath()%>/user/join_hhj.jsp">다시쓰러가기</a>
</c:when>
</c:choose>

</body>
</html>