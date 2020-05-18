<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel= "stylesheet" type="text/css" href="css/main.css">
	<script>
		//찜
		function myFunction(){
		 	var allwindow= window.open("${cp}/jjim.do?a_num=${a_num}", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=200,left=500,width=250,height=100");
		}
		//신고하기
		function singo(){
			var allwindow= window.open("${cp}/singo.jsp?seller=${seller}", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=400,left=600,width=400,height=300");
		}
		//경매시간
		var myVar = setInterval(function () {
			var now = new Date();//현재시간
			var enddate = new Date(${months}+"/"+${day}+"/"+${years});
			var distance =enddate.getTime()-now.getTime();
			
			var days = Math.floor(distance / (1000 * 60 * 60 * 24));
			days
		  	var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		  	var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		  	var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		  	
		  	
			document.getElementById("result").innerHTML = days + "일 " + hours + "시 "
			  + minutes + "분 " + seconds + "초 ";
			
			if (distance < 0) {
			    clearInterval(myVar);
			    document.getElementById("result").innerHTML = "종료된 경매입니다";
			}
		}, 1000);
		
		

		function fnMove(seq){
			typeof event.preventDefault=="function" ? event.preventDefault() : (event.returnValue = false);
			var offset = $("#product-detail-tab" + seq).offset();
			$('html, body').animate({scrollTop : (offset.top - 100)}, 200);
		}
	
	</script>
	<script src="js/mainjs.js"></script>
</head>
<body>
홈>온라인경매>${cate }<br>
<br>
경매 물품 제목 ${info.a_title}<br>
<br>
<div class="container">
	<ul class="slider-container simple-list" id="slider">
		<c:forEach var="vo" items="${ipath }">
			<li class ="slide">
				<img src="${cp}/image/${vo}" width="200" height="300">
			</li>
		</c:forEach>
	</ul>
	
	<a href="#" id="prev"></a>
	<a href="#" id="next"></a>
</div>
<br>
 경매기간 ${info.a_startdate } ~~ ${info.a_enddate }<br> 
 경매 남은시간 <div id="result"></div>
물품번호 ${info.a_num }<br>
시작가 ${info.a_startbid }<br>
입찰단위 ${info.a_bidunit }<br>
입찰방식 : 비공개<br>
입찰수  : ${bidnum }<br>
<a href="${cp }/history.do?a_num=${a_num }">경매기록보기</a> <br>
배송방법 ${ship.s_way }<br>
배송비용 ${ship.s_price }<br>
판매자 ID : <a href="" onclick="singo()">${seller }</a><br>
<a href="">입찰하기</a><br>
<a href="" onclick="myFunction()">관심물품 찜하기</a><br>
문의하기<br>
물품정보<br>
물품문의<br>



</body>
</html>