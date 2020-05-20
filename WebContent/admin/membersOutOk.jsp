<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${msg=='ok' }">
		<span>회원의 탈퇴 처리가 완료되었습니다.</span>
	</c:when>
	<c:when test="${msg=='error' }">
		<span>탈퇴처리가 실패하였습니다.</span>
	</c:when>
</c:choose>
<br><br>
<a href="${cp }/members/list.jh">전체 회원리스트</a>