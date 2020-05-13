<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/MyPageCss.css">
<div id = "simpleList">
	<table border = 1>
		<tr>
			<th rowspan="2">MY</th>
			<th>입찰중</th>
			<th>입금요청</th>
			<th>배송</th>
			<th>판매중</th>
			<th>배송요청</th>
			<th>구매자 문의</th>
		</tr>
		<tr>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
		</tr>
	</table>
</div>

<div id = "leftMenu">
	<jsp:include page="LeftMenu.jsp"></jsp:include>
</div>

<div id = "rightContent" >
	<table border = 1> 
		<tr>
			<th>no</th><th>물품번호</th><th>물품명</th><th>현재가</th><th>입찰</th><th>조회</th><th>마감일</th><th>판매자</th><th>입찰순위</th>
		</tr>
		
		<tr>
			<c:if test="${bidlist == null }">
				안됑
			</c:if>
		</tr>
		
	</table>
</div>