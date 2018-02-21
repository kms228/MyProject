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
	
}
function ring(){
	if(xhr1.readyState==4 && xhr1.status==200){
	//	alert("success");
		var xml=xhr1.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var a = document.createElement("a");
		a.href="#";
	    var aText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(a).appendChild(aText);
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<a href='#2'>다이아</a>";
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<a href='#3'>탄색성</a>";
	    listOf.appendChild(li3);
	    
	    var li4 = li1.cloneNode(true);
	    li4.innerHTML="<a href='#4'>실버</a>";
	    listOf.appendChild(li4);
	}
}

var xhr2 = null;
function selectNeck(){
	xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange=neck;
	xhr2.open('get','<%=request.getContextPath()%>/item?cmd=selectNeck',true);
	xhr2.send();
}
function neck(){
	if(xhr2.readyState==4 && xhr2.status==200){
	//	alert("success");
		var xml=xhr2.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var a = document.createElement("a");
		a.href="#";
	    var aText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(a).appendChild(aText);
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<a href='#2'>다이아</a>";
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<a href='#3'>탄생석</a>";
	    listOf.appendChild(li3);
	    
	}
}

var xhr3 = null;
function selectEar(){
	xhr3 = new XMLHttpRequest();
	xhr3.onreadystatechange=ear;
	xhr3.open('get','<%=request.getContextPath()%>/item?cmd=selectEar',true);
	xhr3.send();
}
function ear(){
	if(xhr3.readyState==4 && xhr3.status==200){
	//	alert("success");
		var xml=xhr3.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var a = document.createElement("a");
		a.href="#";
	    var aText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(a).appendChild(aText);
	    
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<a href='#2'>탄생석</a>";
	    listOf.appendChild(li2);
	}
}

var xhr4 = null;
function selectCoup(){
	xhr4 = new XMLHttpRequest();
	xhr4.onreadystatechange=coup;
	xhr4.open('get','<%=request.getContextPath()%>/item?cmd=selectCoup',true);
	xhr4.send();
}
function coup(){
	if(xhr4.readyState==4 && xhr4.status==200){
	//	alert("success");
		var xml=xhr4.responseXML;
		var listOf = document.getElementById("listOf");
		listOf.innerHTML="";
		
		var li1 = document.createElement("li");
		var a = document.createElement("a");
		a.href="#";
	    var aText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(a).appendChild(aText);
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<a href='#2'>다이아</a>";
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<a href='#3'>실버</a>";
	    listOf.appendChild(li3);
	}
}
</script>
<h1>상품관리 > 상품등록</h1>
<!-- 기본정보/상품명/상세설명 -->
<div id="qa1" class="section">
	
	<div class="sectionBar">
		<h2>기본 정보</h2>
	</div>
	<div class="sectionArea">
		<table border="1">
			<tbody>
				<tr>
					<th scope="row">상품명</th>
					<td><input type="text" name="item_name" placeholder="예시) 18k 1g 정수 심플링 반지" style="width: 340px;"></td>
				</tr>
				
			</tbody>
		</table>
	</div>
</div>
<!--  판매정보/판매가격 -->
<div id="qa2" class="section">
	<div class="sectionBar">
		<h2>판매 정보</h2>
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
	<div class="sectionBar">
		<h2>표시 정보</h2>
	</div>
	<div class="sectionArea">
		<table border="1">
			<tbody>
				<tr>
					<th scope="row">상품 분류</th>
					<td>
					<div class="searchSelect">
						<table border="1">
							<colgroup>
								<col style="width:40%" span="2">
							</colgroup>
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
					</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- 이미지정보/상품이미지등록 -->
<div id="qa4" class="section">
	<div class="sectionBar">
		<h2>이미지 정보</h2>
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