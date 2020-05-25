<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>    

<style>
	#main {background-color: white; width:100%; height:1000px}
/*	#top {background-color: blue; width: 100%; height:200px;  float:none;}*/
	#content {background-color: white; width:1300px; margin: auto;}
	#divleft {background-color: white; width: 400px; height: 800px;float:left}
	#divright {background-color: white; width: 900px; height: 800px;float:left;}
	#divr{margin-left: 50px; margin-top: 50px; margin-right: 50px}
	#paging{margin: auto;}
</style>
<%
	
	String file=request.getParameter("file");
	if(file==null){
		file="/admin/adminMain.jsp";
	}
%>
<div id="main">
	<div id="top" class="jumbotron">
		<h1><a href="${cp }/main_sh/layoutTest.jsp">
		<img src="${cp }/img/4logo.png"></a></h1>
	</div>
	
	<div id="content">
		<div id="divleft" class="panel panel-default">
			<a href="${cp }/adminmain/main.jh" class="list-group-item">관리자 상황판</a><br>
			<div class="panel-heading">신고리스트</div>
			<ul class="list-group">
				<li class="list-group-item"><a href="${cp }/singo/list.jh">전체리스트</a></li>
				<li class="list-group-item"><a href="${cp }/singo/doing.jh?type=0">신고검토중</a></li>
				<li class="list-group-item"><a href="${cp }/singo/doing.jh?type=2">처리반려</a></li>
				<li class="list-group-item"><a href="${cp }/singo/doing.jh?type=1">처리완료</a></li>
			</ul>
			<div class="panel-heading">문의사항(QNA)</div>
			<ul class="list-group">
				<li class="list-group-item"><a href="${cp }/board/qnalist.jh">전체글목록</a></li>
				<li class="list-group-item"><a href="${cp }/board/qnadoing.jh?type=0">답변중</a></li>
				<li class="list-group-item"><a href="${cp }/board/qnadoing.jh?type=1">답변완료</a></li>
			</ul>
			<div class="panel-heading">회원리스트</div>
			<ul class="list-group">
				<li class="list-group-item"><a href="${cp }/members/list.jh">전체 회원리스트</a></li>
				<li class="list-group-item"><a href="${cp }/members/doing.jh?type=1">탈퇴처리중</a></li>
				<li class="list-group-item"><a href="${cp }/members/doing.jh?type=2">탈퇴 처리완료</a></li>
			</ul>
		</div>
		<div id="divright">
			<div id="divr">
  				<jsp:include page="<%=file %>"></jsp:include>
			</div>
		</div>
	</div>
</div>