<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<html>
<head>
<style type="text/css">
p label {width: 70px; display: inline-block;}
.joinlayout{margin-left: 300px; margin-top: 5px; width: 525px;padding: 14px;}
#form{border: solid 2px #b7ddf2; background:#ebf4fb;}
#but{
clear:both;
margin-left:10px;
width:90px;
height:30px;
text-align:center;
line-height:31px;
background-color:#000;
color:#FFFFFF;
font-size:13px;
font-weight:bold;
font-family:tahoma;
}
</style>
</head>
<body>

<div id="form" class="joinlayout">
<c:choose>

<c:when test="${result=='success'}">
<h2>회원삭제 완료</h2>
<h2>언제든 기다릴게요 ㅠ</h2>
<a href="<%=request.getContextPath()%>/move.do?cmd=main">로그인페이지</a>
</c:when>

<c:when test="${result=='fail'}">
<h2>정보수정 실패</h2> 
<a href="<%=request.getContextPath()%>/move.do?cmd=main">다시쓰러가기</a>
</c:when>


</c:choose>
</div>
</body>
</html>