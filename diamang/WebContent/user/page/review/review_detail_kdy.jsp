<%@page import="user.vo.RvBoardVo_kdy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	#iteminfo{width:100%; height:100px;border:1px solid gray;}
</style>
<script>
	var xhr=null;
	function add(){
		var id = "<%=(String)session.getAttribute("id")%>"
		if(id==null){
			var commId = document.getElementById("commId").value;
			var commPwd = document.getElementById("commPwd").value;
		}
		var comments = document.getElementById("comments").value;
	}

</script>
<div>
	<div id="iteminfo">
	<input type="button" value="상품상세보기">
	</div>
	<div>
		<table>
			<tr>
				<th>제목	</th><td>${vo.title }</td>
			</tr>
			<tr>
				<th>작성자</th><td>${vo.writer }</td>
			</tr>
			<tr>
				<th>작성일</th><td>${vo.regdate }</td>
			</tr>
			<tr>
				<th>조회수</th><td>${vo.hit }</td>
			</tr>
			<tr>
				<td colspan="2">${vo.content }</td>
			</tr>
			<tr>
				<th>첨부파일	</th><td>?</td>
			</tr>
			<tr>
				<th>비밀번호</th><td><input type="password" name="pwd"></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath()%>/review_list.do">목록</a>&nbsp;&nbsp;	
		<a href="<%=request.getContextPath()%>/imgUpload.do?rv_num=${vo.rv_num}&ref=${vo.ref}&lev=${vo.lev}&step=${vo.step}">답글</a>
	</div>
	<div id="commList"></div>
	<div id="commAdd">
	<c:if test="${empty sessionScope.id }">
			아이디<input type="text" id="commId">&nbsp;&nbsp;비밀번호<input type="password" id="commPwd">  
		</c:if><br>
		<textarea rows="4" cols="120" id="comments"></textarea>
		<input type="button" value="등록" onclick="add()">
	</div>
</div>
