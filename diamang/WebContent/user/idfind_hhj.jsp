<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<html>
<script type="text/javascript">
	function find() {
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

<c:choose>
<c:when test="${empty sessionScope.id }">

<h1>아이디를 찾아봅시다.</h1>
<form method="post" name="frm" onsubmit="return find()" action="<%=request.getContextPath()%>/JoinController.do?cmd=find" >
<P><label for="name">이름</label><input type="text" name="name" id="name"></P>
<P><label for="email">이메일</label><input type="text" name="email" id="email"></P>
<input type="submit" value="아이디찾기">
<input type="reset" value="취소">

</form>

</c:when>
</c:choose>


</body>
</html>






