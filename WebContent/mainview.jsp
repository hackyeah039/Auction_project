<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel= "stylesheet" type="text/css" href="css/main.css">
	<script src="js/mainjs.js"></script>
</head>
<body>
홈>온라인경매>${cate }<br>
<br>
${info.a_title}<br>
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
물품번호 ${info.a_num }<br>
시작가 ${info.a_startbid }<br>
입찰단위 ${info.a_bidunit }<br>
입찰방식 : 비공개<br>
입찰수  : <br>
<a href="">경매기록보기</a> <br>
배송방법 ${ship.s_way }<br>
배송비용 ${ship.s_price }<br>
<a href="">판매자 ID : ${seller }</a><br>
<a href="">입찰하기</a><br>
<a href="">관심물품 찜하기</a><br>
문의하기<br>
물품정보보<br>
물품문의<br>
배송/반품<br>
영수증 발행안내<br>



</body>
</html>