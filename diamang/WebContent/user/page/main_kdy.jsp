<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#mainImg img{width:100%;margin-top:30px;}
	h2{width:100%;height:32px;background-color:#E1E1E1;}
	#item{float:left;width:249px;height:370px;border-right:1px solid gray;border-bottom:1px solid gray;text-align: center;}
	#item img{width:249px;height:300px;}
	#mwrap{overflow:hidden;height:auto;}
	#new{overflow:hidden;height:auto;}
	#earring{overflow:hidden;height:auto;}
	#neck{overflow:hidden;height:auto;}
	#ring{overflow:hidden;height:auto;}
	#couple{overflow:hidden;height:auto;}
	.iwrap{overflow:hidden;height:auto;}
	#earring a{float:right;}
	#neck a{float:right;}
	#ring a{float:right;}
	#couple a{float:right;margin-bottom:20px;}
</style>
<div id="mwrap">
	<div id="mainImg">
		<img src="<%=request.getContextPath() %>/user/images/main.png" alt="메인이미지">
	</div>
	<div id="new">
		<div class="iwrap">
			<h2>NEW</h2>
		<c:forEach var="vo" items="${list }" varStatus="status">	
		<div id="item">
			<a href="<%=request.getContextPath()%>/itemDetail.do?pnum=${vo.pnum}"><img src="<%=request.getContextPath() %>/admin/upload/${vo.savename }"></a><br>
			${vo.item_name }<br>
			${vo.price }
		</div>
	</c:forEach>
		</div>
	</div>
	<div id="earring">
		<div class="iwrap">
			<h2>EARRING</h2>
		<c:forEach var="vo2" items="${list1 }" varStatus="status">	
			<div id="item">
				<a href="<%=request.getContextPath()%>/itemDetail.do?pnum=${vo2.pnum}"><img src="<%=request.getContextPath() %>/admin/upload/${vo2.savename }"></a><br>
				${vo2.item_name }<br>
				${vo2.price }
			</div>
	</c:forEach>
		</div>
		<a href="<%=request.getContextPath()%>/itemList.do?cmd=earring">더보기</a>
	</div>
	<div id="neck">
		<div class="iwrap">
			<h2>NECKLACE</h2>
		<c:forEach var="vo2" items="${list3 }" varStatus="status">	
			<div id="item">
				<a href="<%=request.getContextPath()%>/itemDetail.do?pnum=${vo2.pnum}"><img src="<%=request.getContextPath() %>/admin/upload/${vo2.savename }"></a><br>
				${vo2.item_name }<br>
				${vo2.price }
			</div>
	</c:forEach>
		</div>
		<a href="<%=request.getContextPath()%>/itemList.do?cmd=necklace">더보기</a>
	</div>
	<div id="ring">
		<div class="iwrap">
			<h2>RING</h2>
		<c:forEach var="vo2" items="${list4 }" varStatus="status">	
			<div id="item">
				<a href="<%=request.getContextPath()%>/itemDetail.do?pnum=${vo2.pnum}"><img src="<%=request.getContextPath() %>/admin/upload/${vo2.savename }"></a><br>
				${vo2.item_name }<br>
				${vo2.price }
			</div>
	</c:forEach>
		</div>
		<a href="<%=request.getContextPath()%>/itemList.do?cmd=ring">더보기</a>
	</div>
	<div id="couple">
		<div class="iwrap">
			<h2>COUPLE</h2>
		<c:forEach var="vo2" items="${list5 }" varStatus="status">	
			<div id="item">
				<a href="<%=request.getContextPath()%>/itemDetail.do?pnum=${vo2.pnum}"><img src="<%=request.getContextPath() %>/admin/upload/${vo2.savename }"></a><br>
				${vo2.item_name }<br>
				${vo2.price }
			</div>
	</c:forEach>
		</div>
		<a href="<%=request.getContextPath()%>/itemList.do?cmd=couple">더보기</a>
	</div>
</div>