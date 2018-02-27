<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/admin/css/common_kms.css?ver=1">
<script type="text/javascript">
	window.onload = function(){
		var msg = "${msg}";
		if(!(msg==="")){
			self.close();
			window.opener.alert(msg);
			window.opener.location.href="/diamang/admin/members.do?cmd=membersgrade";
		}		
	}
</script>
</head>
<body>
	<h2>회원등급 추가</h2>
	<hr>
	<form method="post" action="<%=request.getContextPath()%>/admin/members.do?cmd=addgrade">	
	<table border="1">
		<tr>
			<th>등급명</th><td><input type="text" size="15" name="grade"></td>
		</tr>
		<tr>
			<th>할인율</th><td><input type="text" size="15" placeholder="숫자만 입력해주세요." name="drate"></td>
		</tr>
	</table>
	<input class="basicbtn" type="submit" value="등급추가"><input class="basicbtn" type="button"  onclick="self.close()" value="닫기">
	</form>	
</body>
</html>