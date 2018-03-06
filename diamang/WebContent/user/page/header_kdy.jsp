<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#mainmenu ul{padding-bottom:3px;}
	#mainmenu ul li{float:left;list-style:none;padding-right: 10px;}
	#mainmenu ul li a{text-decoration: none;}
	#topmenu ul li{float:right;list-style:none;padding-right: 10px;}
	#topmenu ul li a{text-decoration: none;}
</style>
<div>
<div id="topmenu">
	<ul>
	<c:choose>
		<c:when test="${empty sessionScope.id }">
		<li><a href="<%=request.getContextPath()%>/move.do?cmd=login">로그인</a></li>
		</c:when>
		<c:otherwise>
		<li><a href="<%=request.getContextPath()%>/JoinController.do?cmd=logout">로그아웃</a></li>
		<li><a href="<%=request.getContextPath()%>/JoinController.do?cmd=update&id=${sessionScope.id}">마이페이지</a></li>
		</c:otherwise>
	</c:choose>
		<li><a href="<%=request.getContextPath()%>/move.do?cmd=join">회원가입</a></li>
		<li><a href="<%=request.getContextPath()%>/order.do?cmd=orderList">주문/배송 조회</a></li>
		<li><a href="<%=request.getContextPath()%>/cart.do?cmd=cartList">장바구니</a></li>
	</ul>
</div>
<div>
	<a href="<%=request.getContextPath()%>/main.do">
	<img src="<%=request.getContextPath() %>/user/images/header.jpg" alt="jewely header" height="280px" width="300px"></a>
</div>
<div id="mainmenu">
<hr>
<ul>
	<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=earring">귀걸이</a></li>
	<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=necklace">목걸이</a></li>
	<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=ring">반지</a></li>
	<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=couple">커플</a></li>
	<li><a href="<%=request.getContextPath() %>/review_list.do">리뷰</a></li>
	<li><a href="<%=request.getContextPath()%>/qna_list.do">Q&A</a></li>
</ul>
	<br>
</div>
</div>