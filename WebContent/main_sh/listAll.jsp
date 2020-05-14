<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listAll.jsp</title>
<style type="text/css">
	#wrap{width: 1300px;}
	#mid{border:1px solid black;}
	#start{border:1px solid black;}
	.auc{width: 300px;height: 400px;border:1px solid #aaa;margin-bottom: 5px;
			margin-left: 20px; margin-top: 10px;}
</style>
</head>
<body onload="allList(1)">
<h1>메인화면</h1>
<div id="wrap">
<h1>wrap 부분</h1>
	<div id="head"><h2>헤드 부분</h2>
	</div>
	<div id="mid"><h2>미드 부분</h2>
		<div id="banner">이벤트 배너</div><br>
	</div>
	<div id="bottom">
		<div id="allauc"></div>
	</div>
	<div id="bottom2" style=fol>
		<div id="page" align="center"></div>
	</div>	
</div>
</body>
<script type="text/javascript">
	function allList(num) {
		delAuc();
		delPage();
		var xhrList=null;
		xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=function(){
			if(xhrList.readyState==4 && xhrList.status==200){
				var nowTime="";
				var xml=xhrList.responseXML;
				var data=xml.getElementsByTagName("data");
				var allauc=document.getElementById("allauc");
				var page=document.getElementById("page");
				var cnt=1;
				for(var i=0;i<data.length;i++){
					<%--페이징 데이터 부분 --%>
					var pageNum=data[i].getElementsByTagName("pageNum")[0].firstChild.nodeValue;
					var pageCnt=data[i].getElementsByTagName("pageCnt")[0].firstChild.nodeValue;
					var startPageNum=data[i].getElementsByTagName("startPageNum")[0].firstChild.nodeValue;
					var endPageNum=data[i].getElementsByTagName("endPageNum")[0].firstChild.nodeValue;
					<%--까지 페이징 데이터 --%>
					var title=data[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					var price=data[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					var id=data[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					var a_check=data[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					var endDate=data[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					var startDiv=document.createElement("div");
					var div=document.createElement("div");
					div.style.textAlign="center";
					let timeDiv=document.createElement("div");
					div.innerHTML="<a href='' style='text-decoration: none; color: black;'>"+
					"제목 : " + title + "<br>" +
					"가격 : " + price + "<br>"+
					"작성자 : " + id + "<br>"+
					"조회수 : " + a_check + "<br>" +"</a>";
					div.style.float="left";
					let arr=endDate.split('-');
					let endTime=new Date(arr[0],arr[1],arr[2]);
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
					
					var pageDiv=document.createElement("div");
					
					pageDiv.innerHTML="<h3>페이지 부분22</h3>";
				}
				if(10<startPageNum){
					pageDiv.innerHTML+="<a href='javascript:allList("+ startPageNum-1 +");'>[이전]</a>";
				}
				for(var i=startPageNum; i<=endPageNum; i++){
					if(i==pageNum){
						pageDiv.innerHTML+="<a href='javascript:allList("+ i +");'><span style='color:red'>["+ i +"]</span></a>";
					}else{
						pageDiv.innerHTML+="<a href='javascript:allList("+ i +");'><span style='color:blue'>["+ i +"]</span></a>";
					}
				}
				if(pageCnt>endPageNum){
					pageDiv.innerHTML+="<a href='javascript:allList("+ endPageNum+1 +");'>[다음]</a>";
				}
					
					page.appendChild(pageDiv);
					page.style.float="bottom";
			};
		}
		xhrList.open('get','${cp}/mainlist.do?pageNum='+num,true);
		xhrList.send();
	}
	function selList(num) {
		allList(num-1 );
	}
	function delAuc() {
		var allauc=document.getElementById("allauc");
		var childs=allauc.childNodes;//전체 자식노드(경매글) 얻어오기
		var len=childs.length;
		for(var i=len-1;i>=0;i--){
			var auctions=childs.item(i); //childs는 item(i)형식으로 배열처럼 데이터를 가져옴
			allauc.removeChild(auctions);
		}
	}
	function delPage() {
		var page=document.getElementById("page");
		var childs=page.childNodes;
		console.log("dddddd:" + childs.length)
		var len=childs.length;
		for(var i=len-1;i>=0;i--){
			console.log('a')
			var pages=childs.item(i);
			page.removeChild(pages);
		}
	}
</script>
</html>