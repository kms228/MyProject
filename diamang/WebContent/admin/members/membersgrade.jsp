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
<h1>회원등급관리</h1>
<h2>회원 등급목록</h2>
<table border="1" width="100%">
	<thead>
	<tr>
		<th>번호</th><th>등급명</th><th>할인율</th><th>회원수</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.gnum }</td><td>${vo.grade }</td><td>${vo.drate }</td><td>${vo.gnumcnt }</td>
		</tr>
	</c:forEach>
	</tbody>
	<tfoot>
		<tr><td colspan="4"><input type="button" value="등급 추가"></td></tr>
	</tfoot>
</table>
<h2>회원가입 시 회원등급 기본설정</h2>
<table border="1" width="100%">
	<thead>
		<tr>
			<th>회원등급 기본설정</th>
			<th>회원가입 시 회원등급을 
				<select>
				<c:forEach var="vo" items="${list }">
					<option>${vo.grade }</option>
				</c:forEach>
				</select>(으)로 설정합니다.
			</th>
		</tr>
	</thead>
</table>
<h2>도움말</h2>
<ul>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
	<li>이 화면은 도움말입니다. 나중에 내용을 적어넣어봐요.</li>
</ul>
</body>
</html>