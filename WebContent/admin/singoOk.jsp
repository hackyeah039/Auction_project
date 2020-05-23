<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>신고결과 페이지</h1>
<c:choose>
	<c:when test="${msg=='max'}">
		<span>더이상 내릴 신뢰도가 없습니다.</span>
	</c:when>
	<c:when test="${msg=='error' }">
		<span>처리실패!</span>
	</c:when>
	<c:otherwise>
		<span>신고자 처리 완료</span>
	</c:otherwise>
</c:choose>
<br><br>
<a href="${cp }/singo/list.jh">전체 신고리스트</a>