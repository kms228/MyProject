<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h1 align="center">쇼핑몰관리24</h1>
	<br>
	<a href="#">상점관리</a>
	<a href="#">고객관리</a>
	<a href="#">상품관리</a>
	<a href="#">배송관리</a>
	<a href="#">게시판관리</a>
	<c:choose >
		<c:when test="${empty sessionScope.id }">
			<div id="login">
				<h3>로그인</h3>
				<form action="<c:url value='/login.do?cmd=login'/>" method="post">
					<table>
						<tr><th>아이디</th><td><input type="text" name="id"></td><td id="loginbutton" rowspan="2"><input type="submit" value="로그인"></td></tr>
						<tr><th>비밀번호</th><td><input type="text" name="pwd"></td></tr>	
					</table>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<%-- 로그아웃 --%>
			<div id="logout">
				<div>
					${id }님 반갑습니다.
					<a href="<c:url value='/login.do?cmd=logout'/>">로그아웃</a>
				</div> 
			</div>
		</c:otherwise>
	</c:choose>
</div>