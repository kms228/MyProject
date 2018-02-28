<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>ADD TO CART</h2>

<div id="orderInfo">
	<table>
		<tr>
			<th>이미지</th><th style="width:500px;">상품명</th><th>판매가</th><th>회원등급</th><th>수량</th><th>배송비</th><th>합계</th>
		</tr>
		<tr>
			<td colspan="7"><hr></td>
		</tr>
		<c:forEach var="vo" items="${list }">
		<tr>
			<td><img src="<%=request.getContextPath()%>/admin/upload/${vo.savename}" alt="대표이미지"></td>
			<td>${vo.item_name }</td>
			<td>${vo.price }원</td>
			<td>${vo.grade }</td>
			<td>${vo.amount }</td>
			<td>3000원</td>
			<td>${vo.total }원</td>
		</tr>
		<tr><td colspan="7"><hr style="color: gray;"></td></tr>
		</c:forEach>
	</table>
</div>