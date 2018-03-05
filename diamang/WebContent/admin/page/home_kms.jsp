<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="box0">
	<div class="box1">
		<h2>상점관리</h2><br>
		<div class="sectionBar">
			<ul>
				<li><a href="<%=request.getContextPath()%>/store?cmd=basicInfo">기본정보설정</a></li>
			</ul> 
		</div>
	</div>
	
	<div class="box1">
		<h2>회원관리</h2><br>
		<div class="sectionBar">
			<ul>
				<li><a href="<%=request.getContextPath() %>/admin/members.do?cmd=membersmain">회원관리 메인</a></li>
				<li><a href="<%=request.getContextPath() %>/admin/members.do?cmd=membersinfo">회원정보 조회</a></li>
				<li><a href="<%=request.getContextPath() %>/admin/members.do?cmd=membersgrade">회원등급 관리</a></li>
			</ul>
		</div>
	</div>
	
	<div class="box1">
		<h2>상품관리</h2><br>
		<div class="sectionBar"> 
			<ul>
				<li><a href="<%=request.getContextPath()%>/item?cmd=insert">상품 등록</a></li>
				<li><a href="<%=request.getContextPath()%>/item?cmd=list">상품 수정 및 삭제</a></li>
			</ul>
		</div>
	</div>
	
	<div class="box1">
		<h2>주문관리</h2><br>
		<div class="sectionBar"> 
			<ul>
				<li><a href="<%=request.getContextPath()%>/admin/order.do?cmd=prepareproduct">상품 준비중</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/order.do?cmd=shippedend">배송중</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/order.do?cmd=shippedcomplete">배송 완료</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/order.do?cmd=ordercancel">배송 취소</a></li>
			</ul>
		</div>
	</div>
	
	<div class="box1">
		<h2>게시판관리</h2><br>
		<div class="sectionBar"> 
			<ul>
				<li><a href="<%=request.getContextPath()%>/admin/board.do?cmd=boardqna">QnA 관리</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/board.do?cmd=boardreview">후기게시판 관리</a></li>
			</ul>
		</div>
	</div>
	
	<br>
</div>