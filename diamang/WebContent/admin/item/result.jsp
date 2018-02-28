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
function winclose(){
	opener.parent.location.reload();
	window.close();
}
</script>
<body>
	<div align="center" id="popup">
		<br>
		<h3>수정완료</h3>
		<br>
		<input type="button" value="닫기" class="basicbtn" onclick="winclose()">
		<br>
	</div>
</body>
</html>