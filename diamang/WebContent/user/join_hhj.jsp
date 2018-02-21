<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <style type="text/css">
		p label {width: 150px; display: inline-block;}
</style>
<head>
<script type="text/javascript">
	function join() {
		//아이디검사
		var id=document.frm.id;
		var regid=/^[A-Za-z0-9+]*$/;
		var pwd=document.frm.pwd;
		var pwd2=document.frm.pwd2;
		var name=document.frm.name;
		var birthday=document.frm.birthday;
		var email=document.frm.email;

		//아이디 공백검사
		if(id.value.length==0){
			alert("아이디를 입력해주세요.");
			birthday.focus();
			return false;
		}
		//아이디 한글X
		if(!regid.test(id.value)){
			alert("아이디는 영어와 숫자로 입력해주세요.");
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
		//이름 빈칸검사
		if(name.value.length==0){
			alert("이름을 입력하세요.");
			name.focus();
			return false;
		}
		//생년월일 빈칸검사
		if(birthday.value.length==0){
			alert("생년월일을 입력하세요.");
			birthday.focus();
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
			alert("이메일주소는 @가 포함되어야 합니다.");
			email.focus();
			return false;
		}
		
		
		//집주소검사 향후 바뀔수있슴
		if(address.value.length==0){
			alert("집주소를 입력하세요.");
			address.focus();
			return false;
		}
		//핸드폰검사	
		if(phone.value.length==0 && phone.value.length<10){
			alert("핸드폰번 11자리 이상 입력하세요");
			phone.focus();
			return false;
		}
		//약관동의 검사
		if(document.frm.check.checked==false) {
			alert("약관에 동의해주세요.");
			return false;
		    }
	}//function
	
	//////////////ajax
	var xhr = null;
	function idcheck() {
		var id = document.getElementsByName("id")[0].value;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback1;
		xhr.open("get","idcheck.jsp?id="+id,true);
		xhr.send();
	}
	function callback1(){
		if(xhr.readyState==4 && xhr.status==200){
			var result=xhr.responseText;
			var json=eval('('+result+')');
			var idsapn=document.getElementById("idsapn");
			console.log(json.using);
			if(json.using==true){
				idsapn.innerHTML="중복된 아이디입니다.";
			}else if(id.value.length<5){
				idsapn.innerHTML="아이디는 6자리이상 영문+숫자로 입력해주세요.";
			}else{
				idsapn.innerHTML="사용가능한 아이디입니다.";
			}
		}
	}
	var xhr2 = null;
	function emailcheck() {
		var email = document.getElementsByName("email")[0].value;
		xhr2=new XMLHttpRequest();
		xhr2.onreadystatechange=callback;
		xhr2.open("get","emailcheck.jsp?email="+email,true);
		xhr2.send();
	}
	function callback(){
		if(xhr2.readyState==4 && xhr2.status==200){
			var result=xhr2.responseText;
			var json1=eval('('+result+')');
			var emailsapn=document.getElementById("emailsapn");
			console.log(json1.using2);
			if(json1.using2==true){
				emailsapn.innerHTML="중복된이메일 입니다.";
			}else if(email.value.indexOf("@")==-1||email.value.indexOf(".")==-1){
				emailsapn.innerHTML="@와.이 포함된 형식의 이메일을 입력해주세요.";
			}else{
				emailsapn.innerHTML="사용가능한 이메일입니다ㅋ.";
			}
		}
	}
</script>

</head>
<body>
<div>
<h1>회원가입</h1>
<form method="post" name="frm" onsubmit="return join()" action="<%=request.getContextPath()%>/JoinController.do?cmd=insertOk" >

<p><label for="id">아이디 </label><input type="text" name="id" id="id"  onkeyup="idcheck()">
<span id="idsapn" style="font-size: 12px;color:red"></span></p>

<p><label for="pwd">비밀번호 </label><input type="password" name="pwd" id="pwd"><span id="pwdsapn"></span></p>
<p><label for="pwd2">비밀번호 확인 </label><input type="password" name="pwd2"></p>
<p><label for="name">이름 </label><input type="text" name="name" id="name" placeholder="예시)이나영"></p>
<p><label for="birthday">생일년월일</label><input type="text" name="birthday" id="birthday" placeholder="예시) 920228"></p>

<p><label for="email">이메일주소</label><input type="text" name="email" id="email" placeholder="예시)test@naver.com" onkeyup="emailcheck()" >
<span id="emailsapn" style="font-size: 12px;color:red"></span></p>

<p><label for="address">집주소</label><input type="text" name="address" id="address" placeholder="예시)서울시 의정부 의정부동 213-12"></p>
<p><label for="phone">핸드폰번호</label><input type="text" name="phone" id="phone" placeholder="예시) 01038371731"></p>
<br>
*이용약관<br>
<textarea rows="5" cols="50" readonly="readonly">이용약관블라블라</textarea><br>
<input type="checkbox" value="약관동의" name="check" checked="checked">위 개인정보취급방침에 대한 고지를 확인하였으며, 이에 동의합니다.<br><br>
 
 
 <input type="submit" value="가입하기" >
 <input type="reset" value="취소">

</form>
</div>
</body>
</html>