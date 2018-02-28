<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
var xhr3 = null;
var snum = 0;
function addText(num){
	if(snum==0){
		snum=num;
	}else{
		snum++;
	}
	xhr3 = new XMLHttpRequest();
	xhr3.onreadystatechange=addT;
	xhr3.open('get','<%=request.getContextPath()%>/item?cmd=addtext',true);
	xhr3.send();
}
function addT(){
	if(xhr3.readyState==4 && xhr3.status==200){
		
		var xml=xhr3.responseXML;
		var div = document.getElementById("addText");
		var ipText = document.createElement("input");
		var ipHdn = document.createElement("input");
		var ipSub = document.createElement("input");
		var ipBtn = document.createElement("input");
		var lab = document.createElement("label");
		var br = document.createElement("br");
		
		lab.innerHTML="&nbsp; "+snum+"번째줄";
		
		ipText.setAttribute("value", "");
		ipText.setAttribute("id", "text"+snum);
		ipText.setAttribute("class", "ipText");
		ipText.style.width="70%";
		
		ipHdn.type="hidden";
		ipHdn.setAttribute("value","0");
		ipHdn.setAttribute("name", "");
		
		ipSub.type="button";
		ipSub.value="추가하기"
		ipSub.setAttribute("class", "basicbtn");
		ipSub.setAttribute("id", "btn1");
		ipSub.setAttribute("onclick", "insertText()");
		
		ipBtn.type="button";
		ipBtn.value="줄삭제(-)"
		ipBtn.setAttribute("class", "basicbtn");
		ipSub.setAttribute("onclick", "delLine()");
		
		div.appendChild(lab);
		div.appendChild(ipText);
		div.appendChild(ipHdn);
		div.appendChild(ipSub);
		
		div.appendChild(br)
		
	}
}

function insertText(){
	var insertT = document.getElementById("text"+snum);
	var insertVal = insertT.value;
	location.href="<%=request.getContextPath()%>/store?cmd=insert&info1="+insertVal;
}
function delText(num){
	location.href="<%=request.getContextPath()%>/store?cmd=del&num="+num;
	
}

</script>
<div>
	<h2>상점관리 > 기본정보설정</h2><br>
	<div class="sectionBar">
		<h3>현재설정</h3>
	</div>
	<table>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.info }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div class="sectionBar">
		<h3>수정하기</h3>
	</div>
	
	<form method="post" action="<c:url value='/store?cmd=goChange'/>">
	<c:set var="inNum" value="0"/>
		<c:forEach var="vo" items="${list }" varStatus="idx">
			&nbsp; <label>${idx.index+1 }번째줄</label>
			<c:set var="inNum" value="${idx.index+2 }"/>
			<input style="width: 70%;" type="text" name="info" value="${vo.info }">
			<input type="hidden" name="num" value="${vo.num }">
			<input type="submit" value="수정하기" class="basicbtn">
			<input type="button" value="내용삭제" class="basicbtn" onclick="delText(${vo.num })">
			<br>
		</c:forEach>
		<div id="addText"></div>
		<div align="center">
			<input type="button" value="줄추가(+)" class="basicbtn" onclick="addText(${inNum})">
		</div>
	</form>
</div>