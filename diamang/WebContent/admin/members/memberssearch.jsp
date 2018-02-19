<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원정보 조회</h1>
<h2>검색조건설정</h2>
<table border="1" width="100%">
	<tbody>
	<tr>
		<th>개인정보</th><td><select><option>아이디</option><option>이름</option><option>이메일</option></select>&nbsp;&nbsp;<input type="text"></td>
	</tr>
	<tr>
		<th>회원등급</th>
		<td>
			<select>
				<c:forEach var="vo" items="${list }">
				<option>${vo.grade }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>주문상품</th><td><input type="text"><input type="button" value="상품검색"></td>
	</tr>
	</tbody>
	<tfoot>
	<tr>
		<td colspan="4">
		<input type="submit" value="검색">
		</td>
	</tr>
	</tfoot>	
</table>
<h2>회원목록</h2>
<table border="1" width="100%">
	<thead>
		<tr>
			<th>가입일</th><th>이름</th><th>아이디</th><th>등급</th><th>전화번호</th><th>이메일</th><th>주소</th>
		</tr>
	</thead>
</table>
<h2>도움말</h2>
<ul>
	<li>아래 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>아래 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>아래 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
</ul>
</body>
</html>