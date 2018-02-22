<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
	#mainmenu ul li{float:left;list-style:none;padding-right: 10px;}
	#mainmenu ul li a{text-decoration: none;}
	#topmenu ul li{float:right;list-style:none;padding-right: 10px;}
	#topmenu ul li a{text-decoration: none;}
</style>
<div>
<div id="topmenu">
	<ul>
		<li><a href="<%=request.getContextPath()%>/move.do?cmd=login">로그인</a></li>
		<li><a href="<%=request.getContextPath()%>/move.do?cmd=join">회원가입</a></li>
		<li><a href="">주문/배송 조회</a></li>
		<li><a href="">장바구니</a></li>
		<li><a href="">마이페이지</a></li>
	</ul>
</div>
<div>
	<a href="<%=request.getContextPath()%>/move.do?cmd=main">
	<img src="<%=request.getContextPath() %>/user/images/header.jpg" alt="jewely header" height="200px" width="300px"></a>
</div>
<div id="mainmenu">
<ul>
	<li><a href="">귀걸이</a></li>
	<li><a href="">목걸이</a></li>
	<li><a href="">반지</a></li>
	<li><a href="">커플</a></li>
	<li><a href="<%=request.getContextPath() %>/review_list.do">리뷰</a></li>
</ul>
	<br>
</div>
</div>