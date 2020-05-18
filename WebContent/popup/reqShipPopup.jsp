<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송요청</title>
</head>
<body>
	<h1>구매자 정보</h1>
	<table id = "buyerInfo" border = "1">
		<tr>
			<th>이름</th>
			<th>전화번호</th>
			<th>배송지</th>			
		</tr>
		
		<tr>
			
		</tr>
	</table>
	
	<form action = "" method="post">
	<h1>운송장 번호 정보</h1>
	<table>
		<tr>
			<th>택배사</th>
			<td><input type = "text" name = ""></td>
		</tr>
		<tr>
			<th>송장번호</th>
			<td><input type = "text" name = ""></td>
		</tr>
	</table>	
	<input type = "submit" value = "등록하기">
	</form>
</body>
</html>