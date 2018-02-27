<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<head>
<style type="text/css">
		p label {width: 100px; display: inline-block;}
		.joinlayout{margin-left: 300px; margin-top: 5px; width: 525px;padding: 14px;}
#form{border: solid 2px #b7ddf2; background:#ebf4fb;}
#but{
clear:both;
margin-left:10px;
width:100px;
height:30px;
text-align:center;
line-height:31px;
background-color:#000;
color:#FFFFFF;
font-size:13px;
font-weight:bold;
font-family:tahoma;
}
</style>
<script type="text/javascript">


	function update() {
		var pwd = document.frm.pwd;
		var pwd2=document.frm.pwd2;
		var email=document.frm.email;
		var phone=document.frm.phone;
		
		if(pwd.value.length==0){
		alert("비밀번호를 입력하세요.");
		pwd.focus();
		return false;
	}	
		if(pwd.value!=pwd2.value){
		alert("비밀번호와 비밀번호확인은 같아야합니다.");
		pwd.focus();
		return false;
	}
		//이메일 검사 향후 조건바뀜
		if(email.value.length==0){
			alert("이메일을 입력하세요.");
			email.focus();
			return false;
		}
		//이메일 조건 검사
		if(email.value.indexOf("@")==-1||email.value.indexOf(".")==-1){
			alert("이메일주소는 '@'와'.'가 포함되어야 합니다.");
			email.focus();
			return false;
		}
		//핸드폰검사	
		if(phone.value.length==0 || phone.value.length<11){
			alert("핸드폰번 11자리 이상 입력하세요");
			phone.focus();
			return false;
		}
}//update

			var xhr = null;
			function del() {
				var pwd = document.frm.pwd;
				var pwd2=document.frm.pwd2;
				if(pwd.value.length==0){
					alert("비밀번호를 입력하세요.");
					pwd.focus();
					return false;
				}	
				//비번1.2 같게
				if(pwd.value!=pwd2.value){
					alert("비밀번호와  비밀번호확인이 동일한지 검사해주세요.");
					pwd2.focus();
					return false;
				}	
				var pwd=document.getElementsByName("pwd")[0].value;
				xhr=new XMLHttpRequest();
				xhr.onreadystatechange=callback;
				xhr.open("get","user/pwdcheck_hhj.jsp?pwd="+pwd,true);
				xhr.send();
			}
			function callback() {
				if(xhr.readyState==4 && xhr.status==200){
					var result=xhr.responseText;
					var json=eval('('+result+')');
					var pwdspan =document.getElementById("pwdspan");
					if(json.using==true){
						////비밀번호맞음 삭제됨
						var mnum = frm.mnum.value;
						var con = confirm('정말 탈퇴하시겠습니까?');
						if(con==true){
							pwdspan.innerHTML="응 탈퇴";
							location.href = "<%=request.getContextPath()%>/JoinController.do?cmd=delete&mnum="+mnum;
						}else{
							pwdspan.innerHTML="응 안해";
						}
					}else{
						pwdspan.innerHTML="비밀번호가 맞는지 확인해주세요.";
						//비밀번호틀림 삭제불가
					}//else
				}//xhr

		}//del()


</script>
</head>
<body>
<div id="form" class="joinlayout">
<h1>Member 정보 수정</h1>

<form method="post" name="frm" onsubmit="return update()" action="<%=request.getContextPath()%>/JoinController.do?cmd=updateOk">
<input type="hidden" value="${user.mnum}" name="mnum">
<p><label for="id">아이디 </label><input type="text" value="${user.id }" name="id" readonly="readonly"></p>
<p><label for="name">이름 </label><input type="text" value="${user.name}" name="name" readonly="readonly"></p>
<p><label for="birthday">생년월일 </label><input type="text" value="${user.birthday }" name="birthday" readonly="readonly"></p>
<p><label for="joindate">가입일 </label><input type="text" value="${user.joindate }" name="joindate" readonly="readonly"></p>
<p><label for="pwd">비밀번호</label><input type="password" name="pwd" id="pwd"></p>
<p><label for="pwd2">비밀번호확인</label><input type="password" name="pwd2" id="pwd2"></p>
<p><label for="email">이메일</label><input type="text" value="${user.email }" name="email" id="email"></p>
<p><label for="address">주소</label><input type="text" value="${user.address }" name="address" id="address"></p>
<p><label for="phone">휴대폰번호</label><input type="text" value="${user.phone}" name="phone" id="phone"></p>
<input type="submit" id="but" value="회원 정보수정" > 
<input type="reset" id="but" value="취소"><br><br>
<input type="button" id="but" onclick="del()" value="회원탈퇴하기" >
</form>

<span id="pwdspan" style="font-size: 12px;color:red"></span>

</div>
</body>


