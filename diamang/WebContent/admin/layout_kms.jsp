<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common_kdy.css">
</head>
<body>
<%
	String spage=request.getParameter("page");
	if(spage==null){
		spage="home_kms.jsp";
	}
%>
<div id="wrap">
	<div id="header">
		<jsp:include page="page/header_kms.jsp"></jsp:include>
	</div>
	<div id="body">
		<jsp:include page="<%=spage %>"></jsp:include>
	</div>
	<div id="footer">
		<jsp:include page="page/footer_kms.jsp"></jsp:include>	
	</div>
</div>
</body>
</html>