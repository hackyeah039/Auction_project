<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${cp }/Auction/jquery.js"></script>

<title>InsertAuction.jsp</title>
<!-- 0512
	카테고리 테이블값 받아와서 value 값 및 내용에 뿌려주는걸로 수정하기 - 나중에
 -->
 <style type="text/css">
 	#preview img {
 		width: 100px;
 		height: 100px;
 	}
 </style>
 <!-- 0513 이미지 프리뷰 스크립트 -->
 <script type="text/javascript">
 	var sel_files = [];
 	$(document).ready(function(){
 		$('#file').on("change",showSelectImg);
 	});
 	function showSelectImg(e) {
 		// filelist 객체 
 		var files = e.target.files;
 		// 파일 선택 갯수 출력
 		console.log(files.length);
 		if(files.length >= 10){
 			// 선택된 파일 없애고 비우기
 			$('#file').val("");
 			alert("10개이상의 그림은 업로드할 수 없습니다.");
 			return;
 		}
		var filesArr = Array.prototype.slice.call(files);
		// 이미지를 다시 올리고 싶을 때 div안 files 요소 지우기 -> 새로 업로드할 파일만 보이게 하기
		$('#preview').empty();
		filesArr.forEach(function(f) {
			// files 배열에 담기 
			sel_files.push(f);
			
			var reader = new FileReader();
			reader.onload = function(e) {
				var img_html = "<img src=\"" + e.target.result + "\"/>";
				$('#preview').append(img_html);
			}
			reader.readAsDataURL(f);
		});
	}
<%
	// 세션에서 회원번호를 가져와서 계좌번호 조회하기
	//String m_num = (String)session.getAttribute("m_num");
	//String m_num = "1";
%>
 	<%-- 바로 팝업 띄우기
 	function showList() {
		var url = "showAccount.jsp?m_num=" + <%=m_num%>;
		window.open(url, "get", "height = 150, width = 280");
	}
	--%>

 	function showList() {
		window.open("${cp}/ShowAccount.do", "_blank", "top=200,left=500,height = 150, width = 280");
	}
	
 </script>
</head>
<body>
<!-- 0513 변수명 db 컬럼값과 동일하게 수정 완료-->
<h1> 카테고리 선택 </h1>
<form method="post" action="${cp }/InsertAuction.do" enctype="multipart/form-data">
<table>
<tr>
	<td>
<input type="radio" name="c_des" value="1">디지털/가전
	</td>
	<td>
<input type="radio" name="c_des" value="2">가구/인테리어
	</td>
	<td>
<input type="radio" name="c_des" value="3">유아동/도서
	</td>
	<td>
<input type="radio" name="c_des" value="4">생활/가공식품
	</td>
	<td>
<input type="radio" name="c_des" value="5">스포츠/레저
	</td>
	<td>
<input type="radio" name="c_des" value="6">여성잡화
	</td>
</tr>
<tr>
	<td>
<input type="radio" name="c_des" value="7">여성의류
	</td>
	<td>
<input type="radio" name="c_des" value="8">남성패션
	</td>
	<td>
<input type="radio" name="c_des" value="9">게임/취미
	</td>
	<td>
<input type="radio" name="c_des" value="10">뷰티/미용
	</td>
	<td>
<input type="radio" name="c_des" value="11">반려동물용품
	</td>
	<td>
<input type="radio" name="c_des" value="12">도서/티켓/음반
	<td>
</tr>
<tr>
	<td>
<input type="radio" name="c_des" value="13">기타
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
		<input type="text" name="a_title" size="49">
	</td>
</tr>
<tr>
	<td>
		물품설명 
	</td>
	<td>
		<textarea rows="10" cols="50" name="a_content"></textarea>
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
		<input type="file" id="file" name="i_path" accept="image/*" multiple>
		<br>
		<!-- 이미지 썸네일 출력하는 영역-->
		<div id = "preview">
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
		<input type="text" name="a_bidunit">원
	</td>
</tr>
<tr>
	<td>
		상품상태
	</td>
	<td>
		<input type="radio" name="a_condition" value="1">최상
		<input type="radio" name="a_condition" value="2">상
		<input type="radio" name="a_condition" value="3">중
		<input type="radio" name="a_condition" value="4">하
	</td>
</tr>
<tr>
	<td>
		경매기간
	</td>
	<td>
		<input id="startdate" name="a_startdate" type="text">
		-
		<input id="enddate" name="a_enddate" type="text">
		<!-- 달력 수정 완료 0514 -->
		<!-- jQuery 사용하여 캘린더 호출0513  -->
		<script type="text/javascript">
		// 참고 페이지 https://xdsoft.net/jqplugins/datetimepicker/
		$(document).ready(function(){
			 $('#startdate').datetimepicker({
			  	//형식 수정 완
				 format:'Y/m/d H:i:s',
			  // 오늘 일자
			  minDate:0 
			 });
			 $('#enddate').datetimepicker({
			  format:'Y/m/d H:i:s',
			  // startdate에서 받아와 기본값이 다음날이 되도록 
			  minDate:'+1970/01/02', // 내일 
			  defaultDate: '+1970/01/02' // 내일
			 });
		});
		</script>
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
			<!-- controller 거쳐서 popup 으로 변경완료 -->
			<input type="text" name="account" id="account">
			<input type="hidden" name="sel_number" id="sel_number">
			<input type="button" value="기존계좌확인" onclick="showList()">

		</td>
	</tr>
</table>
<br>
<input type="submit" value="등록" style="width: 100px;height: 50px">
</form>
</body>
<link rel = "stylesheet" type="text/css" href="${cp }/Auction/jquery.datetimepicker.min.css"/>
<script src="${cp }/Auction/jquery.datetimepicker.full.js"></script>
</html>