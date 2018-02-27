<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
   
<html>
<style type="text/css">
p label {width: 70px; display: inline-block;}
.joinlayout{margin-left: 300px; margin-top: 5px; width: 525px;padding: 14px;}
#form{border: solid 2px #b7ddf2; background:#ebf4fb;}
#but{
clear:both;
margin-left:10px;
width:90px;
height:30px;
text-align:center;
line-height:31px;
background-color:#000;
color:#FFFFFF;
font-size:13px;
font-weight:bold;
font-family:tahoma;
}
</style>
 
<script type="text/javascript">
	

	function find() {
		var pwd1=document.getElementById("pwd");
		var pwd2=document.getElementById("");
		var id=document.frm.id;
		var name=document.frm.name;
		var email=document.frm.email;
		
		if(id.value.length==0){
			alert("아이디를 입력하세요.");
			name.focus();
			return false;
		}
		if(name.value.length==0){
			alert("이름을 입력하세요.");
			name.focus();
			return false;
		}
		if(email.value.length==0){
			alert("이메일을 입력하세요.");
			email.focus();
			return false;
		}
	}
</script>
<head>
</head>
<body>
<div id="form" class="joinlayout">
<h1>비밀번호찾기</h1>
<form method="post" name="frm" onsubmit="return find()" action="<%=request.getContextPath()%>/JoinController.do?cmd=findpwd" >
<P><label for="id">아이디</label><input type="text" name="id" id="id"></P>
<P><label for="name">이름</label><input type="text" name="name" id="name"></P>
<P><label for="email">이메일</label><input type="text" name="email" id="email"></P>
<input type="submit" id="but"; value="확인">
<input type="reset"  id="but"; value="취소">

</form>


<c:choose>

<c:when test="${pwd=='' && pwd1==pwd2}">
<h2>회원님의 비밀번호를 찾지 못하였습니다. </h2>
<a href="<%=request.getContextPath()%>/user/join_hhj.jsp"></a>
</c:when>

<c:when test="${pwd!=null}">
<h3>회원님의 비밀번호를 이메일로 전송하였습니다. </h3> <!-- <label for="pwd">${pwd} 입니다.</label> --><br>
<a href="<%=request.getContextPath()%>/move.do?cmd=main">로그인페이지</a>
</c:when>


</c:choose>

</div>
</body>
</html>







