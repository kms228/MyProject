<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/admin/css/common_kms.css?ver=1">
<c:if test="${!empty errMsg }">
	<script type="text/javascript">
		alert("${requestScope.errMsg}");
	</script>
</c:if>
</head>

<body>
<%--
	tring spage=request.getParameter("page");
	if(spage==null){
		spage="page/home_kms.jsp";
	}
	
	
--%>

<div id="wrap">	
<c:set var="spage" value="${param.page }"/>
<c:set var="errMsg" value="${param.errMsg }"/>
<c:if test="${empty spage }">
	<c:set var="spage" value="page/home_kms.jsp"/>
</c:if>

	<div id="header">
		<jsp:include page="page/header_kms.jsp"></jsp:include>
	</div>
	<div id="body">
		<jsp:include page="${spage }"></jsp:include>
	</div>
	<div id="footer">
		<jsp:include page="page/footer_kms.jsp"></jsp:include>	
	</div>
</div>
</body>
</html>