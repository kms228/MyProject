<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function(){
		var optName = "${search.optName}";
		if(!(optName==="")){			
			document.getElementById(optName).setAttribute("selected","selected");			
		}
		var buy_date = "${search.buy_date}";
		if(!(buy_date==="")){
			document.getElementById(buy_date).setAttribute("selected","selected");
		}
	}
	var page = function(num){
		var url = "prepareProduct.do?cmd=search&pageNum="+ num;
		
		var optValue = "${search.optValue}";
		var buy_date = "${search.buy_date}";
		var item_name = "${search.item_name}";				
		url = url+"&optName=${search.optName}";
		url = url+"&optValue=${search.optValue}";				
		url = url+"&item_name=${search.item_name}";			
		if(!(buy_date === "")){
			url = url+"&buy_date=${search.buy_date}";	
		} else {
			url = url+"&buy_date=nothing"
		}
		location.href=url;
	}
</script>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/admin/prepareProduct.do?cmd=search">
		<table>
			<tbody>
				<tr>
					<th>검색어</th>
					<td><select name="optName" id="optName">
							<option value="order_num" id="order_num">주문번호</option>
							<option value="name" id="name">주문자명</option>
							<option value="id" id="id">주문자아이디</option>
					</select><input type="text" size="30" name="optValue" value="${search.optValue }"></td>
				</tr>
				<tr>
					<th>기간</th>
					<td><select name="buy_date">
							<option value="nothing" id="nothing">전체</option>
							<option value="1" id="1">오늘</option>
							<option value="7" id="7">7일</option>
							<option value="15" id="15">15일</option>							
						</select></td>					
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" size="30" name="item_name" value="${search.item_name }"></td>
				</tr>
			</tbody>
		</table>
		<div>
			<input type="submit" value="검색"><input type="reset"
				value="초기화">
		</div>
	</form>
	<table border="1">
	<thead>
		<tr>
			<th>주문일</th><th>주문번호</th><th>주문자</th><th>구매번호</th><th>상품번호</th><th>상품명</th><th>수량</th><th>상품구매금액</th><th>총금액</th><th>배송중 처리</th><th>배송취소 처리</th>
		</tr>
	</thead>
	<tbody>	
		<c:if test="${list == null }">
		<tr>
			<td colspan="10" align="center">검색된 주문내역이 없습니다.</td>	
		</tr>
		</c:if>				
		<c:set var="n" value="-1"/>
		<c:set var="cnt" value="-1"/>
		<c:forEach var="vo" items="${list }">		
		<tr>
		<c:choose>			
			<c:when test="${n != vo.buy_num && vo.ordercnt != 0}">				
				<td rowspan="${vo.ordercnt+1 }">${vo.buy_date }</td><td rowspan="${vo.ordercnt+1 }">${vo.buy_num}</td><td rowspan="${vo.ordercnt+1 }">${vo.name }</td><td>${vo.order_num }</td><td>${vo.pnum }</td><td>${vo.item_name }</td><td>${vo.amount }</td><td>${vo.price }</td><td rowspan="${vo.ordercnt+1 }">${vo.accprice }</td><td align="center" rowspan="${vo.ordercnt+1 }"><input type="button" value="배송중"></td><td align="center" rowspan="${vo.ordercnt+1 }"><input type="button" value="배송취소"></td>
				<c:set var="n" value="${vo.buy_num }"/>
				<c:set var="cnt" value="${vo.ordercnt }"/>
			</c:when>	
			<c:otherwise>
					<td>${vo.order_num }</td><td>${vo.pnum }</td><td>${vo.item_name }</td><td>${vo.amount }</td><td>${vo.price }</td>
					<c:set var="cnt" value="${cnt-1 }" />																						
			</c:otherwise>
		</c:choose>
		<c:if test="${cnt==1 }">
			<tr>
				<td colspan="5">연락처 : ${vo.caddr}<br>배송지 : ${vo.addr }</td>
			</tr>
		</c:if>
		</tr>
		</c:forEach>
	</tbody>	
</table>
<!-- 페이징처리 -->
<c:if test="${pageVo!=null }">
<div>
	<c:choose>
		<c:when test="${pageVo.startPage>pageVo.currentPageVolume }">
			<a href="javascript:page(${pageVo.startPage-1})">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]	
		</c:otherwise>
	</c:choose>	
	<c:forEach var="i" begin="${pageVo.startPage }"  end="${pageVo.endPage }">
		<c:choose>
			<c:when test="${pageVo.pageNum==i }">
				<a href="javascript:page(${i })"><span style="color:blue">[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="javascript:page(${i })"><span style="color:gray">[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageVo.endPage<pageVo.pageCount }">
			<a href="javascript:page(${pageVo.endPage+1 })">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]	
		</c:otherwise>	
	</c:choose>	
</div>
</c:if>
<div>
<h2>도움말</h2>
<ul>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
</ul>
</div>
</body>
</html>