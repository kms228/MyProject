<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
					</select> <input type="text" size="30" name="optValue" value="${search.optValue }"></td>
				</tr>
				<tr>
					<th>기간</th>
					<td><select name="buy_date">
							<option value="nothing" id="">전체</option>
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
	<table>
	<thead>
		<tr>
			<th>주문일</th><th>상품번호</th><th>주문자</th><th>상품명</th><th>수량</th><th>상품구매금액</th><th>총금액</th><th>배송중 처리</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${list == null }">
		<tr>
			<td colspan="8" align="center">검색된 주문내역이 없습니다.</td>	
		</tr>
		</c:if>		
		<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.buy_date }</td><td>${vo.pnum }</td><td>${vo.name }</td><td>${vo.item_name }</td><td>${vo.amount }</td><td>${vo.price }</td><td>${vo.accprice }</td><td>0</td>
		</tr>
		</c:forEach>
	</tbody>	
</table>
<!-- 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${startPage>10 }">
			<a href="<%=request.getContextPath()%>/board/list.do?pageNum=${startPage-1}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]	
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i" begin="${startPage }"  end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="<%=request.getContextPath()%>/board/list.do?pageNum=${i}"><span style="color:blue">[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/board/list.do?pageNum=${i}"><span style="color:gray">[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="<%=request.getContextPath()%>/board/list.do?pageNum=${endPage+1}">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]	
		</c:otherwise>	
	</c:choose>	
</div>
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