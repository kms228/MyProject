<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/admin/css/common_kms.css?ver=1">
</head>
<script type="text/javascript">

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
</script>

<body>
<div id="openWrap">
	<div class="sectionBar"><br>
		<h3>상품 정보</h3>
		<br>
	</div>
	<form method="post" action="<c:url value='/item?cmd=update'/>" enctype="multipart/form-data" >
		<table border="1" id="opentable">
			<tr>
				<th>상품분류</th><td colspan="2"><input type="text" value="${vo.fieldnum }"></td>
			</tr>
			<tr>	
				<th>상품번호</th><td colspan="2"><input type="text" value="${vo.pnum }" readonly="readonly"><span>수정불가</span></td>
			</tr>
			<tr>
				<th>상품이름</th><td colspan="2"><input type="text" value="${vo.item_name }" placeholder="예시) 18k 1g 정수 심플링 반지"></td>
			</tr>
			<tr class="filebox">
				<th>대표이미지</th>
				<td>
					<input type="text" class="filename" value="${img1vo.savefilename }" id="fileName1" readonly="readonly" >
					<label for="file1" class="btn_label">파일찾기</label>
					<input type="file" id="file1" name="file1" class="realfile" onchange="javascript:file_change1(this.value);">
				</td>
			</tr>
			<tr class="filebox">
				<th>상세이미지</th>
				<td>
					<input type="text" class="filename" value="${img2vo.savefilename }" id="fileName2" readonly="readonly">
					<label for="file2" class="btn_label">파일찾기</label>
					<input type="file" id="file2" name="file2" class="realfile" onchange="javascript:file_change2(this.value);">
				</td>
			</tr>
			<tr>
				<th>재고</th><td colspan="2"><input type="text" value="${vo.stock }" name="stock" placeholder="예시) 5"><span>EA</span></td>
			</tr>
			<tr>
				<th>가격</th><td colspan="2"><input type="text" value="${vo.price }" name="price" placeholder="예시) 50000"><span>KRW</span></td>
			</tr>
			<tr>
				<th>등록일</th><td colspan="2"><input type="text" value="${vo.regdate }" readonly="readonly"><span>(수정된 날짜로 자동변환됩니다.)</span></td>
			</tr>
		</table>
		<br>
		<div align="center"><input type="submit" value="수정" class="basicbtn"></div>
		<br>
	</form>
</div>
</body>

</html>