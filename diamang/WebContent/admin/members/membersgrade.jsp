<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<script type="text/javascript">
	var addGrade = function(){
		window.open("<%=request.getContextPath()%>/admin/members/addgrade.jsp","_blank","width=250, height=170");				
	}
	var uptGrade = function(gnum,grade){
		var selectedGrade = document.getElementById("selectedGrade");		
		selectedGrade = document.getElementById("1").text;		
		window.open("/diamang/admin/members.do?cmd=goupdate&gnum="+gnum,"_blank","width=250, height=170");
	}
</script>

<input type="hidden" value="" id="selectedGrade">
<h2>회원관리 > 회원등급관리</h2><br>
<div class="sectionBar">
	<h3>회원 등급목록</h3>
</div>
<table border="1" class="mem">
	<thead>
	<tr>
		<th>번호</th><th>등급명</th><th>할인율(%)</th><th>회원수</th><th>수정</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.gnum }</td>
			<td>${vo.grade }</td>
			<td style="display: none;"><a href="javascript:uptGrade('${ vo.gnum}','${vo.grade }')" id="${ vo.gnum}">${vo.grade }</a></td>
			<td>${vo.drate }</td>
			<td>${vo.gnumcnt }</td>
			<td><input type="button" class="basicbtn" value="수정" onclick="uptGrade('${ vo.gnum}','${vo.grade }')"></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div align="center">
	<br>
	<input type="button" value="등급 추가" onclick="addGrade()" class="basicbtn">
</div>
<br>
<div class="sectionBar">
	<h3>회원가입 시 회원등급 기본설정</h3>
</div>
<form method="post" action="<%=request.getContextPath()%>/admin/members.do?cmd=defaultGrade">
<table border="1" class="mem">
<caption style="text-align : left;"><span style="color: navy;">현재 설정된 기본등급은 ${defaultGrade } 입니다.</span></caption>
	<thead>
		<tr>
			<th>회원등급 기본설정</th>
			<th>회원가입 시 회원등급을 
				<select name="gnum">					
				<c:forEach var="vo" items="${list }">				
					<option value="${vo.gnum }">${vo.grade }</option>
				</c:forEach>				
				</select>(으)로 설정합니다.
			</th>
		</tr>
	</thead>
</table>
<div align="center">
	<br>
	<input type="submit" value="설정" class="basicbtn">
</div>
</form>
<h2>도움말</h2>
<ul>
	<li>쇼핑몰의 등급 생성 및 기존 등급 내용을 수정할수 있어요.</li>
	<li>등급별 회원수를 확인할수 있습니다.</li>
	<li>이용자가 회원 가입시 기본으로 부여되는 등급을 설정할수 있어요.</li>
</ul>
