<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#itemImg{width:50px;height:50px;}
	td{height:50px;}
	#title h1{text-align: center;}
	#list table{width:100%;margin-bottom:10px;overflow: hidden;height: auto;text-align: center;}
	#page{text-align: center;}
</style>
<div id="title">
<h1>Q&A</h1>
<hr>
<a href="<%=request.getContextPath() %>/move.do?cmd=qna_write">글쓰기</a>
</div>
<br>
<div id="list">
<table>
	<tr style="background-color: pink">
		<th>no</th><th style="width:500px">title</th><th>writer</th><th>date</th><th>hit</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.qnum }</td>
			<td style="text-align: left;">
			<c:if test="${vo.lev>0}">
				<%-- 답글인 경우 들여쓰기 하기 --%>
				<c:forEach var="i" begin="1" end="${vo.lev }">
					&nbsp;&nbsp;
				</c:forEach>
				[re]
			</c:if>
			<a href="<%=request.getContextPath()%>/qna_detail.do?qnum=${vo.qnum }&">${vo.title }</a>
			</td>
			<td>${vo.writer }</td>
			<td>${vo.regdate }</td>
			<td>${vo.hit }</td>
		</tr>
	</c:forEach>
</table>
<!-- 페이징 -->
	<div id="page">
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="<%=request.getContextPath()%>/review_list.do?pageNum=${startPage-1}">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
	<c:forEach var="i" begin="${startPage }"  end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="<%=request.getContextPath()%>/review_list.do?pageNum=${i}">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/review_list.do?pageNum=${i}">
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
	<br><br>
	</div>
</div>