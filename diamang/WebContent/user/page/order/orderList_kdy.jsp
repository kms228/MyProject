<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	#title{text-align: center;margin-bottom:40px;}
	#list{width:100%;}
	#list table{width:100%;text-align: center;margin-bottom:30px;}
</style>
<div id="title">
	<h2>ORDER LIST</h2>
</div>
<div id="list">
	<table>
		<tr>
			<th>주문일자</th><th>주문번호</th><th>주문상품</th><th>주문금액</th><th>주문처리상태</th>
		</tr>
		<tr>
			<td colspan="7"><hr></td>
		</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.buy_date }</td><td>${vo.buy_num }</td>
					
					<c:choose>
						<c:when test="${vo.cnt>1 }">
							<td>${vo.item_name } 외 ${vo.cnt-1 }개</td>		
						</c:when>
						<c:otherwise>
							<td>${vo.item_name }</td>
						</c:otherwise>
					</c:choose>
					<td>${vo.accprice }원</td><td>${vo.state }</td>
				</tr>
			</c:forEach>
	</table>
</div>