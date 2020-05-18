<%@page import="java.sql.Date"%>
<%@page import="semi.dao.jw.AuctionDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>여기임</h1>

<div id="result" name="result"></div>


</body>
<script type="text/javascript">

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
</script>
</html>