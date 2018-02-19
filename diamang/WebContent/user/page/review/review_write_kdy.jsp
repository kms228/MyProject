<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#iteminfo{width:100%; height:100px;border:1px solid gray;}
</style>
<div>
	<%-- 글쓰기 폼 --%>
	<form method="post" action="<%=request.getContextPath() %>/imgUpload.do" enctype="multipart/form-data"
			onsubmit="return check()">
	<div id="iteminfo">
	<input type="button" value="상품정보선택">
	</div>
	<!-- 부모 글에 대한 정보 !-->
	<input type="hidden" name="rv_num" value="${param.rv_num }">
	<input type="hidden" name="ref" value="${param.ref }">
	<input type="hidden" name="lev" value="${param.lev }">
	<input type="hidden" name="step" value="${param.step }">
	<div>
		제목<input type="text" name="title"><br>
		<c:if test="${empty sessionScope.id }">
			작성자<input type="text" name="writer"><br>
			이메일<input type="text" name="email">
		</c:if><br>
		별점
		<input type="radio" name="star" value="1">☆
		<input type="radio" name="star" value="2">☆☆
		<input type="radio" name="star" value="3">☆☆☆
		<input type="radio" name="star" value="4">☆☆☆☆
		<input type="radio" name="star" value="5">☆☆☆☆☆
		<br>
		<textarea rows="10" cols="60" name="content"></textarea><br>
		첨부파일1<input type="file" name="file1"><br>
		<c:if test="${empty sessionScope.id }">
			비밀번호<input type="password" name="pwd">
		</c:if><br>
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