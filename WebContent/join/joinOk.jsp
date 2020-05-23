<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${code=='success' }">
		<h1>환영합니다~</h1>
	</c:when>
	<c:otherwise>
		<h1>회원가입 실패!</h1>
	</c:otherwise>
</c:choose>
<a href="${cp }/sh/testMain.do">메인으로 가기</a>