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
		label.setAttribute("onclick", "naming('14k/18k',11)");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
	    
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label onclick='naming(\"다이아\",12)'>다이아</label>"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<label onclick='naming(\"탄생석\",13)'>탄생석</label>"
	    listOf.appendChild(li3);
	    
	    var li4 = li1.cloneNode(true);
	    li4.innerHTML="<label onclick='naming(\"실버\",14)'>실버</label>"
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
		label.setAttribute("onclick", "naming('14k/18k',21)");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label onclick='naming(\"다이아\",22)'>다이아</label>"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<label onclick='naming(\"탄생석\",23)'>탄생석</label>"
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
		label.setAttribute("onclick", "naming('14k/18k',31)");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
	    
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label onclick='naming(\"탄생석\",32)'>탄생석</label>"
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
		label.setAttribute("onclick", "naming('14k/18k',41)");
		//label.onclick=naming("14k/18k");
		var labelText = document.createTextNode("14k/18k");
	    listOf.appendChild(li1).appendChild(label).appendChild(labelText);
		
	    var li2 = li1.cloneNode(true);
	    li2.innerHTML="<label onclick='naming(\"다이아\",42)'>다이아</label>"
	    listOf.appendChild(li2);
	    
	    var li3 = li1.cloneNode(true);
	    li3.innerHTML="<label onclick='naming(\"실버\",43)'>실버</label>"
	    listOf.appendChild(li3);
	}
}
function naming(name,sfieldnum){
	var resultOf3 = document.getElementById("resultOf3");
	resultOf3.innerHTML="";
	resultOf3.innerHTML=name;
	var fieldnum = document.getElementById("fieldnum");
	fieldnum.setAttribute("value", sfieldnum);
	console.log(sfieldnum);
	console.log(fieldnum.value);
}
var newWindow;
function newPage(pnum){
	newWindow = window.open("<%=request.getContextPath()%>/item?cmd=detail&pnum="+pnum, "", "width=600, height=580, left=500, top=100,scrollbars=1, location=1");
}


</script>
<form method="post" action="<c:url value='/item?cmd=listOk'/>">
	<input type="hidden" name="fieldnum" id="fieldnum">
	<h1>상품관리 > 상품수정</h1><br>
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
													<li><a href="javascript:selectRing();">반&nbsp;&nbsp;&nbsp;지 ></a></li>
													<li><a href="javascript:selectNeck();">목걸이 ></a></li>	
													<li><a href="javascript:selectEar();">귀걸이 ></a></li>
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
<form>
	<table id="itemListTable" border="1">
		<tr>
			<th>상품분류</th><th>상품번호</th><th>상품이름</th><th>재고</th><th>가격</th><th>등록일</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<c:if test="${vo.fieldnum eq 11}"><c:set var="fnum" value="반지 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 12}"><c:set var="fnum" value="반지 > 다이아"/></c:if>
			<c:if test="${vo.fieldnum eq 13}"><c:set var="fnum" value="반지 > 탄생석"/></c:if>
			<c:if test="${vo.fieldnum eq 14}"><c:set var="fnum" value="반지 > 실버"/></c:if>
			<c:if test="${vo.fieldnum eq 21}"><c:set var="fnum" value="반지 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 22}"><c:set var="fnum" value="반지 > 다이아"/></c:if>
			<c:if test="${vo.fieldnum eq 23}"><c:set var="fnum" value="반지 > 탄생석"/></c:if>
			<c:if test="${vo.fieldnum eq 31}"><c:set var="fnum" value="반지 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 32}"><c:set var="fnum" value="반지 > 탄생석"/></c:if>
			<c:if test="${vo.fieldnum eq 41}"><c:set var="fnum" value="반지 > 14k/18k"/></c:if>
			<c:if test="${vo.fieldnum eq 42}"><c:set var="fnum" value="반지 > 실버"/></c:if>
			<c:if test="${vo.fieldnum eq 43}"><c:set var="fnum" value="반지 > 다이아"/></c:if>
			<tr class="tr1">
				<td style="width: 250px;">${fnum }</td>
				<td style="width: 75px;">${vo.pnum }</td>
				<td style="width: 225px;"><a href="javascript:newPage(${vo.pnum })">${vo.item_name }</a></td>
				<td style="width: 100px;">${vo.stock }</td>
				<td style="width: 150px;">${vo.price }</td>
				<td style="width: 200px;">${vo.regdate }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<!-- 페이지처리 -->
	<div id='pageText'>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${startPage-1}">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${i}"><span style="color:blue">[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${i}"><span style="color:gray">[${i }]</span></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="<%=request.getContextPath()%>/item?cmd=listOk&pageNum=${endPage+1}">[다음]</a>
			</c:when>
			<c:otherwise>
				[다음]
			</c:otherwise>
		</c:choose>
	</div>
</form>