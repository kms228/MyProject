<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>


<c:choose>

<c:when test="${result=='success'}">
<h2>회원삭제 완료</h2>
<h2>언제든 기다릴게요 ㅠ</h2>
<a href="<%=request.getContextPath()%>/user/login_hhj.jsp">로그인페이지</a>
</c:when>

<c:when test="${result=='fail'}">
<h2>정보수정 실패</h2> 
<a href="<%=request.getContextPath()%>/user/updateMembers_hhj.jsp">다시쓰러가기</a>
</c:when>


</c:choose>
</body>
</html>