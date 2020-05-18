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
홈>온라인경매>카테고리(여기)<br>

물품제목(여기)<br>
<div class="container">
	<ul class="slider-container simple-list" id="slider">
	
		<c:forEach var="vo" items="${ipath }">
			<li class ="slide">
				<img src="${vo }" width="200" height="300">
			</li>
		</c:forEach>
	</ul>
	
	<a href="#" id="prev"></a>
	<a href="#" id="next"></a>
</div>
 경매기간  ${info.a_startdate } ~ ${info.a_enddate }<br> 
물품번호<br>
경매기간<br>
시작가<br>
입찰단위<br>
입찰방식(비공개)<br>
입찰수<br>
경매기록보기<br>
배송방법("택배"+ 가져오기)<br>
배송비용<br> 
판매자 ID(누르면 신고페이지로)<br>
입찰하기<br>
관심물품<br>
문의하기<br>
물품정보<br>
물품문의<br>
배송/반품<br>
영수증 발행안내<br>



</body>
</html>