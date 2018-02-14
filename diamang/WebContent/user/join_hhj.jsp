<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
		p label {width: 150px; display: inline-block;}
</style>
<script type="text/javascript">
	function join() {
		//아이디검사
		var id=document.frm.id1;
		var regid=/^[A-Za-z0-9+]*$/;
		var pwd=document.frm.pwd;
		var pwd2=document.frm.pwd2;
		 
		
		//아이디 한글X
		if(!regid.test(id.value)){
			alert("영어숫자검사");
			var idspan=document.getElementById(idspan);
			idspan.innerHTML="영문과 숫자로만 입력해 주세요.";
			id.focus();
			return false;
		}
		//아이디 4이상 16이하
		if(id.value.length<4 || id.value.length>16){
			alert("아이디는 4자이상 16자이하로 입력해주세요");
			id.focus();
			return false;
		}
		//비번 
		if(pwd.value.length<5  ){   //영문숫자조합넣어야함
			alert("비밀번호10자이상");
			pwd.focus();
			return false;
		}
		//비번1.2 같게
		if(pwd.value!=pwd2.value){
			alert("비밀번호와  비밀번호확인이 동일한지 검사해주세요.");
			pwd2.focus();
			return false;
		}
		
		
		
	}//function
	
</script>

</head>
<body>
<div>
<h1>회원가입</h1>
<form method="post" name="frm" action="??" onsubmit="return join()">

<p><label for="id">아이디 </label><input type="text" name="id1">
<span id="idsapn" style="font-size: 12px;color:red"></span></p>
<p><label for="pwd">비밀번호 </label><input type="password" name="pwd"><span id="pwdsapn"></span></p>
<p><label for="pwd2">비밀번호 확인 </label><input type="password" name="pwd2"><span id="pwd2span"></span></p>
<p><label for="name">이름 </label><input type="text" name="name"><span id="namesapn"></span></p>
<p><label for="birthday">생일년월일</label><input type="text" name="birthday"></p>
<p><label for="email">이메일주소</label><input type="text" name="email"></p>
<p><label for="address">집주소</label><input type="text" name="address"></p>
<p><label for="phone">핸드폰번호</label><input type="text" name="phone"></p>
<br>
*이용약관<br>
<textarea rows="5" cols="50" readonly="readonly">이용약관블라블라</textarea><br>
<input type="checkbox" value="약관동의" name="check">위 개인정보취급방침에 대한 고지를 확인하였으며, 이에 동의합니다.<br><br>
 
 
 <input type="submit" onclick="join()" value="가입하기" >
 <input type="reset" value="취소">

</form>
</div>
</body>
</html>