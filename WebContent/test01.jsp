<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	window.onload=function() {
		let xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=function(){ <%-- 이름없는 함수 사용 --%>
			if(xhrList.readyState==4 && xhrList.status==200){
				let xml=xhrList.responseXML;
				let nowTime="";
				let CheckData=xml.getElementsByTagName("CheckData");
				let JjimData=xml.getElementsByTagName("JjimData");
				let EndData=xml.getElementsByTagName("EndData");
				let checkAuc=document.getElementById("checkAuc");
				let jjimAuc=document.getElementById("jjimAuc");
				let endAuc=document.getElementById("endAuc");
				
				for(let i=0; i<CheckData.length; i++){
					let a_num=CheckData[i].getElementsByTagName("a_num")[0].firstChild.nodeValue;
					let title=CheckData[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					let price=CheckData[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					let id=CheckData[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					let a_check=CheckData[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					let endDate=CheckData[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					let bidCnt=CheckData[i].getElementsByTagName("bidcnt")[0].firstChild.nodeValue;
					console.log(endDate);
					
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
				
					<%-- 각 경매글 div 만들고 출력하는 부분 --%>
					let div=document.createElement("div");
					div.style.textAlign="center";
					let timeDiv=document.createElement("div");
					timeDiv.id="div1" + i;
					div.innerHTML="<div class='card' style='width: 325px; height: 450px; cursor:pointer;' OnClick=\"location.href ='" + "#" +"'\">"+
					"<img class='card-img-top' src='.../100px180/' alt='Card image cap'>"+
					"<div class='card-body'>"+
						"<h5 class='card-title'>" + title + "</h5>"+
						"<p class='card-text'>" + price + "</p>"+
					"</div>"+
						"<table class='table table-bordered'>"+
						"<tr>"+
							"<td><h6>입찰 수</h6>"+ bidCnt +"</td>"+
							"<td><h6>조회 수</h6>"+ a_check +"</td>"+
						"</tr>"+
					"</table>"+
					"<div class='card-footer bg-dark text-white' id='time"+ i +"'>" + "시간 넣는 곳" + "</div>"+
					"</div>";
					checkAuc.appendChild(div);
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
							$(".card-footer:nth("+i +")").html(nowTime);
						}
					}, 1000)
				}
				for(let i=0; i<JjimData.length; i++){
					let a_num=JjimData[i].getElementsByTagName("a_num")[0].firstChild.nodeValue;
					let title=JjimData[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					let price=JjimData[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					let id=JjimData[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					let a_check=JjimData[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					let endDate=JjimData[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					let bidCnt=JjimData[i].getElementsByTagName("bidcnt")[0].firstChild.nodeValue;
				
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
				
					<%-- 각 경매글 div 만들고 출력하는 부분 --%>
					let div=document.createElement("div");
					div.style.textAlign="center";
					let timeDiv=document.createElement("div");
					timeDiv.id="div2" + i;
					div.innerHTML="<div class='card' style='width: 325px; height: 450px; cursor:pointer;' OnClick=\"location.href ='" + "#" +"'\">"+
					"<img class='card-img-top' src='.../100px180/' alt='Card image cap'>"+
					"<div class='card-body'>"+
						"<h5 class='card-title'>" + title + "</h5>"+
						"<p class='card-text'>" + price + "</p>"+
					"</div>"+
						"<table class='table table-bordered'>"+
						"<tr>"+
							"<td><h6>입찰 수</h6>"+ bidCnt +"</td>"+
							"<td><h6>조회 수</h6>"+ a_check +"</td>"+
						"</tr>"+
					"</table>"+
					"<div class='card-footer bg-dark text-white' id='time"+ i+4 +"'>" + "시간 넣는 곳" + "</div>"+
					"</div>";
					jjimAuc.appendChild(div);
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
							//let div2=document.getElementById("div2"+i);
							$(".card-footer:nth("+i+")").html(nowTime);
						}
					}, 1000)
				}
				for(let i=0; i<EndData.length; i++){
					let a_num=EndData[i].getElementsByTagName("a_num")[0].firstChild.nodeValue;
					let title=EndData[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					let price=EndData[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					let id=EndData[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					let a_check=EndData[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					let endDate=EndData[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					let bidCnt=EndData[i].getElementsByTagName("bidcnt")[0].firstChild.nodeValue;
				
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
				
					<%-- 각 경매글 div 만들고 출력하는 부분 --%>
					let div=document.createElement("div");
					div.style.textAlign="center";
					let timeDiv=document.createElement("div");
					timeDiv.id="div3" + i;
					div.innerHTML="<div class='card' style='width: 325px; height: 450px; cursor:pointer;' OnClick=\"location.href ='" + "#" +"'\">"+
					"<img class='card-img-top' src='.../100px180/' alt='Card image cap'>"+
					"<div class='card-body'>"+
						"<h5 class='card-title'>" + title + "</h5>"+
						"<p class='card-text'>" + price + "</p>"+
					"</div>"+
						"<table class='table table-bordered'>"+
						"<tr>"+
							"<td><h6>입찰 수</h6>"+ bidCnt +"</td>"+
							"<td><h6>조회 수</h6>"+ a_check +"</td>"+
						"</tr>"+
					"</table>"+
					"<div class='card-footer bg-dark text-white' id='time"+ i+8 +"'>" + "시간 넣는 곳" + "</div>"+
					"</div>";
					endAuc.appendChild(div);
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
						}else{
							$(".card-footer:nth("+i+8 +")").html(nowTime);
						}
					}, 1000)
				}
			}
		}
		xhrList.open('get','${cp}/sh/recomList.do',true);
		xhrList.send();
	}

</script>
<h1>인기경매</h1>
<div class="card-group" id="checkAuc"></div>
<h1>추천경매</h1>
<div class="card-group" id="jjimAuc"></div>
<h1>마감임박</h1>
<div class="card-group" id="endAuc"></div>