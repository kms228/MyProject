<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
		p label {width: 70px; display: inline-block;}
</style>
</head>
<body>
<h1>Member 정보 수정</h1>

<form method="post" action="<%=request.getContextPath()%>/JoinController.do?cmd=updateOk">
<input type="hidden" value="${user.mnum}" name="mnum">
<p><label for="id">아이디 </label><input type="text" value="${user.id }" name="id" readonly="readonly"></p>
<p><label for="name">이름 </label><input type="text" value="${user.name}" name="name" readonly="readonly"></p>
<p><label for="birthday">생년월일 </label><input type="text" value="${user.birthday }" name="birthday" readonly="readonly"></p>
<p><label for="joindate">가입일 </label><input type="text" value="${user.joindate }" name="joindate" readonly="readonly"></p>
<p><label for="pwd">비밀번호</label><input type="password" name="pwd" id="pwd"></p>
<p><label for="pwd">비밀번호확인</label><input type="password" name="pwd" id="pwd2"></p>
<p><label for="email">이메일</label><input type="text" value="${user.email }" name="email" id="email"></p>
<p><label for="address">주소</label><input type="text" value="${user.address }" name="address" id="address"></p>
<p><label for="phone">휴대폰번호</label><input type="text" value="${user.phone}" name="phone" id="phone"></p>
<input type="submit" value="회원 정보수정">
<input type="reset" value="취소">
</form>

</body>
</html>