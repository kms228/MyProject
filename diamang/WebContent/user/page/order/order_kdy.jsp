<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	h2{text-align: center;margin-bottom:50px;}
	#orderInfo{width:100%;}
	#orderInfo img{width:50px;height:50px;}
	#orderInfo table{width:100%;text-align: center;}
	#addInfo {margin-top:30px;}
	#addInfo table{width:100%;border: 1px solid #CFCFCF;border-collapse: collapse;text-align: left;}
	#addInfo tr{border: 1px solid #CFCFCF;}
	#addInfo td{border: 1px solid #CFCFCF;padding:10px;}
	#addInfo th{background-color: #E1E1E1;width:30%;}
	#perchaseInfo{display:inline-block;overflow: hidden;width:100%;background-color:#E1E1E1;border: 1px solid #CFCFCF;}
	#perchaseInfo div{float:left;}
	#payInfo table{width:100%;border: 1px solid #CFCFCF;border-collapse: collapse;text-align: left;}
	#payInfo tr{border: 1px solid #CFCFCF;}
	#payInfo td{border: 1px solid #CFCFCF;padding:10px;}
	#payInfo th{background-color: #E1E1E1;width:30%;}
	#message{width:100%;height:100px;background-color: #E1E1E1;border: 1px solid #CFCFCF;margin-top:30px;}
	#message p{padding-top:25px;padding-left:60px;}
	#meminfo{width:100%;height:auto;overflow:hidden;margin-top:30px;}
	#button{padding-left:90%;margin-top:50px;width:100%;margin-bottom:30px;}
</style>
<h2>ORDER</h2>
<form method="post" action="<%=request.getContextPath()%>/order.do?cmd=orderOk">
<div id="orderInfo">
	<table>
		<tr>
			<th>이미지</th><th style="width:500px;">상품명</th><th>판매가</th><th>회원등급</th><th>수량</th><th>배송비</th><th>합계</th>
		</tr>
		<tr>
			<td colspan="7"><hr></td>
		</tr>
		<!-- list에 담긴 상품 정보 가져오기 -->
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
				<c:set var="total" value="${total+vo.total }"/>
			</c:forEach>
				${total }원
			</p>
			<p>${grade.drate }%</p>
			<p>${total}원</p>
		</div>
	</div>
</div>
<div id="message">
	<p>저희 쇼핑몰을 이용해주셔서 감사합니다. ${mem.name}님은 [${grade.grade}] 등급 회원입니다.</p> 
</div>


<div id="addInfo">
배송지 정보<br><br>
<table>
	<tr>
		<th>성명</th><td><input type="text" value="${mem.name}" name="name"></td>
	</tr>
	<tr>
		<th>주소</th><td><input type="text" value="${mem.address}" name="address"></td>
	</tr>
	<tr>
		<th>휴대폰 번호</th><td><input type="text" value="${mem.phone}" name="phone" id="phone"></td>
	</tr>
</table>
</div>
<br>
<div id="payInfo">
결제 정보<br><br>
<table>
	<tr>
		<th>결제 금액</th><td>${total }원</td>
	</tr> 
</table>
<table>
	<tr>
		<th>결제 방식</th><td><input type="radio" value="bank" name="pay" checked="checked">무통장 입금</td>
	</tr>
</table>
</div>

<!-- hidden으로 보내는 정보 -->
<input type="hidden" value="${total }" name="payPrice">
<input type="hidden" value="${mem.mnum }" name="mnum">
<input type="hidden" value="${grade.drate }" name="drate">
<input type="hidden" value="${list }" name="list">
<c:set var="list" value="${list}" scope="request"/>

<div id="button">
<input type="submit" value="결제하기">
</div>
</form>