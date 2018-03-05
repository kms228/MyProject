<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#iteminfo{width:100%;border:1px solid gray;}
	#iteminfo div{display:inline-block;}
	#img img{width:90px;height:90px;}
</style>
<div>
	<%-- 글쓰기 폼 --%>
	<form method="post" action="<%=request.getContextPath() %>/rv_update.do?cmd=updateOk" onsubmit="return check()">
	<div id="iteminfo">
	<div id="img">
		<img src="<%=request.getContextPath()%>/admin/upload/${vo2.savename}">
	</div>
	<div id="info">
			<p>${vo2.item_name }</p>
			<p>${vo2.price }</p>
			<p><input type="button" value="상품상세보기"  onclick = "" id="button1">
			</p>
	</div>
	</div>
	<div>
		제목<input type="text" name="title" value="${vo.title }"><br>
		<label id="lwriter"></label><input type="hidden" name="writer" id="writer"><br>
		<label id="lpwd"></label><input type="hidden" name="pwd" id="pwd">
		<c:if test="${empty sessionScope.id }">
			<script>
				var writer = document.getElementById("writer");
				var pwd = document.getElementById("pwd");
				var lwriter = document.getElementById("lwriter");
				var lpwd = document.getElementById("lpwd");
				lwriter.innerHTML="작성자";
				lpwd.innerHTML="비밀번호";
				
				writer.type="text";
				pwd.type="password";
			</script>
		</c:if>
		별점
		<input type="radio" name="star" value="1">☆
		<input type="radio" name="star" value="2">☆☆
		<input type="radio" name="star" value="3">☆☆☆
		<input type="radio" name="star" value="4">☆☆☆☆
		<input type="radio" name="star" value="5">☆☆☆☆☆
		<br>
		<textarea rows="10" cols="60" name="content">${vo.content }</textarea><br>
		<input type="hidden" name="rv_num" value="${rv_num }">
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