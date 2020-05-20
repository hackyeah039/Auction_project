<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<div id="leftMenu">
	<jsp:include page="LeftMenu.jsp"></jsp:include>
</div>

<div id="rightContent">
	<table border=1>
		<tr>
			<th>no</th>
			<th>물품명</th>
			<th>조회</th>
			<th>시작일</th>
			<th>마감일</th>
			<th>현재가</th>
			<th>입찰</th>
			<th>남은 일자</th>
		</tr>




		<c:forEach var="anum" items="${anumList}">
			<tr>
				<td>${anum}</td>

				<!-- 경매 정보-->
				<c:forEach var = "bidinfo" items = "${BiddingInfoList}">
					<c:if test="${ bidinfo.key == anum }">
						<td>${bidinfo.value.a_title}</td>
						<td>${bidinfo.value.a_check}</td>
						<td>${bidinfo.value.a_startdate}</td>
						<td>${bidinfo.value.a_enddate}</td>

				<!-- 현재 입찰 가격 -->
				<c:forEach var = "currP" items = "${currPriceList}">
					<c:if test="${ currP.key == anum }">
						<td>${currP.value}</td>
					</c:if>				
				</c:forEach>

				<!-- 입찰 등록 수-->
				<c:forEach var = "bidCount" items = "${BidCountList}">
					<c:if test="${ bidCount.key == anum }">
						<td>${bidCount.value}</td>
					</c:if>				
				</c:forEach>

				<td>${bidinfo.value.remainDate}</td>
					</c:if>				
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>