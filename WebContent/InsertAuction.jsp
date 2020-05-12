<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel = "stylesheet" type="text/css" href="/jquery.datetimepicker.css"/>
<script src="/jquery.js"></script>
<script src="/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript">
$('#datetimepicker').datetimepicker();
</script>
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
<input type="radio" name="category" value="3">유아동/도서
	</td>
	<td>
<input type="radio" name="category" value="4">생활/가공식품
	</td>
	<td>
<input type="radio" name="category" value="5">스포츠/레저
	</td>
	<td>
<input type="radio" name="category" value="6">여성잡화
	</td>
</tr>
<tr>
	<td>
<input type="radio" name="category" value="7">여성의류
	</td>
	<td>
<input type="radio" name="category" value="8">남성패션
	</td>
	<td>
<input type="radio" name="category" value="9">게임/취미
	</td>
	<td>
<input type="radio" name="category" value="10">뷰티/미용
	</td>
	<td>
<input type="radio" name="category" value="11">반려동물용품
	</td>
	<td>
<input type="radio" name="category" value="12">도서/티켓/음반
	<td>
</tr>
<tr>
	<td>
<input type="radio" name="category" value="13">기타
	</td>
</tr>
</table>
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
		<!-- accept 속성으로 이미지파일만 올릴수 있도록 제한, multiple 다중 파일 업로드 가능 
			 파일 인코드 타입 지정 참고 https://jhkang-tech.tistory.com/98
			 전 과제 upload 참고 
		-->
		<input type="file" name="file" accept="image/*" multiple>
		<br>
		<!-- 이미지 프리뷰 -->
		<div id = "preview">
			<img id = "img">
		</div>
	</td>
</tr>
</table>
<h1> 경매 설정 </h1>
<table>
<tr>
	<td>
		시작가격
	</td>
	<td>
		<input type="text" name="a_startbid">원
	</td>
</tr>
<tr>
	<td>
		입찰단위
	</td>
	<td>
		<input type="text" name="a_startbid">원
	</td>
</tr>
<tr>
	<td>
		상품상태
	</td>
	<td>
		<input type="radio" name="a_condition" value="최상">최상
		<input type="radio" name="a_condition" value="상">상
		<input type="radio" name="a_condition" value="중">중
		<input type="radio" name="a_condition" value="하">하
	</td>
</tr>
<tr>
	<td>
		경매기간
	</td>
	<td>
		<input id=datetimepicker type="text">
		~
		<input id=datetimepicker type="text">		
	</td>
</tr>
</table>
<h1>배송설정</h1>
<table>
	<tr>
		<td>배송 방법</td>
		<td>
			<input type="radio" name="s_way" value="택배">택배
			<input type="radio" name="s_way" value="우편">우편
			<input type="radio" name="s_way" value="퀵">퀵
		<td>
	</tr>
	<tr>
		<td>
			비용 부담
		</td>
		<td>
		<input type="text" name="s_price">원
		</td>
	</tr>
</table>
<h1>계좌번호 입력</h1>
<table>
	<tr>
		<td>계좌번호</td>
		<td>
			<input type="text" name="account">
		</td>
	</tr>
</table>
<br>
<input type="submit" value="등록" style="width: 100px;height: 50px">
</form>
</body>
<script type="text/javascript">

</script>
</html>