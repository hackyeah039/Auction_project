<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#main {background-color: orange; width:1300px; height:1000px}
	#top {background-color: blue; width: 1300px; height:200px;  float:none;}
	#content {background-color: orange; width:1300px}
	#divleft {background-color: gray; width: 400px; height: 800px;float:left}
	#divright {background-color: #dddddd; width: 900px; height: 800px;float:left}
</style>
<%
	
	String file=request.getParameter("file");
	if(file==null){
		file="/admin/adminMain.jsp";
	}
%>
<div id="main">
	<div id="top">머리부분</div>
	<div id="content">
		<div id="divleft">
			<span>신고리스트</span>
			<ul>
				<li><a href="${cp }/singo.list.jh">전체리스트</a></li>
				<li><a href="${cp }/singo.doing.jh?type=0">신고검토중</a></li>
				<li><a href="${cp }/singo.doing.jh?type=2">처리반려</a></li>
				<li><a href="${cp }/singo.doing.jh?type=1">처리완료</a></li>
			</ul>
			<span>문의사항(QNA)</span>
			<ul>
				<li><a href="${cp }/board.qnalist.jh">전체글목록</a></li>
				<li><a href="${cp }/board.qnadoing.jh?type=0">답변중</a></li>
				<li><a href="${cp }/board.qnadoing.jh?type=1">답변완료</a></li>
			</ul>
			<span>회원리스트</span>
			<ul>
				<li><a href="${cp }/members.list.jh">전체 회원리스트</a></li>
				<li><a href="${cp }/members.doing.jh?type=1">탈퇴처리중</a></li>
				<li><a href="${cp }/members.doing.jh?type=2">탈퇴 처리완료</a></li>
			</ul>
		</div>
		<div id="divright">
			<jsp:include page="<%=file %>"></jsp:include>
		</div>
	</div>
</div>