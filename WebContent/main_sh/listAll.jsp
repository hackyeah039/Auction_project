<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listAll.jsp</title>
<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 
<style type="text/css">
	#wrap{width: 1300px;}
	#mid{width: 100%; height: 60px; margin-top: 15px; margin-bottom: 15px; text-align: center;}
	#head{width: 100%; height: 200px; margin-top: 15px;}
	#start{border:1px solid black;}
	#topCard {
		height: 40px;
		margin: auto;
		width: 100%;
	}
	.sticky-top {
		width: 100%;
		height: 100px;
	}
	#main {
		width: 1300px;
		align-content: center;
		align-items: center;
		align-self: center;
		margin: auto;
	}
</style>
</head>
<body onload="allList(1,0,0)">

<!--   <div class="card  bg-secondary  text-white" id="topCard"></div>  -->

<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
	<div class = "collapse navbar-collapse flex-grow-1 text-left">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" id = "topCard"></a></li>
		</ul>
	</div>
	<div class = "collapse navbar-collapse flex-grow-1 text-right">	
			<ul class="navbar-nav ml-auto flex-nowrap">
				<c:choose>
					<c:when test="${sessionScope.id != null || sessionScope.adminId != null}">
							<li class="nav-item"><a class="nav-link" href="${cp }/logout.jh">로그아웃</a></li>			
						<c:if test="${sessionScope.adminId==null }">
							<li class="nav-item"><a class="nav-link" href="${cp }/myinfo.jh?m_num=${m_num}">내정보</a></li>
						</c:if>
					</c:when>
					<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="${cp }/join/join.jsp">회원가입</a></li>
							<li class="nav-item"><a class="nav-link" href="${cp }/login/login.jsp">로그인</a></li>		
					</c:otherwise>
				</c:choose>
			</ul>
	</div>
</nav>
<!-- 위에 고정되어 따라다니는 네비게이션 바 부분 -->	
<nav class="navbar bg-dark navbar-dark sticky-top text-white">
 	<a class="navbar-brand" href="${cp }/sh/testMain.do"><img src="${cp}/img/logo.png"  style="max-width: 100%; height: 80px;" ></a>
	<ul class="navbar-nav">
		
		<c:choose>
			<c:when test="${sessionScope.adminId =='admin' }" >
				<li class="nav-item"><a class="nav-link" href="${cp }/adminmain/main.jh">관리자페이지</a></li>		
			</c:when>
			<c:otherwise>
				<li class="nav-item"><a class="nav-link" href="${cp }/mypage/simplelist.do">마이페이지</a></li>		
			</c:otherwise>
					
		</c:choose>
	</ul>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="${cp }/InsertAuction.do">물품등록</a></li>
	</ul>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="${cp }/main_sh/listAll.jsp">전체물품</a></li>
	</ul>
<!-- 검색 조건 드랍박스 (영호영 검색기능 넣는 곳) -->	
<form class="form-inline" method="post" action="${cp }/sh/search.do">
	  <div class="btn-group">
			  <select class="btn outline-secondary mr-sm-2 bg-secondary" name="field">
			  	<option value="a_title">제목</option>
			  	<option value="m_id">작성자</option>
			  	<option value="a_content">내용</option>
			  </select>
	</div>
    <input class="form-control mr-sm-2" type="search" placeholder="검색" aria-label="Search" name="keyword" value=${keyword }>
    <button class="btn btn-secondary my-2 my-sm-0" type="submit">검색</button>
</form>
<!-- 까지 검색조건 -->  
</nav>
<!-- 까지 고정되어 따라다니는 네비게이션 바 -->



<div id="main">
	<script> $('.carousel').carousel({ interval: 2000}) </script>
		<div class="container">
		</div>
		<div id="demo" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">
				<!-- 슬라이드 쇼 -->
				<div class="carousel-item active">
					<!--가로-->
					<img class="d-block w-100"
						src="${cp }/main_sh/banner1.jpg?auto=compress&cs=tinysrgb&h=650&w=940"
						alt="First slide">
					<div class="carousel-caption d-none d-md-block">
					</div>
				</div>
				<div class="carousel-item">
					<img class="d-block w-100"
						src="${cp }/main_sh/banner2.jpg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
						alt="Second slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100"
						src="${cp }/main_sh/banner3.jpg?auto=compress&cs=tinysrgb&h=650&w=940"
						alt="Third slide">
				</div>
				<!-- / 슬라이드 쇼 끝 -->
				<!-- 왼쪽 오른쪽 화살표 버튼 -->
				<a class="carousel-control-prev" href="#demo" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<!-- <span>Previous</span> -->
				</a> <a class="carousel-control-next" href="#demo" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<!-- <span>Next</span> -->
				</a>
				<!-- / 화살표 버튼 끝 -->
				<!-- 인디케이터 -->
				<ul class="carousel-indicators">
					<li data-target="#demo" data-slide-to="0" class="active"></li>
					<!--0번부터시작-->
					<li data-target="#demo" data-slide-to="1"></li>
					<li data-target="#demo" data-slide-to="2"></li>
				</ul>
				<!-- 인디케이터 끝 -->
			</div>
</div>
<div id="wrap" align="center" style="float: left;">
	<div id="top">
	</div>
	<div id="mid">
		<div class="card bg-secondary text-white" id="banner">
			<table width="100%" height="60px">
				<tr>
					<td><a href="javascript:allList(0,0,1);" class="text-white font-weight-bold ">인기순</a></td>
					<td><a href="javascript:allList(0,0,2);" class="text-white font-weight-bold">추천경매</a></td>
					<td><a href="javascript:allList(0,0,3);" class="text-white font-weight-bold">마감경매</a></td>
					<td><a href="javascript:allList(1,0,0);" class="text-white font-weight-bold">전체 글</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="bottom">
		<div id="allauc"></div>
	</div>
</div>
	<div id="page" align="center" style=""></div>
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
		document.getElementById("topCard").innerHTML=nowTime;
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
					let i_path=data[i].getElementsByTagName("i_path")[0].firstChild.nodeValue;
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
					div.innerHTML="<div class='card' style='width: 320px; height: 450px; cursor:pointer;' OnClick=\"location.href ='" + "${cp}/main.do?a_num="+a_num+"'\">"+
					"<img class='card-img-top'  src='/images/"+ i_path +"' alt='Card image cap' style='width: 323px; height: 200px; align='center'>"+
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
					//"<div class='card-footer bg-dark text-white'>" + "시간 넣는 곳" + "</div>"+
					"</div>";
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
							div.innerHTML="<img style='width: 323px; height: 495px' src='${cp}/img/0.png'>"+"<input type='hidden' value=>";
							//updateBid();
							//clearInterval(timer);
						}else{
							timeDiv.innerHTML="<div class='mt-2 h-auto card bg-dark text-white' >" + nowTime + "</div>";
						}
					}, 1000)
					div.className="auc card";
					timeDiv.style="height:45px";
					timeDiv.className="card bg-dark text-white";
					div.appendChild(timeDiv);
					allauc.appendChild(div);
				}
				let pageDiv=document.createElement("div");
				<%-- 까지 각 경매글 div 만들고 출력 --%>
				<%-- 페이징 출력 부분 --%>
				if(5<startPageNum){
					pageDiv.innerHTML+="<a href='javascript:allList("+ (startPageNum-1) +",0,0);' class='btn btn-outline-secondary'>이전</a>";
				}
				for(let i=startPageNum; i<=endPageNum; i++){
					if(i==pageNum){
						//pageDiv.innerHTML+="<a href='javascript:allList("+ i +",0,0);' ><span style='color:red'>["+ i +"]</span></a>";
						pageDiv.innerHTML+="<a href='javascript:allList("+ i +",0,0);' class='btn btn-secondary btn-lg active' role='button' aria-pressed='false'>" + i + "</a>"
					}else{
						//pageDiv.innerHTML+="<a href='javascript:allList("+ i +",0,0);' class='btn btn-outline-whilte btn-white btn-lg active' role='button' aria-pressed='true'>" + i + "</a>"
						pageDiv.innerHTML+="<a href='javascript:allList("+ i +",0,0);' class='btn btn-outline-secondary'>" + i + "</a>"
					}
				}
				if(pageCnt>endPageNum){
					pageDiv.innerHTML+="<a href='javascript:allList("+ (endPageNum+1) +",0,0);' class='btn btn-outline-secondary'>다음</a>";
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
