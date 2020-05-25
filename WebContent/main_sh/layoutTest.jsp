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
		height : 100$;
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
	
	/* footer */
    footer {
   		 margin-top : 100px;
    	 clear : both;
	     background-color: #555;
	     color: white;
	     padding: 15px;
	   	 left: 0;
	   	 bottom: 0;
	   	 width: 100%;
    }

</style>
<body>
	<script> $('.carousel').carousel({ interval: 2000 }) </script>

<!-- 
<div class="card  bg-secondary  text-white" id="topCard"></div>
 -->
<nav class="navbar bg-secondary navbar-dark text-white ">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" id = "topCard"></a></li>
	</ul>	
	<c:choose>
		<c:when test="${sessionScope.id != null || sessionScope.adminId != null}">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="${cp }/logout.jh">로그아웃</a></li>			
			</ul>
			<c:if test="${sessionScope.adminId==null }">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="${cp }/myinfo.jh?m_num=${m_num}">내정보</a></li>
				</ul>
			</c:if>
		</c:when>
		<c:otherwise>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="${cp }/join/join.jsp">회원가입</a></li>
			</ul>					
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="${cp }/login/login.jsp">로그인</a></li>		
			</ul>					
		</c:otherwise>
	</c:choose>
</nav>
<!-- 위에 고정되어 따라다니는 네비게이션 바 부분 -->	
<nav class="navbar bg-dark navbar-dark sticky-top text-white">
 	<!--  <a class="navbar-brand" href="${cp }/sh/testMain.do">Logo(누르면 Home으로 가게)</a> -->
 	<a class="navbar-brand" href="${cp }/sh/testMain.do" target="_blank"><img src="${cp}/img/logo.png"  style="max-width: 100%; height: 80px;" alt=""></a>
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

	<c:if test="${empty file }">
		<div>
			<jsp:include page="/main_sh/slideImg.jsp"/>		
		</div>
	</c:if>
	<div id="main" style="margin-bottom: 20px">
			<c:choose>
				<c:when test="${not empty file }">
					<jsp:include page="${file }"/>
				</c:when>
				<c:otherwise>
					<jsp:include page="/main_sh/main.jsp"/>
				</c:otherwise>
			</c:choose>
	</div>
	
<footer class="page-footer font-small blue">

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
  </div>
  <!-- Copyright -->

</footer>
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
