<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<style type="text/css">

		p label {width: 70px; display: inline-block;}
		
</style>
 
<script>
	function enter() {
		var id=document.frm.id;
		var pwd=document.frm.pwd;
		if(id.value.length==0){
			alert("아이디를 입력해주세요.");
			id.focus();
			return false;
		}
		if(pwd.value.length==0){
			alert("비밀번호를 입력해주세요");
			pwd.focus();
			return false;
		}
	}

</script>
<html>
<head>
</head>
<body>

<div>
<h1>Login</h1>
<a>welcome back</a><br>

<c:choose>
<c:when test="${empty sessionScope.id }">
<form method="post" name="frm" onsubmit="return enter()" action="<%=request.getContextPath()%>/JoinController.do?cmd=login">
<p><label for="id">ID </label><input type="text" name="id" id="id" onclick="login()"></p>
<p><label for="pwd">
PWD </label><input type="password" name="pwd" id="pwd" onclick="pwd()"> <input type="submit" onclick="enter()" value="Login"></p>
<input type="checkbox" value="saveid" >아이디저장
</c:when>
<c:otherwise>

[${id }님 어서오시옵소서.]<br>
<a href="<%=request.getContextPath()%>/JoinController.do?cmd=logout">로그아웃</a>

</c:otherwise>
</c:choose>


</form>
</div>
</body>
</html>