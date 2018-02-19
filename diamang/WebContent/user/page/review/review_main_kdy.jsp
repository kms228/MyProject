<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<h1>REVIEW</h1>
<hr>
<a href="<%=request.getContextPath() %>/move.do?cmd=review_write">글쓰기</a>
</div>
<div>
<table>
	<tr style="background-color: pink">
		<th>no</th><th>product</th><th style="width:500px">title</th><th>writer</th><th>date</th><th>hit</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.rv_num }</td>
			<td>null</td> <%-- 선택한 상품의 이미지 --%>
			<td>
			<c:if test="${vo.lev>0}">
				<%-- 답글인 경우 들여쓰기 하기 --%>
				<c:forEach var="i" begin="1" end="${vo.lev }">
					&nbsp;&nbsp;
				</c:forEach>
				[re]
			</c:if>
			<a href="<%=request.getContextPath()%>/rv_detail.do?rv_num=${vo.rv_num }">${vo.title }</a>
			</td>
			<td>${vo.writer }</td>
			<td>${vo.regdate }</td>
			<td>${vo.hit }</td>
		</tr>
	</c:forEach>
</table>
<!-- 페이징 -->
<c:forEach var="i" begin="${startPage }"  end="${endPage }">
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="<%=request.getContextPath()%>/board/list.do?pageNum=${startPage-1}">이전</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="<%=request.getContextPath()%>/board/list.do?pageNum=${i}">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/board/list.do?pageNum=${i}">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="<%=request.getContextPath()%>/review_list.do?pageNum=${endPage+1}">다음</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>