<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/MyPageCss.css">
<div id="simpleList">
	<table border=1>
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

<div id="leftMenu">
	<jsp:include page="LeftMenu.jsp"></jsp:include>
</div>

<div id="rightContent">
	<table border=1>
		<tr>
			<th>no</th>
			<th>물품명</th>
			<th>판매자</th>
			<th>마감일</th>
			<th>조회</th>
			<th>현재가</th>
			<th>입찰</th>
			<th>입찰순위</th>
		</tr>

		<c:forEach var="anum" items="${bidlist}">
			<tr>
				<td>${anum}</td>

				<!-- 경매 정보-->
				<c:forEach var = "bidinfo" items = "${BiddingInfoList}">
					<c:if test="${ bidinfo.key == anum }">
						<td>${bidinfo.value.a_title}</td>
						<td>${bidinfo.value.sel_Id}</td>
						<td>${bidinfo.value.a_enddate}</td>
						<td>${bidinfo.value.a_check}</td>
					</c:if>				
				</c:forEach>

				<!-- 현재 입찰 가격 -->
				<c:forEach var = "currP" items = "${currPriceList}">
					<c:if test="${ currP.key == anum }">
						<td>${currP.value}</td>
					</c:if>				
				</c:forEach>

				<!-- 입찰 등록 수-->
				<c:forEach var = "bidCount" items = "${getBidCountList}">
					<c:if test="${ bidCount.key == anum }">
						<td>${bidCount.value}</td>
					</c:if>				
				</c:forEach>

				<!-- 입찰 순위-->
				<c:forEach var = "rankList" items = "${getBidRankList}">
					<c:if test="${ rankList.key == anum }">
						<td>${rankList.value}</td>
					</c:if>				
				</c:forEach>				
			</tr>
		</c:forEach>
	</table>
</div>