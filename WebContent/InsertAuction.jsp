<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertAuction.jsp</title>
<!-- 0512
	카테고리 테이블값 받아와서 value 값 및 내용에 뿌려주는걸로 수정하기
 -->
</head>
<body>
<h1> 카테고리 선택 </h1>
<form method="post" action="">
<table>
<tr>
	<td>
<input type="radio" name="category" value="1">디지털/가전
	</td>
	<td>
<input type="radio" name="category" value="2">가구/인테리어
	</td>
	<td>
<input type="radio" name="category" value="3">유아동/유아도서
	</td>
	<td>
<input type="radio" name="category" value="4">생활/가공식품
	</td>
	<td>
<input type="radio" name="category" value="5">스포츠/레저
	</td>
	<td>
<input type="radio" name="category" value="6">도서/티켓/음반
	</td>
</tr>
<tr>
	<td>
<input type="radio" name="category" value="7">여성잡화
	</td>
	<td>
<input type="radio" name="category" value="8">여성의류
	</td>
	<td>
<input type="radio" name="category" value="9">남성패션/잡화
	</td>
	<td>
<input type="radio" name="category" value="10">게임/취미
	</td>
	<td>
<input type="radio" name="category" value="11">뷰티/미용
	</td>
	<td>
<input type="radio" name="category" value="12">반려동물용품
	<td>
</tr>
<tr>
	<td>
<input type="radio" name="category" value="13">기타
	</td>
</tr>
</table>
<br>
<h1> 경매 물품 정보 </h1>
<table>
<tr>
	<td>
		제목
	</td>
	<td>
		<input type="text" name="title" size="49">
	</td>
</tr>
<tr>
	<td>
		물품설명 
	</td>
	<td>
		<textarea rows="10" cols="50" name="content"></textarea>
	</td>
</tr>
<tr>
	<td>
		이미지등록
	</td>
	<td>
		<!-- accept 속성으로 이미지파일만 올릴수 있도록 제한, multiple 다중 파일 업로드 가능 -->
		<input type="file" name="file" class="imageUp" accept="image/*" multiple>
		<button class="imgUpBtn">사진업로드</botton>
		<br>
		<div id = "preview">
			<img id = "img">
		</div>
	</td>
</tr>
</table>
<br>
<h1> 경매 설정 </h1>
<table>
<tr>
	<td>
		
	</td>
</tr>
<tr>
</tr>
<tr>
</tr>

</table>
<input type="submit" value="등록">
</form>
</body>
<script type="text/javascript">
// 사진업로드 버튼 클릭시 업로드 창 띄우기(input태그는 나중에 가리기.)
const imgUpBtn = document.querySelector('.imgUpBtn');
const imageUp = document.querySelector('.imageUp');
imgUpBtn.addEventListener('click',()=>{
	imageUp.click();
});

</script>
</html>