<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form>
	<table id="itemListTable" border="1">
		<tr>
			<th>상품분류</th><th>상품번호</th><th>상품이름</th><th>재고</th><th>가격</th><th>수정 및 삭제</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<c:if test="${vo.fieldnum eq 11}"><c:set var="fnum" value="반지 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 12}"><c:set var="fnum" value="반지 > 다이아"/></c:if>
			<c:if test="${vo.fieldnum eq 13}"><c:set var="fnum" value="반지 > 탄생석"/></c:if>
			<c:if test="${vo.fieldnum eq 14}"><c:set var="fnum" value="반지 > 실버"/></c:if>
			
			<c:if test="${vo.fieldnum eq 21}"><c:set var="fnum" value="목걸이 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 22}"><c:set var="fnum" value="목걸이 > 다이아"/></c:if>
			<c:if test="${vo.fieldnum eq 23}"><c:set var="fnum" value="목걸이 > 탄생석"/></c:if>
			
			<c:if test="${vo.fieldnum eq 31}"><c:set var="fnum" value="귀걸이 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 32}"><c:set var="fnum" value="귀걸이 > 탄생석"/></c:if>
			
			<c:if test="${vo.fieldnum eq 41}"><c:set var="fnum" value="커플링 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 42}"><c:set var="fnum" value="커플링 > 다이아"/></c:if>
			<c:if test="${vo.fieldnum eq 43}"><c:set var="fnum" value="커플링 > 실버"/></c:if>
			<tr class="tr1">
				<td style="width: 250px;">${fnum }</td>
				<td style="width: 75px;">${vo.pnum }</td>
				<td style="width: 225px;">${vo.item_name }</td>
				<td style="width: 100px;">${vo.stock }</td>
				<td style="width: 150px;">${vo.price }</td>
				<td style="width: 200px;">
					<input type="button" value="수정" class="basicbtn" onclick="newPage(${vo.pnum })">
					<input type="button" value="삭제" class="basicbtn" onclick="del(${vo.pnum},${vo.fieldnum })">
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<br>
	<!-- 페이지처리 -->
	<div id='pageText'>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${startPage-1}">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${i}&fieldnum=${requestScope.fieldnum}"><span style="color:blue">[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${i}&fieldnum=${requestScope.fieldnum}"><span style="color:gray">[${i }]</span></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${endPage+1}">[다음]</a>
			</c:when>
			<c:otherwise>
				[다음]
			</c:otherwise>
		</c:choose>
	</div>
</form>