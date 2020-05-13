<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>TimeTest.jsp</title>
<script type="text/javascript">
setInterval(getTime, 1000);
setInterval(getTime2, 1000);
setInterval(getTime3, 1000);
	function getTime() {
		var time=new Date();
		var ss=time.getHours() + "시" + time.getMinutes() +
		 "분" + time.getSeconds() + "초";
		document.getElementById("time1").innerHTML=ss;
	}
	function getTime2() {
		var time=new Date();
		var ss=time.getHours()+1 + "시" + time.getMinutes() +
		 "분" + time.getSeconds() + "초";
		document.getElementById("time2").innerHTML=ss;
	}
	function getTime3() {
		var time=new Date();
		var ss=time.getHours()+2 + "시" + time.getMinutes() +
		 "분" + time.getSeconds() + "초";
		document.getElementById("time3").innerHTML=ss;
	}
	
</script>
</head>
<body>
<div id="time1"></div><br>
<div id="time2"></div><br>
<div id="time3"></div><br>
<div id="time4"></div><br>
<div id="time5"></div><br>
<div id="time6"></div><br>
<div id="time7"></div><br>
<div id="time8"></div><br>
<div id="time9"></div><br>
<div id="time10"></div><br>
<div id="time11"></div><br>
<div id="time12"></div><br>
<div id="time13"></div><br>
<div id="time14"></div><br>
<div id="time15"></div><br>
<div id="time16"></div><br>
<div id="time17"></div><br>
<div id="time18"></div><br>
<div id="time19"></div><br>
<div id="time20"></div><br>
</body>
</html>