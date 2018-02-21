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
		var rv_num = <%=request.getAttribute("rv_num")%>
		var commId = <%=(String)session.getAttribute("id")%>;
		var commPwd = "";
		if(commId==null){
			commId = document.getElementById("commId").value;
			commPwd = document.getElementById("commPwd").value;
			if(commId=="" || commPwd==""){
				alert("아이디와 비밀번호를 모두 입력하세요.");
				return;
			}if((/^\s*$/).test(commId) || (/^\s*$/).test(commPwd)){
				alert("공백이 포함될 수 없습니다.");
				return;
			}
		}
		var comments = document.getElementById("comments").value;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=insert;
		var url = "<%=request.getContextPath()%>/comm.do?cmd=insert";
		xhr.open('post',url,true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var params = "rv_num="+rv_num+"&commId="+commId+"&commPwd="+commPwd+"&comments="+comments;
		xhr.send(params);
	}
	function insert(){
		if(xhr.readyState==4 && xhr.status==200){
			var result=xhr.responseText;
			var json = eval('('+result+')');
			if((json.result)=='success'){
				document.getElementById("commId").value="";
				document.getElementById("commPwd").value="";
				document.getElementById("comments").value="";
				//댓글 목록
				getlist();
				
			}else{
				alert("댓글 등록 실패");
			}
		}
	}
	
	var xhr1=null;
	function getlist(){
		xhr1=new XMLHttpRequest();
		xhr1.onreadystatechange=list;
		var url = "<%=request.getContextPath()%>/comm.do?cmd=list&rv_num=<%=request.getAttribute("rv_num")%>";
		xhr1.open('get',url,true);
		xhr1.send();
	}
	
	function list(){
		if(xhr1.readyState==4 && xhr1.status==200){
			var result = xhr1.responseText;
			var json = JSON.parse(result);
			
			var commList = document.getElementById("commList");
			commList.innerHTML="";
			for(var i=0;i<json.list.length;i++){
				var div = document.createElement("div");
				var id = json.list[i].commId;
				var p = document.createElement("p");
				p.style.fontSize="15px";
				p.innerHTML=json.list[i].commId;
				var regdate = json.list[i].regdate;
				var p2 = document.createElement("p");
				p2.style.fontSize="12px";
				p2.style.color="gray";
				p2.innerHTML=regdate;
				var comments = json.list[i].comments;
				var p3 = document.createElement("p");
				p3.style.fontSize="14px";
				p3.innerHTML=comments;
				div.className="comm";
				div.style.border="1px solid gray";
				div.appendChild(p);
				div.appendChild(p2);
				div.appendChild(p3);
				div.innerHTML+="<a href=\"javascript:delete()\">삭제</a>&nbsp;"+
								"<a href=\"javascript:update()\">수정</a>";
				commList.appendChild(div);
			}
		}
	}

</script>
<body onload="getlist()">
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
			아이디<input type="hidden" id="commId">&nbsp;&nbsp;비밀번호<input type="hidden" id="commPwd">
	<c:if test="${empty sessionScope.id }">
			<script>
				var id = document.getElementById("commId");
				id.type="text";
				var pwd = document.getElementById("commPwd");
				pwd.type="password";
			</script>
		</c:if><br>
		<textarea rows="4" cols="120" id="comments"></textarea>
		<input type="button" value="등록" onclick="add()">
	</div>
</div>
</body>
