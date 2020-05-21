<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면 테스트용 입니다.</title>
</head>
<body>
<h1>메인화면 테스트용 입니다.</h1>
<ul>
	<li><a href="${cp }/join/join.jsp">회원가입</a></li>
	<li><a href="${cp }/logout.jh">로그아웃</a></li>		
	<li><a href="${cp }/myinfo.jh?m_num=${m_num}">내정보</a></li>		
	<li><a href="${cp }/login/login.jsp">로그인</a></li>
	<li><a href="${cp }/adminmain/main.jh">관리자 페이지</a></li>
	세션아이디 : ${id}
	세션회번호 : ${m_num}
	관리자아이디 : ${adminId}
</ul>
</body>
<script type="text/javascript"></script>
</html>