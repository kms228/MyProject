<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<script type="text/javascript">
window.onload = function(){
	/* if(!("${msg}"==="")){
		alert("${msg}");
	} */
	var optName = "${search.optName}";
	if(!(optName==="")){	
		document.getElementById(optName).setAttribute("selected","selected");			
	}
	var gnum = "${search.gnum}";
	if(!(gnum==="")){
		document.getElementById(gnum).setAttribute("selected","selected");
	}
}
//페이지 이동 이나 기타 조건시 검색조건 받기위한 메소드
var getSearch = function(){
	var url = "";		
	var gnum = "${search.gnum}";
	var optValue = "${search.optValue}";
							
	url = url+"&grade=${search.gnum}";
	if(!(optValue === "")){
		url = url+"&optName=${search.optName}";
		url = url+"&optValue=${search.optValue}";
	} else {
		url = url+"&optName=";
		url = url+"&optValue=";
	}	
	return url;
}

var page = function(num){
	var url = "members.do?cmd=infosearch&pageNum="+ num;
		
	url = url + getSearch();
	//alert(url);
	location.href=url;
}
</script>

<h2>회원관리 > 회원정보 조회</h2><br>
<div class="sectionBar">
	<h3>검색조건설정</h3>
</div>
<form method="post" action="<%=request.getContextPath()%>/admin/members.do?cmd=infosearch">
<table border="1" class="mem2">
	<tbody>
	<tr>
		<th>개인정보</th>
		<td>
			<select id="optName" name="optName">
				<option value="">전체</option>
				<option value="id" id="id">아이디</option>
				<option value="name" id="name">이름</option>
				<option value="email" id="email">이메일</option>
			</select>&nbsp;&nbsp;
			<input type="text" id="optValue" name="optValue" value="${search.optValue }">
		</td>
	</tr>
	<tr>
		<th>회원등급</th>
		<td>
			<select id="grade" name="grade"> <!-- document.getElementById("grade").value; -->
				<option value="">전체</option>
				<c:forEach var="vo" items="${grade }">				
					<option value="${vo.gnum }" id="${vo.gnum }">${vo.grade }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<!-- <tr>
		<th>주문상품</th><td><input type="text" name="good"><input type="button" value="상품검색" id="good"></td>
	</tr> -->
	</tbody>
</table>
<br>
<div align="center">
	<input class="basicbtn" type="submit" value="검색" class="basicbtn">
</div>
<br>
</form>
<div class="sectionBar">
	
	<h3>회원목록</h3>
</div>
<table border="1" class="mem">
	<thead>
		<tr>
			<th>가입일</th><th>이름</th><th>아이디</th><th>등급</th><th>전화번호</th><th>이메일</th><th>주소</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.joindate }</td><td>${vo.name }</td><td>${vo.id }</td><td>${vo.grade }</td><td>${vo.phone }</td><td>${vo.email }</td><td>${vo.address }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 페이징처리 -->
<c:if test="${pageVo!=null }">
<div align="center">
	<c:choose>
		<c:when test="${pageVo.startPage>pageVo.currentPageVolume }">
			<a href="javascript:page(${pageVo.startPage-1})">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]	
		</c:otherwise>
	</c:choose>	
	<c:forEach var="i" begin="${pageVo.startPage }"  end="${pageVo.endPage }">
		<c:choose>
			<c:when test="${pageVo.pageNum==i }">
				<a href="javascript:page(${i })"><span style="color:blue">[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="javascript:page(${i })"><span style="color:gray">[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageVo.endPage<pageVo.pageCount }">
			<a href="javascript:page(${pageVo.endPage+1 })">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]	
		</c:otherwise>	
	</c:choose>	
</div>
</c:if>
<h2>도움말</h2>
<ul>
	<li>아이디, 이름, 이메일 등으로 회원정보를 검색할수 있습니다.</li>
	<li>개인정보 탭에서 검색한 회원들중에서 등급별로 검색할수 있어요.</li>
</ul>
