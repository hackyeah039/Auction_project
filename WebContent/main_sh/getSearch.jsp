<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

	window.onload=searchList(1);
		
	var t = [];
	function searchList(n) {
		if(n >= 2){
			t.forEach(clearInterval);
		}
		//$(".card-group").empty();
		//$("#SearchAuc").empty();
		let pageN=n;
		let xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=function(){ <%-- 이름없는 함수 사용 --%>
				//document.getElementById("SearchAuc").innerHTML="";
				delAuc();
				delPage();
			if(xhrList.readyState==4 && xhrList.status==200){
				let xml=xhrList.responseXML;
				let nowTime="";
				let Data=xml.getElementsByTagName("data");
				let SearchAuc=document.getElementById("SearchAuc");
				<%-- 페이징 데이터 초기값 설정 --%>
				let cnt=1;
				let startPageNum="";
				let endPageNum="";
				let pageNum="";
				for(let i=0; i<Data.length; i++){
					pageNum=Data[i].getElementsByTagName("pageNum")[0].firstChild.nodeValue;
					var pageCnt=Data[i].getElementsByTagName("pageCnt")[0].firstChild.nodeValue;
					startPageNum=Data[i].getElementsByTagName("startPageNum")[0].firstChild.nodeValue;
					endPageNum=Data[i].getElementsByTagName("endPageNum")[0].firstChild.nodeValue;
					<%-- 숫자값 형변환 --%>
					pageNum*=1;
					pageCnt*=1;
					startPageNum*=1;
					endPageNum*=1;
					
					let a_num=Data[i].getElementsByTagName("a_num")[0].firstChild.nodeValue;
					let title=Data[i].getElementsByTagName("a_title")[0].firstChild.nodeValue;
					let price=Data[i].getElementsByTagName("price")[0].firstChild.nodeValue;
					let id=Data[i].getElementsByTagName("id")[0].firstChild.nodeValue;
					let a_check=Data[i].getElementsByTagName("a_check")[0].firstChild.nodeValue;
					let endDate=Data[i].getElementsByTagName("a_enddate")[0].firstChild.nodeValue;
					let bidCnt=Data[i].getElementsByTagName("bidcnt")[0].firstChild.nodeValue;
					let i_path=Data[i].getElementsByTagName("i_path")[0].firstChild.nodeValue;
					
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
					
					
					console.log("${cp}/img/"+ i_path);
					<%-- 각 경매글 div 만들고 출력하는 부분 --%>
					let div=document.createElement("div");
					div.style.textAlign="center";
					let timeDiv=document.createElement("div");
					timeDiv.id="div1" + i;
					div.innerHTML="<div class='card' style='width: 325px; height: 450px; cursor:pointer;' OnClick=\"location.href ='" + "${cp}/main.do?a_num="+a_num+"'\">"+
					"<img class='card-img-top' src='${cp}/img/"+ i_path +"' alt='Card image cap' style='width: 323px; height: 200px; align='center'>"+
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
					<%--timer(i)=--%>
					var tt = setInterval(function() {
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
					}, 1000);
					t.push(tt);
				}
				//시작
				let pageDiv=document.createElement("div");
				if(5<startPageNum){
					pageDiv.innerHTML+="<a href='javascript:searchList("+ (startPageNum-1) +");'>[이전]</a>";
				}
				for(let i=startPageNum; i<=endPageNum; i++){
					if(i==pageNum){
						pageDiv.innerHTML+="<a href='javascript:searchList("+ i +");'><span style='color:red'>["+ i +"]</span></a>";
					}else{
						pageDiv.innerHTML+="<a href='javascript:searchList("+ i +");'><span style='color:blue'>["+ i +"]</span></a>";
					}
				}
				if(pageCnt>endPageNum){
					pageDiv.innerHTML+="<a href='javascript:searchList("+ (endPageNum+1) +");'>[다음]</a>";
				}
					page.appendChild(pageDiv);
					page.style.float="bottom";
			};
		}
		xhrList.open('get','${cp}/main_sh/search.jsp?field=${field}&keyword=${keyword}&pageNum='+pageN,true);
		xhrList.send();
	}
	function delAuc() {
		var SearchAuc=document.getElementById("SearchAuc");
		var childs=SearchAuc.childNodes;//전체 자식노드(경매글) 얻어오기
		var len=childs.length;
		for(var i=len-1;i>=0;i--){
			var auctions=childs.item(i); //childs는 item(i)형식으로 배열처럼 데이터를 가져옴
			SearchAuc.removeChild(auctions);
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
</script>
<h1>검색결과</h1>
<div class="card-group" id="SearchAuc"></div>
<div id="page" align="center"></div>
