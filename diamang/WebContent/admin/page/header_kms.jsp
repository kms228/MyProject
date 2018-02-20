<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
var xhr1=null
function addMenu(){
	xhr1=new XMLHttpRequest();
	xhr1.onreadystatechange=list;
	xhr1.open('get','../../item?cmd=itemMenu',true);
	xhr1.send();
}
function list(){
	if(xhr1.readyState==4 && xhr1.status==200){
		alert("success");
		var menuList = document.getElementById("menuList");
		//기존댓글지우기
		menuList.innerHTML="";
		var xml = xhr1.responseXML;
		var comm = xml.getElementsByTagName("comm");
		var num = xml.getElementsByTagName("num");
		var id = xml.getElementsByTagName("id");
		var comments = xml.getElementsByTagName("comments");		
		for(var i=0;i<comm.length;i++){
			var div = document.createElement("div");
			var html = "<div name='id1'>아이디 : "+ id[i].firstChild.nodeValue+"</div>"+		
					   "<div name='reple1'>댓글 : "+ comments[i].firstChild.nodeValue+"</div>"+						  
					   "<a href='javascript:deletee("+num[i].firstChild.nodeValue+")'>삭제</a><br>"+
					   "<a href='javascript:changeText()'>수정</a>";
			
			div.innerHTML=html;
			div.className="comm";
			commlist.appendChild(div); 

			//num[i].firstChild.nodeValue		
		}

	}
}
</script>
<div>
	<h1 align="center">쇼핑몰관리24</h1>
	<br>
	<a href="#">상점관리</a><span>|&nbsp;</span>
	<a href="#">고객관리</a><span>|&nbsp;</span>
	<a href="javascript:addMenu()" >상품관리</a><span>|&nbsp;</span>
	<a href="#">배송관리</a><span>|&nbsp;</span>
	<a href="#">게시판관리</a>
	<c:choose >
		<c:when test="${empty sessionScope.id }">
			<div id="login">
				<h3>로그인</h3>
				<form action="<c:url value='/login.do?cmd=login'/>" method="post">
					<table>
						<tr><th>아이디</th><td><input type="text" name="id"></td><td id="loginbutton" rowspan="2"><input type="submit" value="로그인"></td></tr>
						<tr><th>비밀번호</th><td><input type="text" name="pwd"></td></tr>	
					</table>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<%-- 로그아웃 --%>
			<div id="logout">
				<div>
					${id }님 환영합니다.
					<a href="<c:url value='/login.do?cmd=logout'/>">로그아웃</a>
				</div> 
			</div>
		</c:otherwise>
	</c:choose>
	<br>
	
</div>