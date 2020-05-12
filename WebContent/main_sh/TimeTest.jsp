<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>TimeTest.jsp</title>
<script type="text/javascript">
setInterval(getTime, 1000);
	function getTime() {
		var time=new Date();
		var ss=time.getHours() + "시" + time.getMinutes() +
		 "분" + time.getSeconds() + "초";
		document.getElementById("time1").innerHTML=ss;
		document.getElementById("time2").innerHTML=ss;
		document.getElementById("time3").innerHTML=ss;
		document.getElementById("time4").innerHTML=ss;
		document.getElementById("time5").innerHTML=ss;
		document.getElementById("time6").innerHTML=ss;
		document.getElementById("time7").innerHTML=ss;
		document.getElementById("time8").innerHTML=ss;
		document.getElementById("time9").innerHTML=ss;
		document.getElementById("time10").innerHTML=ss;
		document.getElementById("time11").innerHTML=ss;
		document.getElementById("time12").innerHTML=ss;
		document.getElementById("time13").innerHTML=ss;
		document.getElementById("time14").innerHTML=ss;
		document.getElementById("time15").innerHTML=ss;
		document.getElementById("time16").innerHTML=ss;
		document.getElementById("time17").innerHTML=ss;
		document.getElementById("time18").innerHTML=ss;
		document.getElementById("time19").innerHTML=ss;
		document.getElementById("time20").innerHTML=ss;
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