<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<script type="text/javascript">
	window.onload = function(){
		if(!("${msg}"==="")){
			alert("${msg}");
		}
		var optName = "${search.optName}";
		if(!(optName==="")){			
			document.getElementById(optName).setAttribute("selected","selected");			
		}
		var buy_date = "${search.buy_date}";
		if(!(buy_date==="")){
			document.getElementById(buy_date).setAttribute("selected","selected");
		}
	}
	//페이지 이동 이나 기타 조건시 검색조건 받기위한 메소드
	var getSearch = function(){
		var url = "";		
		var buy_date = "${search.buy_date}";
		var optValue = "${search.optValue}";
								
		url = url+"&item_name=${search.item_name}";
		if(!(optValue === "")){
			url = url+"&optName=${search.optName}";
			url = url+"&optValue=${search.optValue}";
		} else {
			url = url+"&optName=";
			url = url+"&optValue=";
		}
		if(!(buy_date === "")){
			url = url+"&buy_date=${search.buy_date}";	
		} else {
			url = url+"&buy_date=nothing"
		}
		return url;
	}
	
	var page = function(num){
		var url = "order.do?cmd=shippedendSearch&pageNum="+ num;
			
		url = url + getSearch();
		//alert(url);
		location.href=url;
	}	
</script>
<h2>주문관리 > 배송중</h2><br>
<div class="sectionBar">
	<h3>검색</h3>
</div>

	<form method="post" action="<%=request.getContextPath()%>/admin/order.do?cmd=shippedendSearch">
		<table border="1" class="ord"> 
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
		<br>
		<div align="center">
			<input class="basicbtn" type="submit" value="검색"><input class="basicbtn" type="reset"
				value="초기화">
		</div>
	</form>
	<br>
	<div class="sectionBar">
		<h3>검색 결과</h3>
	</div>
	<table border="1" class="ord2">
	<thead>
		<tr>
			<th>주문일</th><th>주문번호</th><th>주문자</th><th>상품명</th><th>수량</th><th>상품구매금액</th><th>총금액</th>
		</tr>
	</thead>
	<tbody>	
		<c:if test="${list == null }">
		<tr>
			<td colspan="11" align="center">검색된 주문내역이 없습니다.</td>	
		</tr>
		</c:if>				
		<c:set var="n" value="-1"/>
		<c:set var="cnt" value="-1"/>
		<c:forEach var="vo" items="${list }">		
		<tr>
		<c:choose>			
			<c:when test="${n != vo.buy_num && vo.ordercnt != 0}">				
				<td rowspan="${vo.ordercnt+1 }">${vo.buy_date }</td>
				<td rowspan="${vo.ordercnt+1 }">${vo.buy_num}</td>
				<td rowspan="${vo.ordercnt+1 }">${vo.name }</td>
				<td>${vo.item_name }</td>
				<td>${vo.amount }</td>
				<td>${vo.price }</td>
				<td rowspan="${vo.ordercnt }">${vo.accprice }</td>				
				<c:set var="n" value="${vo.buy_num }"/>
				<c:set var="cnt" value="${vo.ordercnt }"/>
			</c:when>	
			<c:otherwise>
					<td>${vo.item_name }</td>
					<td>${vo.amount }</td>
					<td>${vo.price }</td>
					<c:set var="cnt" value="${cnt-1 }" />																						
			</c:otherwise>
		</c:choose>
		<c:if test="${cnt==1 }">
			<tr>
				<td colspan="4">연락처 : ${vo.caddr}<br>배송지 : ${vo.addr }</td>
			</tr>
		</c:if>
		</tr>
		</c:forEach>
	</tbody>	
</table>
<!-- 페이징처리 -->
<c:if test="${pageVo!=null }">
<div align="center">
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
	<li>배송완료 처리된 상품들을 검색합니다.</li>
</ul>
</div>
