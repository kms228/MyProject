<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#iteminfo{width:100%; height:100px;border:1px solid gray;}
</style>
<div>
	<form method="post" action="<%=request.getContextPath() %>/imgUpload.do" enctype="multipart/form-data"
			onsubmit="return check()">
	<div id="iteminfo">
	<input type="button" value="상품정보선택">
	</div>
	<div>
		제목<input type="text" name="title"><br>
		별점
		<input type="radio" name="star" value="1">☆
		<input type="radio" name="star" value="2">☆☆
		<input type="radio" name="star" value="3">☆☆☆
		<input type="radio" name="star" value="4">☆☆☆☆
		<input type="radio" name="star" value="5">☆☆☆☆☆
		<br>
		<textarea rows="10" cols="60" name="content"></textarea><br>
		첨부파일1<input type="file" name="file1"><br>
		<input type="submit" value="등록">
		
	</div>
	</form>
<script>
	function check(){
		var title = document.getElementsByName("title")[0].value;
		var content = document.getElementsByName("content")[0].value;
		
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