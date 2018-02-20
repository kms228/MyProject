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
<h3>>상품분류</h3>
<br>
<div class="searchSelect">
	<table border="1">
		<colgroup>
			<col style="width:25%" span="3">
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
<br>
<h3>>대표이미지 선택</h3>	
<br>
<div class="searchImage">
	<table border="1">
		<tr>
			<td><input type="file"></td>
		</tr>
	</table>
</div>
<br>
<h3>>상세이미지 선택</h3>
<br>
<div class="searchImage2">


</div>