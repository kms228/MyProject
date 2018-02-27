<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
var xhr1=null
function addMenu1(){	
	xhr1=new XMLHttpRequest();	
	xhr1.onreadystatechange=list1;	
	xhr1.open('get','<%=request.getContextPath()%>/item?cmd=itemMenu',true);
	xhr1.send();
}
function list1(){
	if(xhr1.readyState==4 && xhr1.status==200){
		//alert("success");
		var menuList = document.getElementById("menuList");
		//기존메뉴지우기
		menuList.innerHTML="";
		var xml = xhr1.responseXML;
		menuList.innerHTML="<a href='<%=request.getContextPath()%>/item?cmd=insert'>상품등록</a>|&nbsp;"+
							"<a href='<%=request.getContextPath()%>/item?cmd=list'>&nbsp;상품수정 및 삭제</a>";
	}
}

var xhr2=null
function addMenu2(){	
	xhr2=new XMLHttpRequest();	
	xhr2.onreadystatechange=list2;	
	xhr2.open('get','<%=request.getContextPath()%>/store?cmd=storeMenu',true);
	xhr2.send();
}
function list2(){
	if(xhr2.readyState==4 && xhr2.status==200){
		//alert("success");
		var menuList = document.getElementById("menuList");
		//기존메뉴지우기
		menuList.innerHTML="";
		var xml = xhr2.responseXML;
		menuList.innerHTML="<a href='<%=request.getContextPath()%>/item?cmd=insert'>기본정보설정</a>|&nbsp;"+
							"<a href='<%=request.getContextPath()%>/item?cmd=update'>&nbsp;이용약관설정</a>";
	}
}

var xhr3=null
function addMenu3(){	
	xhr3=new XMLHttpRequest();	
	xhr3.onreadystatechange=list3;	
	xhr3.open('get','<%=request.getContextPath()%>/item?cmd=1',true);
	xhr3.send();
}
function list3(){
	if(xhr3.readyState==4 && xhr3.status==200){
		//alert("success");
		var menuList = document.getElementById("menuList");
		//기존메뉴지우기
		menuList.innerHTML="";
		var xml = xhr3.responseXML;
		menuList.innerHTML="<a href='<%=request.getContextPath() %>/admin/members.do?cmd=membersmain'>회원관리메인</a>|&nbsp;"+
							"<a href='<%=request.getContextPath() %>/admin/members.do?cmd=membersinfo'>회원정보조회</a>|&nbsp;"+
							"<a href='<%=request.getContextPath() %>/admin/members.do?cmd=membersgrade'>&nbsp;회원등급관리</a>";
	}
}

var xhr4=null
function addMenu4(){	
	xhr4=new XMLHttpRequest();	
	xhr4.onreadystatechange=list4;	
	xhr4.open('get','<%=request.getContextPath()%>/item?cmd=2',true);
	xhr4.send();
}
function list4(){
	if(xhr4.readyState==4 && xhr4.status==200){
		//alert("success");
		var menuList = document.getElementById("menuList");
		//기존메뉴지우기
		menuList.innerHTML="";
		var xml = xhr4.responseXML;
		menuList.innerHTML="<a href='<%=request.getContextPath()%>/admin/order.do?cmd=prepareproduct'>상품준비중</a>|&nbsp;&nbsp;"+
							"<a href='<%=request.getContextPath()%>/admin/order.do?cmd=shippedend'>배송중</a>|&nbsp;&nbsp;"+
							"<a href='<%=request.getContextPath()%>/admin/order.do?cmd=shippedcomplete'>배송완료</a>|&nbsp;&nbsp;"+
							"<a href='<%=request.getContextPath()%>/admin/order.do?cmd=ordercancel'>&nbsp;배송취소</a>";
	}
}

var xhr5=null
function addMenu5(){	
	xhr5=new XMLHttpRequest();	
	xhr5.onreadystatechange=list5;	
	xhr5.open('get','<%=request.getContextPath()%>/item?cmd=3',true);
	xhr5.send();
}
function list5(){
	if(xhr5.readyState==4 && xhr5.status==200){
		//alert("success");
		var menuList = document.getElementById("menuList");
		//기존메뉴지우기
		menuList.innerHTML="";
		var xml = xhr5.responseXML;
		menuList.innerHTML="<a href='<%=request.getContextPath()%>/admin/board.do?cmd=boardqna'>QnA 관리</a>|&nbsp;"+
							"<a href='<%=request.getContextPath()%>/admin/board.do?cmd=boardreview'>&nbsp;후기게시판 관리</a>";
	}
}
</script>
<div>
<br>
	<c:choose >
		<c:when test="${empty sessionScope.id }">
			<div id="login">
				<form action="<c:url value='/login.do?cmd=login'/>" method="post">
					<table>
						<tr>
							<th>아이디</th><td><input type="text" name="id"></td>
							<th>비밀번호</th><td><input type="text" name="pwd"></td>
							<td id="loginbutton" rowspan="2">
								<input class="basicbtn" type="submit" value="로그인">
							</td>
						</tr>	
					</table>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<%-- 로그아웃 --%>
			<div id="logout">
				<div>
					${id }님 환영합니다.
					<input class="basicbtn" type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/login.do?cmd=logout'">
				</div> 
			</div>
		</c:otherwise>
	</c:choose>
	<br>
	<h1 align="center" onclick="location.href='<%=request.getContextPath()%>/admin/layout_kms.jsp'">쇼핑몰관리24</h1>
	<br>
	<a href="javascript:addMenu2()" onmouseover="addMenu2()">상점관리</a><span>|&nbsp;</span>
	<a href="javascript:addMenu3()" onmouseover="addMenu3()">고객관리</a><span>|&nbsp;</span>
	<a href="javascript:addMenu1()" onmouseover="addMenu1()">상품관리</a><span>|&nbsp;</span>
	<a href="javascript:addMenu4()" onmouseover="addMenu4()">주문관리</a><span>|&nbsp;</span>
	<a href="javascript:addMenu5()" onmouseover="addMenu5()">게시판관리</a>
	
	
	<div id="menuList"></div>
</div>