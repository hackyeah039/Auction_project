<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${cp }/Auction/jquery.js"></script>

<title>insertAuction.jsp</title>

 <style type="text/css">
 	#preview img {
 		width: 100px;
 		height: 100px;
 	}
 	#insert{
 		align-content: center;
 		align-items: center;
 		align-self: center;
 		margin: auto;
 	}
 	p{
 		width: 160px;
 		height: 40px;
 		border-radius: 10px;
 		text-align: center;
 		padding-top: 5px;
 	}
 	.tts{
 		width: 100px;
 		text-align: center;
 		
 	}
 	table{
 		border-collapse: separate;
 		border-spacing:  0 10px;
 	}
 	#clist{
 		width: 130px;
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
	//팝업 창 - 계좌 번호 조회
 	function showList() {
		window.open("${cp}/ShowAccount.do", '_blank', 'top=20, left=50, height=100, width=330');
	}
	
	//예외처리  
	function chkValues(){
		//카테고리 
		let category = document.getElementsByName("c_num");
		let cn = 0;
		for(let i = 0 ; i<category.length ; i++){
			if(category[i].checked){
				cn++;
			}
		}
		if(cn == 0){
			alert("카테고리를 선택해주세요.");
			return false;
		}
		// 글 제목, 한글만 
		if(document.getElementsByName("a_title")[0].value.trim() == ""){
			alert("제목을 입력하세요.");
			return false;
		} else if(document.getElementsByName("a_title")[0].value.length > 33){
			alert("제목에 입력가능한 글자수를 초과하였습니다.");
			return false;
		}
		// 글 내용 
		if(document.getElementsByName("a_content")[0].value.trim() == ""){
			alert("내용을 입력하세요.");
			return false;
		} else if(document.getElementsByName("a_content")[0].value.length > 333){
			alert("내용에 입력가능한 글자수를 초과하였습니다.");
			return false;
		}
		// 파일
		if(!document.getElementById("file").value){
			alert("사진을 등록해주세요.");
			return false;
		}
		//시작가격
		if(document.getElementsByName("a_startbid")[0].value.trim() == ""){
			alert("시작가격을 입력하세요.");
			return false;
		} else if(document.getElementsByName("a_startbid")[0].value.length > 8){
			alert("시작가격 입력범위를 초과하였습니다.");
			return false;
		} else if($.isNumeric($('input[name=a_startbid]').val()) == false){
			alert("시작가격을 숫자로 입력하세요.");
			return false;
		}
		//입찰단위
		if(document.getElementsByName("a_bidunit")[0].value.trim() == ""){
			alert("입찰단위를 입력하세요.");
			return false;
		} else if(document.getElementsByName("a_bidunit")[0].value.length > 7){
			alert("입찰단위의 입력범위를 초과하였습니다.");
			return false;
		} else if($.isNumeric($('input[name=a_bidunit]').val()) == false){
			alert("입찰단위를 숫자로 입력하세요.");
			return false;
		}
		//상품상태 
		let condition = document.getElementsByName("a_condition");
		let con = 0;
		for(let i = 0 ; i<condition.length ; i++){
			if(condition[i].checked){
				con++;
			}
		}
		if(con == 0){
			alert("상품상태를 선택해주세요.");
			return false;
		}
		//날짜 비교 
		var startdate = document.getElementsByName("a_startdate")[0].value;
		var enddate = document.getElementsByName("a_enddate")[0].value;
		if(startdate.trim() == "" || enddate.trim() == ""){
			alert("경매일자를 입력해주세요.");
			return false;
		} else if(startdate.substring(0, 10) >= enddate.substring(0, 10)){
			alert("경매시작일자가 종료일자와 같거나 클 수 없습니다.");
			return false;
		}
		//배송방법
		let way = document.getElementsByName("s_way");
		let wn = 0;
		for(let i = 0 ; i<way.length ; i++){
			if(way[i].checked){
				wn++;
			}
		}
		if(wn == 0){
			alert("배송방법을 선택해주세요.");
			return false;
		}
		//배송비용
		if(document.getElementsByName("s_price")[0].value.trim() == ""){
			alert("배송비용를 입력하세요.");
			return false;
		} else if(document.getElementsByName("s_price")[0].value.length > 6){
			alert("배송비용의 입력범위를 초과하였습니다.");
			return false;
		} else if($.isNumeric($('input[name=s_price]').val()) == false){
			alert("배송비용을 숫자로 입력하세요.");
			return false;
		}
		//계좌번호
		if(document.getElementsByName("account")[0].value.trim() == ""){
			alert("계좌번호를 입력하세요.");
			return false;
		} else if(document.getElementsByName("account")[0].value.length > 13){
			alert("계좌번호 입력범위를 초과하였습니다.");
			return false;
		} else if($.isNumeric($('input[name=account]').val()) == false){
			alert("계좌번호를 숫자로 입력하세요.");
			return false;
		}
		return true;
	}
	</script>
</head>
<body>
<!-- 0513 변수명 db 컬럼값과 동일하게 수정 완료-->
<div class="card bg-white outline-white" id="insert" style="margin-top: 50px;">
	<h2 class="text-left mt-3 mb-3" style="margin-top: 10px"><strong>물품등록</strong></h2>
	<form method="post" action="${cp }/InsertAuction.do" enctype="multipart/form-data" onsubmit="return chkValues()">
	<p class="h4 text-white bg-primary mt-3 mb-3"> <strong>카테고리 선택</strong> </p>
	 <table>
	 	<c:forEach var="vo" items="${clist }" varStatus="vs">
	 		<c:choose>
	 			<c:when test="${vs.index%6 == 0}">
	 			<tr>
	 				<td id="clist">
	 				<input type="radio" name="c_num" value="${vo.c_num }">${vo.c_des }
	 				</td>
	 			</c:when>
	 			<c:otherwise>
	 				<td id="clist">
	 				<input type="radio" name="c_num" value="${vo.c_num }">${vo.c_des }
	 				</td>
	 			</c:otherwise>
	 		</c:choose>
	 	</c:forEach>
	 </table>
	<p class="h4 text-white bg-primary mt-3 mb-3"> <strong>경매물품 정보</strong> </p>
	<table>
	<tr>
		<td class="tts">
			제목
		</td>
		<td>
			<input type="text" name="a_title" size="47">
		</td>
	</tr>
	<tr>
		<td class="tts">
			물품<br>설명 
		</td>
		<td>
			<textarea rows="10" cols="50" name="a_content" maxlength="333"></textarea>
		</td>
	</tr>
	<tr>
		<td class="tts">
			이미지<br>등록 
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
	<p class="h4 text-white bg-primary mt-3 mb-3"> <strong>경매 설정</strong> </p>
	<table>
	<tr>
		<td class="tts">
			시작가격
		</td>
		<td>
			<input type="text" name="a_startbid">원
		</td>
	</tr>
	<tr>
		<td class="tts">
			입찰단위
		</td>
		<td>
			<input type="text" name="a_bidunit">원
		</td>
	</tr>
	<tr>
		<td class="tts">
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
		<td class="tts">
			경매기간
		</td>
		<td>
			<input id=curTime type="hidden" value="">
			<input id="startdate" name="a_startdate" type="text">
			-
			<input id="enddate" name="a_enddate" type="text"><span id=click></span>
			
			<!-- 캘린더 시작일 클릭 지정 후 종료일 지정시 종료일 기본 선택값이 캘린더 시작일 다음날이 되도록 수정 -->
			<script type="text/javascript">
			// 참고 페이지 https://xdsoft.net/jqplugins/datetimepicker/
	
			$(document).ready(function() {
			    // 선택한 날의 다음날 부터 선택하도록 체크
				function checkDateStart(){
			        if ($('#startdate').val()){
			        	var date = new Date($('#startdate').val());
			        	date.setDate(date.getDate()+1);
			        	return date;
			        } else {
			        	// 값이 없을때 다음날 부터 선택할 수 있도록 
				        return '+1970/01/02';
			        }
			    }
	
			    $(function(){
			    	// 시작일 
			    	$('#startdate').datetimepicker({
			    		format:'Y/m/d H:i',
			    		onShow:function( ct ){
			    			this.setOptions({
			    				maxDate:$('#enddate').val()?$('#enddate').val():false,
			    				//오늘 이전 날짜는 클릭 불가능
			    				minDate:0,
			    				minTime:0
			    			})
			    		},
			    		timepicker:true
			    	});
			    	
			    	$('#enddate').datetimepicker({
			    		format:'Y/m/d H:i',
			    		onShow:function( ct ){
			    			this.setOptions({
			    				minDate:checkDateStart()
			    			})
			    		},
			    		timepicker:true
			    	});
			    });
			});
			</script>
		</td>
	</tr>
	</table>
	<p class="h4 text-white bg-primary mt-3 mb-3"> <strong>배송 설정</strong> </p>
	<table>
		<tr>
			<td class="tts">배송 방법</td>
			<td>
				<input type="radio" name="s_way" value="택배">택배
				<input type="radio" name="s_way" value="우편">우편
				<input type="radio" name="s_way" value="퀵">퀵
			<td>
		</tr>
		<tr>
			<td class="tts">
				비용 부담
			</td>
			<td>
				<input type="text" name="s_price">원
			</td>
		</tr>
	</table>
	<p class="h4 text-white bg-primary mt-3 mb-3"> <strong>배송 설정</strong> </p>
	<table>
		<tr>
			<td class="tts">계좌번호</td>
			<td>
				<!-- controller 거쳐서 popup 으로 변경완료 -->
				<input type="text" name="account" id="account">
				<input type="hidden" name="sel_number" id="sel_number">
				<input class="btn btn-outline-Dark my-2 my-sm-0" type="button" value="기존계좌확인" onclick="showList()">
			</td>
		</tr>
	</table>
	<br>
	<input class="btn btn-outline-Dark my-2" type="submit" value="등록" style="width: 100px;height: 50px">
	</form>
</div>
</body>
<!-- 0524 00시 - startdate 시간 출력이 안되서 변경 full.js => full.min.js / min.css => .css-->
<link rel = "stylesheet" type="text/css" href="${cp }/Auction/jquery.datetimepicker.css"/>
<script src="${cp }/Auction/jquery.datetimepicker.full.min.js"></script>
</html>