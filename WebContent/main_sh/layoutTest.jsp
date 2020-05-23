<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout.jsp</title>
	<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 
</head>
<body>
	<script> $('.carousel').carousel({ interval: 2000 }) </script>
<style>
	#wrap {
		width: 100%;
		height: 100%;
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
	#topCard {
		height: 40px;
		margin: auto;
		width: 100%;
	}
</style>
	<%
		String head = "/main_sh/headerTest.jsp";
		String hair = "/main_sh/hairTest.jsp";
	%>
<div class="card  bg-secondary  text-white" id="topCard"></div>

<!-- 위에 고정되어 따라다니는 네비게이션 바 부분 -->	
<nav class="navbar bg-dark navbar-dark sticky-top text-white">
 	<a class="navbar-brand" href="#">Logo(누르면 Home으로 가게)</a>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
	</ul>	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
	</ul>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="#">마이페이지</a></li>
	</ul>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="${cp }/InsertAuction.do">물품등록</a></li>
	</ul>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="#">전체물품</a></li>
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
	<div id="main">
		<div>
			<c:choose>
				<c:when test="${not empty file }">
					<jsp:include page="${file }"/>
				</c:when>
				<c:otherwise>
					<jsp:include page="/main_sh/main.jsp"/>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
			<div class="jumbotron jumbotron-fluid">
				<div class="container">
					<h1 class="display-4" style="align-content: center;">제작자</h1>
					<p class="lead">이상훈</p>
				</div>
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
		document.getElementById("topCard").innerHTML=nowTime;
	}, 1000)
	<%-- 까지 최상단 시간 출력  --%>
</script>
</html>