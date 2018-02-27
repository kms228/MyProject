<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* .row {		
		width : 100%;
	} */
	.column {		
		float : left;
		width : 32%;
		height : 230px;			
		border : 1px solid gray;
		margin : 5px;
	}
	/* Clear floats after the columns */
	.row:after {
    content: "";
    display: table;
    clear: both;
	}
	
	table {
		border-collapse : collapse;
		width : 100%;
	}
	table tr:hover{background-color: #f5f5f5;}
	
	table, td, th {
		border : 1px solid gray;		
	}	
	table th {
		height : 40px;
		background-color: #4CAF50;
		color : white;			
	}	
</style>
<script type="text/javascript">
	var fillUp = function(rv_num,ref,lev,step){
		alert('오는것 확인');		
		var url =  "<%=request.getContextPath()%>/admin/board.do?cmd=fillUp&rv_num="+rv_num +"&ref="+ref+"&lev="+lev+"&step="+step;
		window.open(url, "_blank", "width=400,height=300");
	}
</script>
</head>
<body>
<h1>후기게시판 관리</h1>
<h2>leaderboard</h2>
<div class="row">
	<div class="column">
		<h3>조회수 TOP 5(오늘)</h3>
		<table>
			<thead>
			<tr>
				<th>글 제목</th><th>hit</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="today" items="${list1 }">
				<tr>
					<td>${today.title }</td><td>${today.hit }</td>
				</tr>
			</c:forEach>						
			</tbody>									
		</table>		
	</div>
	<div class="column">
		<h3>조회수 TOP 5(1주일)</h3>
		<table>
			<thead>
			<tr>
				<th>글 제목</th><th>hit</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="seven" items="${list2 }">
				<tr>
					<td>${seven.title }</td><td>${seven.hit }</td>
				</tr>
			</c:forEach>						
			</tbody>									
		</table>		
	</div>	
	<div class="column">
		<h3>회원별 글 누적 TOP 5</h3>
		<table>
			<thead>
			<tr>
				<th>id</th><th>글 개수</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="today" items="${list3 }">
				<tr>
					<td>${today.id }</td><td>${today.rvcnt }</td>
				</tr>
			</c:forEach>				
			</tbody>
		</table>		
	</div>	
</div>
<div>
<h2>게시물 관리</h2>
<table>
	<thead>
	<tr>
		<th>글번호</th><th>상품이름</th><th>별점</th><th>제목</th><th>작성자</th><th>작성일</th><th>답변상태</th><th>답변하기</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="reviews" items="${list }">	
	<tr>
		<td>${reviews.rv_num }</td><td></td><td>${reviews.star }</td><td>${reviews.title }</td><td>${reviews.id }</td><td>${reviews.regdate }</td>
		<c:choose>
			<c:when test="${reviews.rvcnt>0 }">
				<td>답변완료</td><td>-</td>	
			</c:when>
			<c:otherwise>
				<td>-</td><td><a href="javascript:fillUp(${reviews.rv_num },${reviews.ref },${reviews.lev },${reviews.step })">답변하기</a></td>
			</c:otherwise>
		</c:choose>		
	</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<h2>도움말</h2>
<ul>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
</ul>
</body>
</html>