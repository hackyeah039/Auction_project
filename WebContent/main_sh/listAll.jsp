<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listAll.jsp</title>
<style type="text/css">
	#ontop{width: 950px; border:1px solid black;}
	#wrap{width: 1300px;}
	#mid{width: 100%; height: 60px;border:1px solid black; margin-top: 15px; margin-bottom: 15px; text-align: center;}
	#head{width: 100%; height: 200px;border: 1px solid blue; margin-top: 15px;}
	#start{border:1px solid black;}
	.auc{width: 300px;height: 400px;
		border:1px solid black;margin-bottom: 5px;
		margin-left: 20px; margin-top: 10px;}
</style>
</head>
<body onload="allList(1,0,0)">
<div id="wrap">
	<div id="top">
		<div id="ontop" style="float: left"></div>
		<div id="righttop">
			<div id="login" style="float: left; margin-left: 40px;">로그인</div>
			<div id="join" style="float: left; margin-left: 40px; margin-right: 40px">회원가입</div>
			<div id="mypage" style="">마이페이지</div>
		</div>
	</div>
<div id="head"><h2>이벤트 배너</h2></div>
	<div id="mid">
		<div id="banner">
			<table width="100%" height="60px" border="1">
				<tr>
					<td><a href="javascript:allList(0,0,1);">인기순</a></td>
					<td><a href="javascript:allList(0,0,2);">추천경매</a></td>
					<td><a href="javascript:allList(0,0,3);">마감경매</a></td>
					<td><a href="javascript:allList(1,0,0);">전체 글</a></td>
				</tr>
			</table>
		</div>
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
	<%-- 최상단 시간 출력 부분 --%>
	setInterval(function() {
		var time=new Date();
		var year=time.getFullYear();
		var month=(time.getMonth()+1);
		var date=time.getDate();
		var hour=time.getHours();
		var min=time.getMinutes();
		var sec=time.getSeconds();
		var nowTime="현재시간 : " + year + "년" + month + "월"+ date + "일" + hour + "시" +
		min + "분" + sec + "초";
		document.getElementById("ontop").innerHTML=nowTime;
	}, 1000)
	<%-- 까지 최상단 시간 출력  --%>

	<%-- 전체글 뽑아오는 함수 --%>
	function allList(num1,num2,num3) {
		let pageN=num1;
		let cateN=num2;
		let recomN=num3;
		delAuc(); <%-- 기존 경매글 지우기 --%>
		delPage(); <%-- 기존  페이지 지우기 --%>
		xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=function(){ <%-- 이름없는 함수 사용 --%>
			if(xhrList.readyState==4 && xhrList.status==200){
				let nowTime="";
				let xml=xhrList.responseXML;
				let data=xml.getElementsByTagName("data");
				let allauc=document.getElementById("allauc");
				var page=document.getElementById("page");
				let cnt=1;
				let startPageNum="";
				let endPageNum="";
				let pageNum="";
				for(var i=0;i<data.length;i++){
					<%--페이징 데이터 부분 --%>
					pageNum=data[i].getElementsByTagName("pageNum")[0].firstChild.nodeValue;
					var pageCnt=data[i].getElementsByTagName("pageCnt")[0].firstChild.nodeValue;
					startPageNum=data[i].getElementsByTagName("startPageNum")[0].firstChild.nodeValue;
					endPageNum=data[i].getElementsByTagName("endPageNum")[0].firstChild.nodeValue;
					<%-- 숫자값 형변환 --%>
					pageNum*=1;
					pageCnt*=1;
					startPageNum*=1;
					endPageNum*=1;
					<%--까지 페이징 데이터 --%>
					
					<%--xml데이터 가져오는 부분 --%>
					let a_num=data[i].getElementsByTagName("a_num")[0].firstChild.nodeValue;
					let title=data[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					let price=data[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					let id=data[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					let a_check=data[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					let endDate=data[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					let bidCnt=data[i].getElementsByTagName("bidcnt")[0].firstChild.nodeValue;
					<%--까지 xml데이터 가져오기 --%>
					<%-- 마감시간 가져와서 인트형태로 형변환 부분 --%>
					let endYear=endDate.substring(0, 4);
					let endMonth=endDate.substring(4, 6);
					let endDay=endDate.substring(6, 8);
					let endHour=endDate.substring(8, 10);
					let endMin=endDate.substring(10, 12);
					let endSec=endDate.substring(12, 14);
					endYear*=1;
					endMonth*=1;
					endDay*=1;
					endHour*=1;
					endMin*=1;
					endSec*=1;
					let endTime=new Date(endYear,(endMonth-1),endDay,endHour,endMin,endSec).getTime();
					<%-- 까지 마감시간 가져와서 인트형태로 형변환 --%>
					
					<%-- 각 경매글 div 만들고 출력하는 부분 --%>
					let startDiv=document.createElement("div");
					let div=document.createElement("div");
					div.style.textAlign="center";
					let timeDiv=document.createElement("div");
					div.innerHTML="<a href='${cp}/sh/checkup.do?a_num="+ a_num +"' style='text-decoration: none; color: black;'>"+
					"제목 : " + title + "<br>" +
					"가격 : " + price + "<br>"+
					"작성자 : " + id + "<br>"+
					"조회수 : " + a_check + "<br>" +
					"입찰수 : " + bidCnt + "<br>" +"</a>";
					div.style.float="left";
					//let timer(i);
					<%--timer(i)=--%>setInterval(function() {
						let nowTime=new Date().getTime();
						let diff=(endTime-nowTime);
						let days=Math.floor(diff / (1000 * 60 * 60 * 24));//일
						let hours=Math.floor((diff / (1000*60*60)) % 24);//시간
						let minutes=Math.floor((diff / (1000*60)) % 60);//분
						let seconds=Math.floor((diff / 1000) % 60);//초
						nowTime="마감시간 : " + days + "일" + hours + "시간" +
								minutes + "분" + seconds + "초";
						if(days<0){
							div.innerHTML="<img src='${cp}/img/0.png'>"+"<input type='hidden' value=>";
							//updateBid();
							//clearInterval(timer);
						}else{
							timeDiv.innerHTML=nowTime;
						}
					}, 1000)
					div.className="auc";
					div.appendChild(timeDiv);
					allauc.appendChild(div);
				}
				let pageDiv=document.createElement("div");
				<%-- 까지 각 경매글 div 만들고 출력 --%>
				<%-- 페이징 출력 부분 --%>
				if(5<startPageNum){
					pageDiv.innerHTML+="<a href='javascript:allList("+ (startPageNum-1) +",0,0);'>[이전]</a>";
				}
				for(let i=startPageNum; i<=endPageNum; i++){
					if(i==pageNum){
						pageDiv.innerHTML+="<a href='javascript:allList("+ i +",0,0);'><span style='color:red'>["+ i +"]</span></a>";
					}else{
						pageDiv.innerHTML+="<a href='javascript:allList("+ i +",0,0);'><span style='color:blue'>["+ i +"]</span></a>";
					}
				}
				if(pageCnt>endPageNum){
					pageDiv.innerHTML+="<a href='javascript:allList("+ (endPageNum+1) +",0,0);'>[다음]</a>";
				}
		
					page.appendChild(pageDiv);
					page.style.float="bottom";
			};
			<%-- 까지 페이징 출력 --%>
			
		}
		xhrList.open('get','${cp}/sh/mainlist.do?pageNum='+pageN+"&cateNum="+cateN+"&recomNum="+recomN,true);
		xhrList.send();
	}
	
	<%-- 기존의 경매글과 페이지 모두 지워주는 메소드 부분 --%>
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
		var len=childs.length;
		for(var i=len-1;i>=0;i--){
			var pages=childs.item(i);
			page.removeChild(pages);
		}
	}
	function updateBid(){
		location.href="${cp}/sh/updateBid.do";
		//window.onload();
	}
</script>
