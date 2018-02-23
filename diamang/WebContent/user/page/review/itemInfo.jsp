<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#menu p{display:inline;margin:50px;}
	#list p{display: inline-block;margin:50px;}
	#list span{display:block;}
</style>
</head>
<script type="text/javascript">
	function send(event){
		var div = event.target; //버튼 가져오기
		var img =window.opener.document.getElementById("img"); //부모창에서 이미지 들어갈 곳의 div 가져오기
		while(img.hasChildNodes()){ // 다시 선택 시 img div의 자식 요소 지우고 시작
			img.removeChild(img.firstChild);
		}
		var info = window.opener.document.getElementById("info"); //부모창에서 정보 들어갈 곳의 div 가져오기
		var hidePnum=window.opener.document.getElementsByName("hidPnum")[0]; //부모창에서 상품 번호 저장할 히든 input 가져오기
		while(info.hasChildNodes()){
			info.removeChild(info.firstChild);
		}
		
		var pimg = window.opener.document.createElement("p"); //이미지 들어갈 곳의 p
		var pitem_name = window.opener.document.createElement("p"); //상품 이름 들어갈 곳의 p
		var pprice = window.opener.document.createElement("p"); //가격 들어갈 곳의 p
		var pbutton = window.opener.document.createElement("p"); //버튼 들어갈 곳의 p
		
		var value= div.parentNode.parentElement.firstChild.nextSibling.innerHTML; //상품 이미지 
		var value2 = div.parentNode.parentElement.firstChild.nextSibling.nextElementSibling.firstChild.innerHTML; //상품 이름
		var value3 = div.parentNode.parentElement.firstChild.nextSibling.nextElementSibling.firstChild.nextSibling.innerHTML; //가격
		var pnum = div.parentNode.firstChild.value; //상품 번호
		var button1 = window.opener.document.createElement("input"); //버튼 만들기
		
		//속성 지정
		button1.type="button";
		button1.value="상품상세보기"
		pimg.innerHTML=value; 
		pitem_name.innerHTML=value2;
		pprice.innerHTML = value3;
		hidePnum.value=pnum;
		
		//부모창에 있는 태그에 붙이기
		img.appendChild(pimg);
		pbutton.appendChild(button1);
		info.appendChild(pitem_name);
		info.appendChild(pprice);
		info.appendChild(pbutton);
		
		//window.close();
	}
</script>
<body>
	<h2>Product Info</h2>
	<c:if test="${empty sessionScope.id }">
	</c:if>
	<div id="menu">
		<p>상품 이미지</p>
		<p>상품 정보</p>
		<p>상품 선택</p>
	</div>
	<div id="list">
	<c:forEach var="vo" items="${list }">
		<div>
		<p>${vo.savename }</p>
		<p><span>${vo.item_name }</span><span>${vo.price }</span></p>
		<p><input type="hidden" value="${vo.pnum }" name="pnum">
		<input type="button" value="선택" onclick="send(event)"></p>
		</div>
	</c:forEach>
	</div>
</body>
</html>