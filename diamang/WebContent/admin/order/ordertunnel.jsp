<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/admin/order.do?cmd=prepareproduct">상품준비중</a>
<a href="<%=request.getContextPath()%>/admin/order.do?cmd=shippedend">배송중</a>
<a href="<%=request.getContextPath()%>/admin/order.do?cmd=shippedcomplete">배송완료</a>
<a href="<%=request.getContextPath()%>/admin/order.do?cmd=ordercancel">배송취소</a>
</body>
</html>