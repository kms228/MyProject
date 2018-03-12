<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	
</script>


<div id="box0">
	<div class="box1">
		<h2>상점관리</h2><br>
		<div class="sectionBar">
			<ul>
				<li onclick="location.href='<%=request.getContextPath()%>/store?cmd=basicInfo'">기본정보 설정</li>
			</ul> 
		</div>
	</div>

	<div class="box1">
		<h2>회원관리</h2><br>
		<div class="sectionBar">
			<ul>
				<li onclick="location.href='<%=request.getContextPath() %>/admin/members.do?cmd=membersmain'">회원관리 메인</li>
				<li onclick="location.href='<%=request.getContextPath() %>/admin/members.do?cmd=membersinfo'">회원정보 조회</li>
				<li onclick="location.href='<%=request.getContextPath() %>/admin/members.do?cmd=membersgrade'">회원등급 관리</li>
			</ul>
		</div>
	</div>
	
	<div class="box1">
		<h2>상품관리</h2><br>
		<div class="sectionBar"> 
			<ul>
				<li onclick="location.href='<%=request.getContextPath()%>/item?cmd=insert'">상품 등록</li>
				<li onclick="location.href='<%=request.getContextPath()%>/item?cmd=list'">상품 수정 및 삭제</li>
			</ul>
		</div>
	</div>
	
	<div class="box1">
		<h2>주문관리</h2><br>
		<div class="sectionBar"> 
			<ul>
				<li onclick="location.href='<%=request.getContextPath()%>/admin/order.do?cmd=prepareproduct'">상품 준비중</li>
				<li onclick="location.href='<%=request.getContextPath()%>/admin/order.do?cmd=shippedend'">배송중</li>
				<li onclick="location.href='<%=request.getContextPath()%>/admin/order.do?cmd=shippedcomplete'">배송 완료</li>
				<li onclick="location.href='<%=request.getContextPath()%>/admin/order.do?cmd=ordercancel'">배송 취소</li>
			</ul>
		</div>
	</div>
	
	<div class="box1">
		<h2>게시판관리</h2><br>
		<div class="sectionBar"> 
			<ul>
				<li onclick="location.href='<%=request.getContextPath()%>/admin/board.do?cmd=boardqna'">QnA 관리</li>
				<li onclick="location.href='<%=request.getContextPath()%>/admin/board.do?cmd=boardreview'">후기게시판 관리</li>
			</ul>
		</div>
	</div>
	
	<br>
</div>