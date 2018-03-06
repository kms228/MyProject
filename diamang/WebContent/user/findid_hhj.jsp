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
		var id1=document.getElementById("id");
		var id2=document.getElementById("");
		var name=document.frm.name;
		var email=document.frm.email;
		
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
<h1>아이디를 찾아봅시다.</h1>
<form method="post" name="frm" onsubmit="return find()" action="<%=request.getContextPath()%>/JoinController.do?cmd=findid" >
<P><label for="name">이름</label><input type="text" name="name" id="name"></P>
<P><label for="email">이메일</label><input type="text" name="email" id="email"></P>
<input type="submit" id="but"; value="확인">
<input type="reset" id="but"; value="취소">

</form>


<c:choose>

<c:when test="${id=='' &&  id1==id2}">
<h2>회원님의 아이디를 찾지 못하였습니다. </h2>
<a href="<%=request.getContextPath()%>/user/join_hhj.jsp"></a>
</c:when>

<c:when test="${id!=null}">
<h2>회원님의 아이디 </h2> <label for="id">${id}</label>
<a href="<%=request.getContextPath()%>/move.do?cmd=login">로그인페이지</a>
</c:when>


</c:choose>
</div>
</body>
</html>







