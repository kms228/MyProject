<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/admin/css/common_kms.css?ver=1">
</head>
<script type="text/javascript">
	window.onload = function(){
		var msg = "${msg}";
		if(!(msg==="")){
			self.close();			
			window.opener.location.href="/diamang/admin/members.do?cmd=membersgrade";
		}		
	}
</script>
<body>
	<h2>회원등급 수정</h2>
	<hr>
	<form method="post" action="<%=request.getContextPath()%>/admin/members.do?cmd=updateGrade">
	<input type="hidden" name="gnum" value="${grade.gnum }">
	<table border="1">
		<tr>
			<th>등급명</th><td><input type="text" size="15" id="grade" name="grade" value="${grade.grade }"></td>
		</tr>
		<tr>
			<th>할인율</th><td><input type="text" size="15" name="drate" value="${grade.drate }"></td>
		</tr>
	</table>
	<input class="basicbtn" type="submit" value="등급수정"><input class="basicbtn" type="button"  onclick="self.close()" value="닫기">
	</form>	
</body>
</html>