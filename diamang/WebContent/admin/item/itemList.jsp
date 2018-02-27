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
	resultOf3.innerHTML="(중분류)"+name;
	var fieldnum = document.getElementById("fieldnum");
	fieldnum.setAttribute("value", sfieldnum);
}
var newWindow;
function newPage(pnum){
	newWindow = window.open("<%=request.getContextPath()%>/item?cmd=detail&pnum="+pnum, "", "width=600, height=580, left=500, top=100,scrollbars=1, location=1");
}

function del(pnum,fieldnum){
	var con = confirm("삭제하시겠습니까?");
	if(con==true){
		console.log(con);
		location.href="<%=request.getContextPath()%>/item?cmd=del&pnum="+pnum+"&fieldnum="+fieldnum;
	}
}

</script>

<form method="post" action="<c:url value='/item?cmd=listOk'/>">
	<input type="hidden" name="fieldnum" id="fieldnum">
	<h2>상품관리 > 상품수정</h2><br>
	<!-- 기본정보/상품명/상세설명 -->
	
	<!--  판매정보/판매가격 -->
	
	<!-- 표시설정/상품분류 -->
	<div id="qa3" class="section">
		<div class="sectionBar"><br>
			<h3>상품 찾기</h3>
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
								<span><input type="submit" value="찾기" class="basicbtn"></span>
							</div>
						</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 이미지정보/상품이미지등록 -->
	
	<br>
	
</form>
<br>
<div class="sectionBar"><br>	
	<h3>상품 리스트</h3>
</div>
<c:set var="spage" value="${page }"/>
<jsp:include page="${spage }"></jsp:include>
