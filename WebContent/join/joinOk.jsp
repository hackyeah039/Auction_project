<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

<style>
	.card{margin: auto; margin-top: 200px}
</style>
<div class="card w-75">
<div id="main" class="card-body">
	<c:choose>
		<c:when test="${code=='success' }">
			<h5 class="card-title">환영합니다!</h5>
	 	   <p class="card-text">로그인 하러 가려면 아래 버튼을 눌러주세요.</p>
	 	   <a href="${cp }/login/login.jsp" class="btn btn-primary">로그인</a>

			<br><br>
	 	   <a href="${cp }/sh/testMain.do">메인으로 가기</a>
		</c:when>
		<c:otherwise>
			<h1>회원가입 실패!</h1><br>
			<a href="${cp }/sh/testMain.do">메인으로 가기</a>
		</c:otherwise>
	</c:choose>
</div>
</div>


