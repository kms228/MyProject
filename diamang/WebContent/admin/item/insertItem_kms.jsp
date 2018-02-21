<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
var xhr1 = null;
function selectRing(){
	xhr1 = new XMLHttpRequest();
	xhr1.onreadystatechange=ring;
	xhr1.open('get','<%=request.getContextPath()%>/item?cmd=selectRing',true);	
	xhr1.send();
	var resultOf2 = document.getElementById("resultOf2");
	var resultOf3 = document.getElementById("resultOf3");
	resultOf3.innerHTML="";
	resultOf2.innerHTML="";
	resultOf2.innerHTML="(대분류)반지 > ";
	
}
function ring(){
	if(xhr1.readyState==4 && xhr1.status==200){
	//	alert("success");
		var resultOf = document.getElementById("resultOf");
		var xml=xhr1.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var label = document.createElement("label");
		label.setAttribute("name", "111");
		label.setAttribute("onclick", "naming('14k/18k')");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
	    
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label name='222' onclick='naming(\"다이아\")'>다이아</label>"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<label name='333' onclick='naming(\"탄생석\")'>탄생석</label>"
	    listOf.appendChild(li3);
	    
	    var li4 = li1.cloneNode(true);
	    li4.innerHTML="<label name='333' onclick='naming(\"실버\")'>실버</label>"
	    listOf.appendChild(li4);
	}
}

var xhr2 = null;
function selectNeck(){
	xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange=neck;
	xhr2.open('get','<%=request.getContextPath()%>/item?cmd=selectNeck',true);
	xhr2.send();
	var resultOf2 = document.getElementById("resultOf2");
	var resultOf3 = document.getElementById("resultOf3");
	resultOf3.innerHTML="";
	resultOf2.innerHTML="";
	resultOf2.innerHTML="(대분류)목걸이 > ";
}
function neck(){
	if(xhr2.readyState==4 && xhr2.status==200){
	//	alert("success");
		var xml=xhr2.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var label = document.createElement("label");
		label.setAttribute("name", "111");
		label.setAttribute("onclick", "naming('14k/18k')");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label name='222' onclick='naming(\"다이아\")'>다이아</label>"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<label name='333' onclick='naming(\"탄생석\")'>탄생석</label>"
	    listOf.appendChild(li3);
	    
	}
}

var xhr3 = null;
function selectEar(){
	xhr3 = new XMLHttpRequest();
	xhr3.onreadystatechange=ear;
	xhr3.open('get','<%=request.getContextPath()%>/item?cmd=selectEar',true);
	xhr3.send();
	var resultOf2 = document.getElementById("resultOf2");
	var resultOf3 = document.getElementById("resultOf3");
	resultOf3.innerHTML="";
	resultOf2.innerHTML="";
	resultOf2.innerHTML="(대분류)귀걸이 > ";
}
function ear(){
	if(xhr3.readyState==4 && xhr3.status==200){
	//	alert("success");
		var xml=xhr3.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var label = document.createElement("label");
		label.setAttribute("name", "111");
		label.setAttribute("onclick", "naming('14k/18k')");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
	    
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label name='333' onclick='naming(\"탄생석\")'>탄생석</label>"
	    listOf.appendChild(li2);
	}
}

var xhr4 = null;
function selectCoup(){
	xhr4 = new XMLHttpRequest();
	xhr4.onreadystatechange=coup;
	xhr4.open('get','<%=request.getContextPath()%>/item?cmd=selectCoup',true);
	xhr4.send();
	var resultOf2 = document.getElementById("resultOf2");
	resultOf2.innerHTML="";
	resultOf2.innerHTML="(대분류)커플링 > ";
}
function coup(){
	if(xhr4.readyState==4 && xhr4.status==200){
	//	alert("success");
		var xml=xhr4.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var label = document.createElement("label");
		label.setAttribute("name", "111");
		label.setAttribute("onclick", "naming('14k/18k')");
		//label.onclick=naming("14k/18k");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label name='222' onclick='naming(\"다이아\")'>다이아</label>"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<label name='333' onclick='naming(\"실버\")'>실버</label>"
	    listOf.appendChild(li3);
	}
}
function naming(name){
	var resultOf3 = document.getElementById("resultOf3");
	resultOf3.innerHTML="";
	resultOf3.innerHTML=name;
}
</script>
<form method="post" action="<c:url value='/item?cmd=insertOk'/>" enctype="multipart/form-data">
<h1>상품관리 > 상품등록</h1><br>
<!-- 기본정보/상품명/상세설명 -->
<div id="qa1" class="section">
	
	<div class="sectionBar">
		<h3>기본 정보</h3>
	</div>
	<div class="sectionArea">
		<table border="1">
			<tbody>
				<tr>
					<th scope="row">상품명</th>
					<td><input type="text" name="item_name"  placeholder="예시) 18k 1g 정수 심플링 반지" style="width: 340px;"></td>
				</tr>
				<tr>
					<th scope="row">수량</th>
					<td><input type="text" name="stock" placeholder="예시) 8">EA</td>
				</tr>	
			</tbody>
		</table>
	</div>
</div>
<!--  판매정보/판매가격 -->
<div id="qa2" class="section">
	<div class="sectionBar"><br>
		<h3>판매 정보</h3>
	</div>
	<div class="sectionArea">
		<table border="1">
			<tbody>
				<tr>
					<th scope="row">판매 가격</th>
					<td><input type="text" name="item_name" placeholder="예시) 55000" style="width: 140px;">KRW</td>
				</tr>
				
			</tbody>
		</table>
	</div>
</div>
<!-- 표시설정/상품분류 -->
<div id="qa3" class="section">
	<div class="sectionBar"><br>
		<h3>표시 정보</h3>
	</div>
	<div class="sectionArea">
		<table border="1">
			<tbody>
				<tr>
					<th scope="row">상품 분류</th>
					<td>
					<div class="searchSelect">
						<table border="1">
							
							<thead>
								<tr>
									<th scope="col">대분류</th>
									<th scope="col">중분류</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div class="list">
											<ul>
											<!-- li{list-style:none} -->
												<li><a href="javascript:selectRing();">반지 ></a></li>
												<li><a href="javascript:selectNeck();">목걸이 ></a></li>	
												<li><a href="javascript:selectEar();">반지 ></a></li>
												<li><a href="javascript:selectCoup();">커플링 ></a></li>
											</ul>
										</div>
									</td>
									<td>
										<div class="list">
											<ul id="listOf">
												
											</ul>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<div id="resultOf1"><span style="font-weight: bold"> 선택된 상품분류 </span>
							<span id="resultOf2"></span><span id="resultOf3"></span>
						</div>
					</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- 이미지정보/상품이미지등록 -->
<div id="qa4" class="section">
	<div class="sectionBar"><br>
		<h3>이미지 정보</h3>
	</div>
	<div class="sectionArea">
		<table border="1">
			<tbody>
				<tr>
					<th>대표 이미지</th>
					<td><input type="file"></td>
				</tr>
				<tr>
					<th>상세 이미지</th>
					<td><input type="file"></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<br>
<div align="center"><input type="submit" value="등록"> <input type="reset" value="취소"></div>
</form>