<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/admin/css/common_kms.css?ver=1">
</head>
<script type="text/javascript">

function file_change1(file1){	
	var str=file1.lastIndexOf("\\")+1;	//파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
	file1 = file1.substring(str, file1.length);
	document.getElementById('fileName1').value=file1;
}

function file_change2(file2){
	var str=file2.lastIndexOf("\\")+1;	//파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
	file2 = file2.substring(str, file2.length);
	document.getElementById('fileName2').value=file2;
}

var xhr1 = null;
function cSelectbox(val){
	var resultOf = document.getElementById("resultOf");
	console.log(val);
	xhr1 = new XMLHttpRequest();
	if(val=="ring"){
		resultOf.setAttribute("value", "반지 > ");
		xhr1.onreadystatechange=ring;
	}else if(val=="neck"){
		resultOf.setAttribute("value", "목걸이 > ");
		xhr1.onreadystatechange=neck;
	}else if(val=="ear"){
		resultOf.setAttribute("value", "귀걸이 > ");
		xhr1.onreadystatechange=ear;
	}else if(val=="coup"){
		resultOf.setAttribute("value", "커플링 > ");
		xhr1.onreadystatechange=coup;
	
	}else if(val=="sel"){
		resultOf.setAttribute("value", "");
		xhr1.onreadystatechange=sel;
	}
	xhr1.open('get','<%=request.getContextPath()%>/item?cmd=selectRing',true);	
	xhr1.send();
}

function sel(){
	if(xhr1.readyState==4 && xhr1.status==200){
		var xml=xhr1.responseXML;
		var selectbox2 = document.getElementById("selectbox2");
		while ( selectbox2.hasChildNodes() ) {
			selectbox2.removeChild( selectbox2.firstChild ); 
		}
		
		var op1 = document.createElement("option");
		op1.setAttribute("value", "");
		var opText = document.createTextNode("::선택::");
		selectbox2.appendChild(op1).appendChild(opText);
	}
}

function ring(){
	if(xhr1.readyState==4 && xhr1.status==200){
		var xml=xhr1.responseXML;
		var selectbox2 = document.getElementById("selectbox2");
		while ( selectbox2.hasChildNodes() ) {
			selectbox2.removeChild( selectbox2.firstChild ); 
		}
		var op = document.createElement("option");
		op.setAttribute("value", "반지 > ");
		var ot = document.createTextNode("::선택::");
		selectbox2.appendChild(op).appendChild(ot);

		var op1 = document.createElement("option");
		op1.setAttribute("value", "반지 > 14k/18k");
		var opText = document.createTextNode("14k/18k");
		selectbox2.appendChild(op1).appendChild(opText);
		
		var op2 = op1.cloneNode(true);
		op2.setAttribute("value", "반지 > 다이아");
		op2.innerHTML="다이아"
		selectbox2.appendChild(op2);
		
		var op3 = op1.cloneNode(true);
		op3.setAttribute("value", "반지 > 탄생석");
		op3.innerHTML="탄생석"
		selectbox2.appendChild(op3);
		
		var op4 = op1.cloneNode(true);
		op4.setAttribute("value", "반지 > 실버");
		op4.innerHTML="실버"
		selectbox2.appendChild(op4);
	}
}

function neck(){
	if(xhr1.readyState==4 && xhr1.status==200){
		console.log("neck들어옴");
		var xml=xhr1.responseXML;
		var selectbox2 = document.getElementById("selectbox2");
		while ( selectbox2.hasChildNodes() ) {
			selectbox2.removeChild( selectbox2.firstChild ); 
		}
		var op = document.createElement("option");
		op.setAttribute("value", "목걸이 > ");
		var ot = document.createTextNode("::선택::");
		selectbox2.appendChild(op).appendChild(ot);
		
		var op1 = document.createElement("option");
		op1.setAttribute("value", "목걸이 > 14k/18k");
		var opText = document.createTextNode("14k/18k");
		selectbox2.appendChild(op1).appendChild(opText);
		
		var op2 = op1.cloneNode(true);
		op2.setAttribute("value", "목걸이 > 다이아");
		op2.innerHTML="다이아"
		selectbox2.appendChild(op2);
		
		var op3 = op1.cloneNode(true);
		op3.setAttribute("value", "목걸이 > 탄생석");
		op3.innerHTML="탄생석"
		selectbox2.appendChild(op3);
	}
}

function ear(){
	if(xhr1.readyState==4 && xhr1.status==200){
		var xml=xhr1.responseXML;
		var selectbox2 = document.getElementById("selectbox2");
		while ( selectbox2.hasChildNodes() ) {
			selectbox2.removeChild( selectbox2.firstChild ); 
		}
		var op = document.createElement("option");
		op.setAttribute("value", "귀걸이 > ");
		var ot = document.createTextNode("::선택::");
		selectbox2.appendChild(op).appendChild(ot);
		
		var op1 = document.createElement("option");
		op1.setAttribute("value", "귀걸이 > 14k/18k");
		var opText = document.createTextNode("14k/18k");
		selectbox2.appendChild(op1).appendChild(opText);
		
		var op3 = op1.cloneNode(true);
		op3.setAttribute("value", "귀걸이 > 탄생석");
		op3.innerHTML="탄생석"
		selectbox2.appendChild(op3);
	}
}

function coup(){
	if(xhr1.readyState==4 && xhr1.status==200){
		var xml=xhr1.responseXML;
		var selectbox2 = document.getElementById("selectbox2");
		while ( selectbox2.hasChildNodes() ) {
			selectbox2.removeChild( selectbox2.firstChild ); 
		}
		var op = document.createElement("option");
		op.setAttribute("value", "커플링 > ");
		var ot = document.createTextNode("::선택::");
		selectbox2.appendChild(op).appendChild(ot);
		
		var op1 = document.createElement("option");
		op1.setAttribute("value", "커플링 > 14k/18k");
		var opText = document.createTextNode("14k/18k");
		selectbox2.appendChild(op1).appendChild(opText);
		
		var op2 = op1.cloneNode(true);
		op2.setAttribute("value", "커플링 > 다이아");
		op2.innerHTML="다이아"
		selectbox2.appendChild(op2);
		
		var op4 = op1.cloneNode(true);
		op4.setAttribute("value", "커플링 > 실버");
		op4.innerHTML="실버"
		selectbox2.appendChild(op4);
	}
}

function naming(val){
	console.log(val);
	var resultOf = document.getElementById("resultOf");
	resultOf.setAttribute("value", val);
	
	var fieldnum = document.getElementById("fieldnum");
	var sfieldnum=0;
	if(val=="반지 > 14k/18k"){
		fieldnum.setAttribute("value", 11);
		
	}else if(val=="반지 > 다이아"){
		fieldnum.setAttribute("value", 12);
		
	}else if(val=="반지 > 탄생석"){
		fieldnum.setAttribute("value", 13);
		
	}else if(val=="반지 > 실버"){
		fieldnum.setAttribute("value", 14);
		
	}else if(val=="목걸이 > 14k/18k"){
		fieldnum.setAttribute("value", 21);
		
	}else if(val=="목걸이 > 다이아"){
		fieldnum.setAttribute("value", 22);
		
	}else if(val=="목걸이 > 탄생석"){
		fieldnum.setAttribute("value", 23);
		
	}else if(val=="귀걸이 > 14k/18k"){
		fieldnum.setAttribute("value", 31);
		
	}else if(val=="귀걸이 > 탄생석"){
		fieldnum.setAttribute("value", 32);
		
	}else if(val=="커플링 > 14k/18k"){
		fieldnum.setAttribute("value", 41);
		
	}else if(val=="커플링 > 다이아"){
		fieldnum.setAttribute("value", 42);
		
	}else if(val=="커플링 > 실버"){
		fieldnum.setAttribute("value", 43);
	}
	console.log(fieldnum.value);
	
}

</script>
<body>
<div id="openWrap">
	<div class="sectionBar" align="center">
		<br>
		<h3>상품 정보</h3>
		<br>
	</div>
	<form onsubmit="return confirm('수정하시겠습니까?');" method="post" action="<c:url value='/item?cmd=update'/>" enctype="multipart/form-data" >
		<c:if test="${vo.fieldnum eq 11}"><c:set var="fnum" value="반지 > 14k/18k"/></c:if>
		<c:if test="${vo.fieldnum eq 12}"><c:set var="fnum" value="반지 > 다이아"/></c:if>
		<c:if test="${vo.fieldnum eq 13}"><c:set var="fnum" value="반지 > 탄생석"/></c:if>
		<c:if test="${vo.fieldnum eq 14}"><c:set var="fnum" value="반지 > 실버"/></c:if>
		<c:if test="${vo.fieldnum eq 21}"><c:set var="fnum" value="목걸이 > 14k/18k"/></c:if>
		<c:if test="${vo.fieldnum eq 22}"><c:set var="fnum" value="목걸이 > 다이아"/></c:if>
		<c:if test="${vo.fieldnum eq 23}"><c:set var="fnum" value="목걸이 > 탄생석"/></c:if>
		<c:if test="${vo.fieldnum eq 31}"><c:set var="fnum" value="귀걸이 > 14k/18k"/></c:if>
		<c:if test="${vo.fieldnum eq 32}"><c:set var="fnum" value="귀걸이 > 탄생석"/></c:if>
		<c:if test="${vo.fieldnum eq 41}"><c:set var="fnum" value="커플링 > 14k/18k"/></c:if>
		<c:if test="${vo.fieldnum eq 42}"><c:set var="fnum" value="커플링 > 다이아"/></c:if>
		<c:if test="${vo.fieldnum eq 43}"><c:set var="fnum" value="커플링 > 실버"/></c:if>
		<table border="1" id="opentable">
			<tr>
				<th>상품분류</th>
				<td colspan="2">
					<input type="text" value="${fnum }" readonly="readonly" id="resultOf">
					<input type="hidden" name="fieldnum" id="fieldnum" value="${vo.fieldnum }">
					<span style="font-size: small;">대분류</span>
					<select onchange="cSelectbox(this.value)" id="selectbox1">
						<option value="sel">::선택::</option>
						<option value="ring">반지</option>
						<option value="neck">목걸이</option>
						<option value="ear">귀걸이</option>
						<option value="coup">커플링</option>
					</select>
					<span style="font-size: small;">중분류</span>
					<select id="selectbox2" onchange="naming(this.value)">
						<option value="">::선택::</option>
					</select>
				</td>
			</tr>
			<tr>	
				<th>상품번호</th><td colspan="2"><input type="text" name="pnum" value="${vo.pnum }" readonly="readonly"><span style="font-size: small;">수정불가</span></td>
			</tr>
			<tr>
				<th>상품이름</th>
				<td colspan="2" id="p_name">
					<input type="text" name="item_name" value="${vo.item_name }" placeholder="예시) 18k 1g 정수 심플링 반지" >				
				</td>
			</tr>
			<tr class="filebox">
				<th>대표이미지</th>
				<td>
					<input type="text" class="filename" value="${img1vo.savefilename }" id="fileName1" readonly="readonly" >
					<label for="file1" class="btn_label">파일찾기</label>
					<input type="file" id="file1" name="file1" class="realfile" onchange="javascript:file_change1(this.value);">
					<input type="hidden" name="delFile1" value="${img1vo.savefilename} ">
				</td>
			</tr>
			<tr class="filebox">
				<th>상세이미지</th>
				<td>
					<input type="text" class="filename" value="${img2vo.savefilename }" id="fileName2" readonly="readonly">
					<label for="file2" class="btn_label">파일찾기</label>
					<input type="file" id="file2" name="file2" class="realfile" onchange="javascript:file_change2(this.value);">
					<input type="hidden" name="delFile2" value="${img2vo.savefilename} ">
				</td>
			</tr>
			<tr>
				<th>재고</th><td colspan="2"><input type="text" value="${vo.stock }" name="stock" placeholder="예시) 5"><span style="font-size: small;">EA</span></td>
			</tr>
			<tr>
				<th>가격</th><td colspan="2"><input type="text" value="${vo.price }" name="price" placeholder="예시) 50000"><span style="font-size: small;">KRW</span></td>
			</tr>
			<tr>
				<th>등록일</th><td colspan="2"><input type="text" value="${vo.regdate }" readonly="readonly"><span style="font-size: small;">(수정된 날짜로 자동변환됩니다.)</span></td>
			</tr>
		</table>
		<br>
		<div align="center">
			<input type="submit" value="수정" class="basicbtn">
		</div>
		<br>
	</form>
</div>
</body>

</html>