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
}
</style>
<script type="text/javascript">
	//답변하기
	var fillUp = function(qnum,refer,lev,step){			
		var url =  "<%=request.getContextPath()%>/admin/board.do?cmd=fillUp1&qnum="+qnum +"&refer="+refer+"&lev="+lev+"&step="+step;
		window.open(url, "_blank", "width=400,height=300");
	}
	//페이징 url --> controller
	var page = function(num){
		var url = "board.do?cmd=boardqna&pageNum="+ num;
			
		location.href=url;
	}
	var toggleContent = function(qnum){
		var x = document.getElementById(qnum);
		if(x.style.display === "none"){
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}
	/* var content = function(rv_num){
		var url = "board.do?cmd=boardreview&rv_num="+ rv_num;
		location.href=url;
	} */
</script>
</head>
<body>
<h1>QNA게시판 관리</h1>
<div>
<h2>게시물 관리</h2>
<table>
	<thead>
	<tr>
		<th>글번호</th><th>제목</th><th>아이디</th><th>작성일</th><th>답변상태</th><th>답변하기</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="qna" items="${list }">	
	<tr>
		<td>${qna.qnum }</td><td><a href="javascript:toggleContent('${qna.qnum }')">${qna.title }</a></td><td>${qna.id }</td><td>${qna.regdate }</td>
		<c:choose>
			<c:when test="${qna.qcnt>0 }">
				<td>답변완료</td><td>-</td>	
			</c:when>
			<c:otherwise>
				<td>-</td><td><a href="javascript:fillUp(${qna.qnum },${qna.refer },${qna.lev },${qna.step })">답변하기</a></td>
			</c:otherwise>
		</c:choose>		
	</tr>
	<tr>
		<td colspan="6">
	<div class="myDiv" id="${qna.qnum }" style="display : none">
		${qna.content}
	</div>
		</td>
	</tr>				
	</c:forEach>
	</tbody>
</table>
</div>
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
<h2>도움말</h2>
<ul>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
</ul>
</body>
</html>