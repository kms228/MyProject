<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#iteminfo{width:100%;border:1px solid gray;}
	#iteminfo div{display:inline-block;}
	#iteminfo img{width:90px;height:90px;}
</style>
<div>
	<%-- 글쓰기 폼 --%>
	<form method="post" action="<%=request.getContextPath() %>/qnaInsert.do" enctype="multipart/form-data"
			onsubmit="return check()">
	<!-- 부모 글에 대한 정보 !(답글)-->
	<input type="hidden" name="qnum" value="${param.qnum }">
	<input type="hidden" name="ref" value="${param.ref }">
	<input type="hidden" name="lev" value="${param.lev }">
	<input type="hidden" name="step" value="${param.step }">
	<div>
		제목<input type="text" name="title" value="${vo.title }"><br>
		<label id="lwriter"></label><input type="hidden" name="writer" id="writer"><br>
		<label id="lpwd"></label><input type="hidden" name="pwd" id="pwd">
		<br>
		<textarea rows="10" cols="60" name="content">${vo.content }</textarea><br>
		첨부파일1<input type="file" name="file1"><br>
		<input type="submit" value="등록">
	</div>
	</form>
<script>
	function check(){
		var title = document.getElementsByName("title")[0].value;
		var content = document.getElementsByName("content")[0].value;
		
		//제목, 내용 비입력 체크
		if(title==""){
			alert("제목을 입력해주세요");
			return false;
		}
		if(content==""){
			alert("내용을 입력해주세요");
			return false;
		}
		return true;
	}
</script>
</div>