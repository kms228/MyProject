<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	#menu{width:100%;height:50px;}
	#main{width:100%;float:left;margin-bottom:20px;}
	#page{text-align: center;}
	#menu div{float:left;}
	#total{margin:0px;padding:0px;width:30%;}
	#sort{height:30px;position:relative;left:380px;}
	#sort ul{margin:0px;padding:0px;}
	#sort ul li{display:inline;list-style:none;padding-right: 10px;}
	a{text-decoration: none;}
	#main table{width:100%;text-align: center;height:30px;background-color: #A6A6A6;}
	#main table tr{}
	#main table td{width:20%;border-right:1px solid white;border-left:1px solid white;}
	
	#iwrap{width:100%;height:auto;border-left: 1px solid gray;border-top: 1px solid gray;overflow: hidden;margin-top:20px;}
	#item{float:left;width:249px;height:370px;border-right:1px solid gray;border-bottom:1px solid gray;text-align: center;}
	#item img{width:249px;height:300px;}
	#itemName{text-align: center;}
	#page{margin-bottom:30px;}
</style>

<div id="itemName">
<c:set var="item" value="${item }"/>
<h2>${fn:toUpperCase(item)}</h2>
</div>

<div id="menu">
	<div id="total">TOTAL ${cnt} ITEMS;</div>
	<div id = "sort">
		<ul>
			<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=new&item_num=${item_num}&fieldnum=${fieldnum}">신상품순</a></li>
			<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=name&item_num=${item_num}&fieldnum=${fieldnum}">상품명순</a></li>
			<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=low&item_num=${item_num}&fieldnum=${fieldnum}">낮은가격</a></li>
			<li><a href="<%=request.getContextPath()%>/itemList.do?cmd=high&item_num=${item_num}&fieldnum=${fieldnum}">높은가격</a></li>
		</ul>
	</div>
</div>
<div id="main">
<!-- 상품 분류 메뉴 -->
	<table>
		<tr>
			<td><a href="<%=request.getContextPath()%>/itemList.do?cmd=all&item_num=${item_num}">전체보기</a></td>
			<td><a href="<%=request.getContextPath()%>/itemList.do?cmd=14k/18k&item_num=${item_num}">14K/18K</a></td>
			<c:if test="${item_num!=3 }">
				<td><a href="<%=request.getContextPath()%>/itemList.do?cmd=diamond&item_num=${item_num}">다이아</a></td>
			</c:if>
			<c:if test="${item_num!=4 }">
				<td><a href="<%=request.getContextPath()%>/itemList.do?cmd=birth&item_num=${item_num}">탄생석</a></td>
			</c:if>
			<c:if test="${item_num!=2 && item_num!=3 }">
				<td><a href="<%=request.getContextPath()%>/itemList.do?cmd=silver&item_num=${item_num}">실버</a></td>
			</c:if>
		</tr>
	</table>
<!-- 상품 표시 div -->
	<div id="iwrap">
	<c:forEach var="vo" items="${list }" varStatus="status">	
		<div id="item">
			<a href="<%=request.getContextPath()%>/itemDetail.do?pnum=${vo.pnum}"><img src="<%=request.getContextPath() %>/admin/upload/${vo.savename }"></a><br>
			${vo.item_name }<br>
			${vo.price }
		</div>
	</c:forEach>
	</div>
</div><br>
<!-- 페이징 -->
<div id="page">
	<c:choose>
		<c:when test="${startPage>10}">
			<a href="<%=request.getContextPath()%>/itemList.do?cmd=${item}&pageNum=${startPage-1}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="<%=request.getContextPath()%>/itemList.do?cmd=${item}&pageNum=${i}"><span style="color:blue">${i }</span></a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/itemList.do?cmd=${item}&pageNum=${i}"><span style="color:gray">${i }</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="<%=request.getContextPath()%>/itemList.do?cmd=${item}&pageNum=${endPage+1}">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>

