<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	#orderInfo{width:100%;}
	img{width:50px;height:50px;}
	table{width:100%;text-align: center;}
	#perchaseInfo{display:inline-block;overflow: hidden;width:100%;}
	#perchaseInfo div{float:left;}
	#message{width:100%;height:100px;background-color: #E1E1E1;}
	#message p{padding-top:35px;padding-left:60px;}
	#meminfo{width:100%;height:auto;overflow:hidden;margin-top:30px;}
</style>
<h2>ORDER</h2>

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

<div id="memInfo">
배송지 정보
<input type="hidden" value="${mem.mnum}" name="mnum">
<p><label for="name">이름 </label><input type="text" value="${mem.name}" name="name" readonly="readonly"></p>
<p><label for="birthday">생년월일 </label><input type="text" value="${mem.birthday }" name="birthday" readonly="readonly"></p>
<p><label for="joindate">가입일 </label><input type="text" value="${mem.joindate }" name="joindate" readonly="readonly"></p>
<hr>
<h5 style="margin-left: 150px;">개인정보수정</h5>
<p><label for="pwd">비밀번호</label><input type="password" name="pwd" id="pwd"></p>
<p><label for="pwd2">비밀번호확인</label><input type="password" name="pwd2" id="pwd2"></p>
<p><label for="email">이메일</label><input type="text" value="${mem.email }" name="email" id="email"></p>
<p><label for="address">주소</label><input type="text" value="${mem.address }" name="address" id="address"></p>
<p><label for="phone">휴대폰번호</label><input type="text" value="${mem.phone}" name="phone" id="phone"></p>
</div>