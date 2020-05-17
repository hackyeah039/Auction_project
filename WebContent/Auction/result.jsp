<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /Auction/result.jsp -->
<c:choose>
	<c:when test="${code=='success' }">
		<h1>경매 글 등록 성공!!</h1>
	</c:when>
	<c:otherwise>
		<h1>경매 글 등록 실패!!</h1>
	</c:otherwise>
</c:choose>