<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	window.onload=function() {
		let xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=function(){ <%-- 이름없는 함수 사용 --%>
			console.log("콜백전");
			if(xhrList.readyState==4 && xhrList.status==200){
			console.log("콜백입장");
				let xml=xhrList.responseXML;
				let nowTime="";
				let Data=xml.getElementsByTagName("data");
				let SearchAuc=document.getElementById("SearchAuc");
				
				for(let i=0; i<Data.length; i++){
					let a_num=Data[i].getElementsByTagName("a_num")[0].firstChild.nodeValue;
					let title=Data[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					let price=Data[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					let id=Data[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					let a_check=Data[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					let endDate=Data[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					let bidCnt=Data[i].getElementsByTagName("bidcnt")[0].firstChild.nodeValue;
					
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
					"<h4 class='card-title'>" + title + "</h4>"+
					"<h3 class='card-text text-primary'>" + price + "원</h3>"+
					"</div>"+
						"<table class='table table-bordered'>"+
						"<tr>"+
							"<td><h5>입찰 수</h5><h5>"+bidCnt +"</h5></td>"+
							"<td><h5>조회 수</h5><h5>"+a_check +"</h5></td>"+
						"</tr>"+
					"</table>"+
					"<div class='card-footer bg-dark text-white'>" + "시간 넣는 곳" + "</div>"+
					"</div>";
					SearchAuc.appendChild(div);
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
							//$("#time:nth("+i +")").html(nowTime);
						}
					}, 1000)
				}
			}
		}
		xhrList.open('get','${cp}/main_sh/search.jsp?field=${field}&keyword=${keyword}',true);
		xhrList.send();
	}

</script>
<h1>검색결과</h1>
<div class="card-group" id="SearchAuc"></div>
