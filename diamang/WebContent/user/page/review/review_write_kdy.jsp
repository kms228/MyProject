<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#iteminfo{width:100%;border:1px solid gray;}
	#iteminfo div{display:inline-block;}
	#iteminfo img{width:90px;height:90px;}
</style>
<script>
	<!-- 상품 정보 선택하는 팝업 열기 위한 스크립트 !-->
	function itemInfo(){
		window.open("<%=request.getContextPath()%>/itemInfo.do","_blank","width=600px,height=400px");
	}
</script>
<div id="wrap">
	<%-- 글쓰기 폼 --%>
	<form method="post" action="<%=request.getContextPath() %>/imgUpload.do" enctype="multipart/form-data"
			onsubmit="return check()">
	<c:if test="${empty param.rv_num}"> <!-- 답변이 아니라 처음 글 쓸 때 -->
	<div id="iteminfo">
		<input type="button" value="상품정보선택" onclick="itemInfo()"><br>
			<div id="img">
			</div>
			<div id="info">
			<%--<p>상품 이름</p>
				<p>가격</p>
				<p>버튼</p>
				<input type="button" value="상품상세보기"  onclick = "itemInfo()" id="button1">
				<input type="button" value="상품정보선택"  onclick = "itemInfo()" id="button2">
				 --%>
			</div>
	</div>
	</c:if>
	
	<input type="hidden" name="hidPnum" value="${param.pnum }">
	<!-- 부모 글에 대한 정보 !(답글)-->
	<input type="hidden" name="rv_num" value="${param.rv_num }">
	<input type="hidden" name="ref" value="${param.ref }">
	<input type="hidden" name="lev" value="${param.lev }">
	<input type="hidden" name="step" value="${param.step }">
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