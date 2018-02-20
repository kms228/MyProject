<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/admin/board?cmd=boardnotice">공지사항 관리</a>
<a href="<%=request.getContextPath()%>/admin/board?cmd=boardqna">qna 관리</a>
<a href="<%=request.getContextPath()%>/admin/board?cmd=boardreview">후기게시판 관리</a>
</body>
</html>