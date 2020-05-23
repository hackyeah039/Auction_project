<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"><되/script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

<h3>신고처리 결과</h3><br>
<c:choose>
	<c:when test="${msg=='max'}">
		<span>더이상 내릴 신뢰도가 없습니다.</span>
	</c:when>
	<c:when test="${msg=='error' }">
		<span>처리실패!</span>
	</c:when>
	<c:otherwise>
		<span>신고자 처리가 완료되었습니다!</span>
	</c:otherwise>
</c:choose>
<br><br>
<a href="${cp }/singo/list.jh">전체 신고리스트</a>