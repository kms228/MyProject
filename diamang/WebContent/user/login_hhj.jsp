<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<link rel="stylesheet" type="text/css" href="user/css/user_hhj.css?ver=1">
 
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

<div id="form" class="joinlayout">
<h1>Login</h1>
<a>welcome back!!</a><br><br>

<c:choose>

<c:when test="${empty sessionScope.id }">

<form method="post" name="frm" onsubmit="return enter()" action="<%=request.getContextPath()%>/JoinController.do?cmd=login">
<table>

<tr>
<th>ID</th> <td><input type="text" name="id" id="id" onclick="login()"></td>
</tr>

<tr>
<th>PWD</th> <td><input type="password" name="pwd" id="pwd"></td><td><input type="submit" value="Login" id="but"></td>
</tr>
 
</table>
<!-- <input type="checkbox" value="saveid" value="1" &{chk} >아이디저장<br> -->
<a href="<%=request.getContextPath()%>/user/findid_hhj.jsp" style="text-decoration:none">아이디찾기</a>
<a href="<%=request.getContextPath()%>/user/findpwd_hhj.jsp" style="text-decoration:none">비번찾기</a>

</form>
</c:when>
<c:otherwise>

[${id }님 어서오시옵소서.]<br>
<a href="<%=request.getContextPath()%>/JoinController.do?cmd=logout">로그아웃</a>
<a href="<%=request.getContextPath()%>/JoinController.do?cmd=update&id=${id}">정보수정</a>

</c:otherwise>
</c:choose>


</div>
</body>
</html>