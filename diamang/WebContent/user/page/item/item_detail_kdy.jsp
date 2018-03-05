<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	#item_main{margin-top:50px;overflow:hidden;height:auto;border-bottom:1px dotted gray;padding-bottom:50px;}
	#imgInfo{float:left;width:50%;}
	#imgInfo img{}
	#orderinfo{width:50%;}
	#box{float:left;}
	#box div{border-bottom: 1px dotted gray;width:300px;margin:10px;padding-bottom:10px;}
	#detail img{margin:auto;}
</style>
<script>
	function order(){
		var frm = document.form;
		frm.method="post";
		frm.action="<%=request.getContextPath()%>/order.do?cmd=orderCheck";
		frm.submit();
	}
	
	function cart(){
		var frm = document.form;
		frm.method="post";
		frm.action="<%=request.getContextPath()%>/cart.do?cmd=cartCheck";
		frm.submit();
	}
</script>
<div id="item_main">
	<div id="imgInfo">
		<img src="<%=request.getContextPath() %>/admin/upload/${vo.savename}" style="width:400px;" alt="대표이미지">
		
	</div>
	<div id="orderInfo">
	<form method="post" action="<%=request.getContextPath()%>/order.do?cmd=orderCheck" name="form">
		<div style="width:50%;float:left;">
		<div id="box">
			<div>
				${vo.item_name}
			</div>
			<div>
				가격 : ${vo.price }
			</div>
			<div>
			
			<input type="number" value="1" step="1" min="1" max="1000" name="amount">
			</div>
		</div>
		</div>
		<div style="float:left;position:relative;margin-top:100px;">
		
		<input type="hidden" value="${vo.pnum }" name="pnum">
		<input type="hidden" value="${vo.savename }" name="savename">
		<input type="hidden" value="${vo.item_name }" name="item_name">
		<input type="hidden" value="${vo.price }" name="price">
		<input type="button" value="구매하기" onclick="order()"><input type="button" value="장바구니" onclick="cart()">
		</div>
		</form>
	</div>
</div>
<div id="detail">
		<img src="<%=request.getContextPath() %>/admin/upload/${vo.detailImg}" alt="상세이미지">
	</div>