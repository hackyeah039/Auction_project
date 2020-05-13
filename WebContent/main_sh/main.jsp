<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main.jsp</title>
<style type="text/css">
	.auc{width: 300px;height: 400px;border:1px solid #aaa;margin-bottom: 5px;}
</style>
</head>
<body onload="allList()">
<h1>메인화면</h1>
<div id="wrap">
	<div id="head">
	</div>
	<div id="mid">
		<div id="banner"></div><br>
	</div>
	<div id="bottom">
		<div id="allauc"></div>
		<div id="recom"></div><br>
		<div id="last"></div><br>
		<div id="recent"></div><br>
	</div>
</div>
</body>
<script type="text/javascript">
	function allList() {
		var xhrList=null;
		xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=function(){
			if(xhrList.readyState==4 && xhrList.status==200){
				var nowTime="";
				var xml=xhrList.responseXML;
				var data=xml.getElementsByTagName("data");
				var allauc=document.getElementById("allauc");
				for(var i=0;i<data.length;i++){
					var title=data[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					var price=data[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					var id=data[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					var a_check=data[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					var endDate=data[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					var div=document.createElement("div");
					let timeDiv=document.createElement("div");
					div.innerHTML="제목 : " + title + "<br>" +
									"가격 : " + price + "<br>"+
									"작성자 : " + id + "<br>"+
									"조회 : " + a_check + "<br>";
					let arr=endDate.split('-');
					let endTime=new Date(arr[0],arr[1],arr[2]);
					console.log(endDate);
					setInterval(function() {
						var time=new Date();
						var month=(endTime.getMonth()-time.getMonth())-1;
						var d=endTime.getDate()-time.getDate();
						var h=24-time.getHours();
						var m=60-time.getMinutes();
						var s=60-time.getSeconds();
						nowTime="마감시간 : " + month + "개월"+ d + "일" + h + "시간" +
						m + "분" + s + "초";
						timeDiv.innerHTML=nowTime;
					}, 1000)
					div.className="auc";
					div.appendChild(timeDiv);
					allauc.appendChild(div);
				}
			};
		}
		xhrList.open('get','${cp}/mainlist.do',true);
		xhrList.send();
	}
	
</script>
</html>