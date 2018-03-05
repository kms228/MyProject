<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	h2{text-align: center;}
	#orderInfo img{width:50px;height:50px;}
	#orderInfo table{width:100%;text-align: center;}
	#perchaseInfo{display:inline-block;overflow: hidden;width:100%;background-color:#E1E1E1;border: 1px solid #CFCFCF;margin-top:30px;}
	#perchaseInfo div{float:left;}
</style>
<script>
	function order(){
		var frm = document.form;
		frm.method="post";
		frm.action="<%=request.getContextPath()%>/order.do?cmd=orderCheck";
		frm.submit();
	}
	
	function cancel(){
		var pnum=document.getElementsByName("check");
		for(var i=0;i<pnum.length;i++){
			if(pnum[i].checked){
				var item = "item"+pnum[i].value;
				console.log("상품"+item);
				document.cookie=item+'='+';expires=Thu, 01 Jan 1970 00:00:01 GMT;';
			}
		}
		var frm = document.form;
		frm.method="post";
		frm.action="<%=request.getContextPath()%>/cart.do?cmd=cartList";
		frm.submit();
	}
</script>
<h2>ADD TO CART</h2>

<form method="post" action="<%=request.getContextPath()%>/" name="form">
<div id="orderInfo">
	<table>
		<tr>
			<th>선택</th><th>이미지</th><th style="width:500px;">상품명</th><th>판매가</th><th>회원등급</th><th>수량</th><th>합계</th>
		</tr>
		<tr>
			<td colspan="7"><hr></td>
		</tr>
		<c:choose>
			<c:when test="${empty list }">
				<td>장바구니에 담긴 상품이 없습니다.</td>
			</c:when>
			<c:otherwise>
				<c:forEach var="vo" items="${list }">
		<tr>
			<td><input type="checkbox" value="${vo.pnum }"name="check"></td>
			<td><img src="<%=request.getContextPath()%>/admin/upload/${vo.savename}" alt="대표이미지"></td>
			<td>${vo.item_name }</td>
			<td>${vo.price }원</td>
			<td>${vo.grade }</td>
			<td>${vo.amount }</td>
			<td>${vo.total }원</td>
		</tr>
		<tr><td colspan="7"><hr style="color: gray;"></td></tr>
		<!-- hidden으로 보내는 정보 -->
		<input type="hidden" value="${vo.pnum }" name="pnum">
		<input type="hidden" value="${vo.savename }" name="savename">
		<input type="hidden" value="${vo.item_name }" name="item_name">
		<input type="hidden" value="${vo.price }" name="price">
		<input type="hidden" value="${vo.amount }" name="amount">
		</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</div>

<div id="perchaseInfo">
	<div style="margin-top:30px;padding-left:10px;">
	<p>총 주문 금액</p>
	</div>
	<div style="position:relative;left:700px;">
		<div>
			<p>상품 합계 금액</p>
			<p>등급별 할인율</p>
			<p>결제예상금액</p>
		</div>
		<div style="margin-left:10px;">
			<p>
			<c:forEach var="vo" items="${list }">
				<c:set var="sum" value="${sum+vo.total }"/>
			</c:forEach>
				${sum }원
			</p>
			<p>${grade.drate }%</p>
			<p>${sum}원</p>
		</div>
	</div>
</div>

<input type="button" value="선택한 상품 삭제" onclick="cancel()"><input type="button" value="주문하기" onclick="order()">
</form>