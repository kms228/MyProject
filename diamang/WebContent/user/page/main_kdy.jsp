<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#mainImg img{width:100%;margin-top:30px;}
	#best div{width:100%;height:32px;background-color:#E1E1E1;}
	#item{float:left;width:249px;height:370px;border-right:1px solid gray;border-bottom:1px solid gray;text-align: center;}
	#item img{width:249px;height:300px;}
</style>
<div id="mwrap">
	<div id="mainImg">
		<img src="<%=request.getContextPath() %>/user/images/main.png" alt="메인이미지">
	</div>
	<div id="best">
		<div>
			<h2>BEST</h2>
		<c:forEach var="vo" items="${list }" varStatus="status">	
		<div id="item">
			<a href="<%=request.getContextPath()%>/itemDetail.do?pnum=${vo.pnum}"><img src="<%=request.getContextPath() %>/admin/upload/${vo.savename }"></a><br>
			${vo.item_name }<br>
			${vo.price }
		</div>
	</c:forEach>
		</div>
	</div>
</div>