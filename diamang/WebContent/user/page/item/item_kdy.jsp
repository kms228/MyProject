<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	#menu{width:100%;height:50px;}
	
	#menu div{float:left;}
	#total{margin:0px;padding:0px;width:30%;}
	#sort{height:30px;position:relative;left:380px;}
	#sort ul{margin:0px;padding:0px;}
	#sort ul li{display:inline;list-style:none;padding-right: 10px;}
	a{text-decoration: none;}
	table{width:100%;text-align: center;height:30px;}
	table tr{background-color: gray;}
	table td{width:20%;}
	#item{float:left;width:249px;border-top:1px solid gray;border-right:1px solid gray;}
</style>
<h2>BEST</h2>


<div id="menu">
	<div id="total">TOTAL${stock}ITEMS;</div>
	<div id = "sort">
		<ul>
			<li><a href="">신상품순</a></li>
			<li><a href="">상품명순</a></li>
			<li><a href="">낮은가격</a></li>
			<li><a href="">높은가격</a></li>
		</ul>
	</div>
</div>
<div>
	<table>
		<tr>
			<td><a href="">전체보기</a></td>
			<td><a href="">14K/18K</a></td>
			<td><a href="">다이아</a></td>
			<td><a href="">탄생석</a></td>
			<td><a href="">실버</a></td>
		</tr>
	</table>
	<c:forEach var="vo" items="${list }">
		<div id="item">
			<a href=""><img src="<%=request.getContextPath() %>/admin/upload/${vo.savename }"></a><br>
			${vo.item_name }<br>
			${vo.price }
		</div>
	</c:forEach>
	
	
</div>
