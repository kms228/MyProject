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
		
		li1.setAttribute("onclick", "naming('14k/18k',11)");
		var li1Text = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(li1Text);
	    
		
	    var li2 = li1.cloneNode(true);
	    li2.setAttribute("onclick", "naming('다이아',12)");
	    li2.innerHTML="다이아"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.setAttribute("onclick", "naming('탄생석',13)");
	    li3.innerHTML="탄생석"
	    listOf.appendChild(li3);
	    
	    var li4 = li1.cloneNode(true);
	    li4.setAttribute("onclick", "naming('실버',14)");
	    li4.innerHTML="실버"
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
		li1.setAttribute("onclick", "naming('14k/18k',21)");
		var li1Text = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(li1Text);
		
	    var li2 = li1.cloneNode(true);
	    li2.setAttribute("onclick", "naming('다이아',22)");
	    li2.innerHTML="다이아"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.setAttribute("onclick", "naming('탄생석',23)");
	    li3.innerHTML="탄생석"
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
		li1.setAttribute("onclick", "naming('14k/18k',31)");
		var li1Text = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(li1Text);
	    
	    var li2 = li1.cloneNode(true);
	    li2.setAttribute("onclick", "naming('탄생석',32)");
	    li2.innerHTML="탄생석"
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
		li1.setAttribute("onclick", "naming('14k/18k',41)");
		var li1Text = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(li1Text);
		
	    var li2 = li1.cloneNode(true);
	    li2.setAttribute("onclick", "naming('다이아',42)");
	    li2.innerHTML="다이아"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.setAttribute("onclick", "naming('실버',43)");
	    li3.innerHTML="실버"
	    listOf.appendChild(li3);
	}
}
function naming(name,sfieldnum){
	var resultOf3 = document.getElementById("resultOf3");
	resultOf3.innerHTML="";
	resultOf3.innerHTML=name;
	var fieldnum = document.getElementById("fieldnum");
	fieldnum.setAttribute("value", sfieldnum);
	//console.log(sfieldnum);
}

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

var xhr5 = null;
function itemcheck() {
	var item_name = document.getElementsByName("item_name")[0].value;
	xhr5=new XMLHttpRequest();
	xhr5.onreadystatechange=callback1;
	xhr5.open("get","item/itemNameCheck.jsp?item_name="+item_name,true);
	xhr5.send();
	
}
function callback1(){
	if(xhr5.readyState==4 && xhr5.status==200){
		var result=xhr5.responseText;
		var json=eval('('+result+')');
		var itemSpan=document.getElementById("itemSpan");
		console.log(json.using);
		if(json.using==true){
			itemSpan.innerHTML="상품등록불가 : 중복된 상품명입니다.";
			return false;
		}else{
			itemSpan.innerHTML="등록가능한 상품명입니다.";
		}
	}
}
function subm(){
	var fieldnum = document.getElementById("fieldnum").value;
	if(fieldnum!=null && fieldnum!=""){
		alert("상품등록성공");
		return true;
	}else{	
		alert("상품분류확인하세요.");
		return false;
	}
	
	
}

</script>

<form onsubmit="return subm();" method="post" action="<c:url value='/item?cmd=itemInsert'/>" enctype="multipart/form-data">
	<input type="hidden" name="fieldnum" id="fieldnum">
	<h2>상품관리 > 상품등록</h2><br>
	<!-- 기본정보/상품명/상세설명 -->
	<div id="qa1" class="section">
		
		<div class="sectionBar">
			<h3>기본 정보</h3>
		</div>
		<div class="sectionArea">
			<table border="1" id="insertTable">
				<tbody>
					<tr>
						<th scope="row">상품명</th>
						<td>
							<input type="text" name="item_name"  placeholder="예시) 18k 1g 정수 심플링 반지" style="width: 340px;" onkeyup="itemcheck()" required="required">
							<span id="itemSpan" style="font-size: 12px;color:red"></span>
						</td>
					</tr>
					<tr>
						<th scope="row">수량</th>
						<td><input type="text" name="stock" placeholder="예시) 8"required="required">EA</td>
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
						<td><input type="text" name="price" placeholder="예시) 55000" style="width: 140px;"required="required">KRW</td>
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
		<div class="sectionArea2">
			<table border="1">
				<tbody>
					<tr>
						<th scope="row">상품 분류</th>
						<td>
						<div class="searchSelect">
							<table border="1">
								<thead>
									<tr>
										<th scope="col" class="dd">대분류</th>
										<th scope="col" class="dd">중분류</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="list">
												<ul>
												<!-- li{list-style:none} -->
													<li onclick="selectRing()">반&nbsp;&nbsp;&nbsp;지 ></li>
													<li onclick="selectNeck()">목걸이 ></li>	
													<li onclick="selectEar()">귀걸이 ></li>
													<li onclick="selectCoup()">커플링 ></li>
													
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
					<tr class="filebox">
						<th>대표 이미지</th>
						<td><input type="text" class="filename" id="fileName1" readonly="readonly" >
					<label for="file1" class="btn_label">파일찾기</label>
					<input type="file" id="file1" name="file1" class="realfile" onchange="javascript:file_change1(this.value);"required="required"></td>
					</tr>
					<tr class="filebox">
						<th>상세 이미지</th>
						<td><input type="text" class="filename" id="fileName2" readonly="readonly">
					<label for="file2" class="btn_label">파일찾기</label>
					<input type="file" id="file2" name="file2" class="realfile" onchange="javascript:file_change2(this.value);"required="required"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<div align="center"><input type="submit" value="등록" class="basicbtn"> <input type="reset" value="취소"  class="basicbtn"></div>
</form>