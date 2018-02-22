<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#menu p{display:inline;margin:50px;}
</style>
</head>
<script type="text/javascript">
	var xhr=null;
	function list(){
		
	}
</script>
<body onload="list()">
	<h2>Product Info</h2>
	<c:if test="${empty sessionScope.id }">
	</c:if>
	${sessionScope.id}
	<div id="menu">
		<p>상품 이미지</p>
		<p>상품 정보</p>
		<p>상품 선택</p>
	</div>
	<div id="list"></div>
</body>
</html>