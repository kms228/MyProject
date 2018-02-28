<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function(){
		var msg = "${msg}";
		if(!(msg==="")){
			self.close();
			var url = "<%=request.getContextPath()%>/admin/board.do?cmd=boardqna";
			window.opener.location.href=url;
		}		
	}
</script>
</head>
<body>
<form method="post"  action="<%=request.getContextPath()%>/admin/board.do?cmd=fillupQna">
<input type="hidden" name="refer" value="${param.refer }">
<input type="hidden" name="qnum" value="${param.qnum }">
<input type="hidden" name="lev" value="${param.lev }">
<input type="hidden" name="step" value="${param.step }">
<table>
	<tbody>
	<tr>
		<th>제목</th><td><input type="text" size="30" name="title"></td>		
	</tr>
	<tr>
		<th>내용</th><td><textarea rows="15" cols="40" name="content"></textarea></td>
	</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2"><input type="submit" value="답변작성"></td>		
		</tr>		
	</tfoot>
</table>
</form>
</body>
</html>