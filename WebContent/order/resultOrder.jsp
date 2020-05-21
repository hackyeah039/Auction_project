<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입금결과</title>
</head>
<body>
<c:choose>
	<c:when test="${result == 'success'}">
		<h1>입금성공</h1>
		<p><a href = "${cp }/mypage/buyerTransact.do">돌아가기</a></p>
	</c:when>
	<c:otherwise>
		<h1>입금실패</h1>
		<p><a href = "${cp }/mypage/buyerTransact.do">돌아가기</a></p>
	</c:otherwise>
</c:choose>
	
</body>
</html>