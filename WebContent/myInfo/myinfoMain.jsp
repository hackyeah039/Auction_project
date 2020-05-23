<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>   
<%
	String file=request.getParameter("file");
	if(file==null){
		file="/myinfo/myinfoUpdate.jsp";
	}
%>
<style>
	#content{width: 700px; margin: auto; margin-top: 200px}
</style>

<!-- 네비바 -->
<div id="main">

	<div id="header">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		    <div class="navbar-nav">
		      <a class="nav-item nav-link" href="${cp }/main_sh/layoutTest.jsp">메인으로 가기</a>
		      <a class="nav-item nav-link" href="${cp }/myinfo.jh?m_num=${m_num}">정보수정하기</a>
		      <a class="nav-item nav-link" href="${cp }/myInfo/myinfoMain.jsp?file=pwdUpdate.jsp?m_num=${m_num}">비밀번호 변경</a>
		      <a class="nav-item nav-link" href="${cp }/myInfo/myinfoMain.jsp?file=membersOut.jsp?m_num=${m_num}">탈퇴하기</a>
		    </div>
		  </div>
		</nav>
	</div>
	<div id="content">
		<jsp:include page="<%=file %>"></jsp:include>
	</div>
</div>