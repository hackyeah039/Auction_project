<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="leftMenu">
	<jsp:include page="LeftMenu.jsp"></jsp:include>
</div>
<div id="rightContent">

	<table border="1">
		<tr>
			<th>NO</th>
			<th>물품명</th>
			<th>조회</th>
			<th>마감일</th>
			<th>입찰</th>
			<th>낙찰가격</th>
			<th>판매자</th>
			<th>입찰결과</th>
		</tr>

		<c:if test="${getListSize == 0  }">
			<tr>
				<td colspan="8">정보가 존재하지 않습니다.</td>
			</tr>
		</c:if>

		<c:forEach var="anum" items="${completedTranList}">
			<tr>
				<td>${anum.a_num }</td>

				<!-- 물품명, 조회, 마감일 -->
				<td>${anum.a_title}</td>
				<td>${anum.a_check}</td>
				<td>${anum.a_enddate}</td>

				<!-- 입찰수-->
				<c:forEach var="bidCountList" items="${bidCountList}">
					<c:if test="${ bidCountList.key == anum.a_num }">
						<td>${bidCountList.value}</td>
					</c:if>
				</c:forEach>
				
				<!-- 낙찰가격-->
				<c:forEach var="bidnum" items="${bidList}">
					<c:if test="${ bidnum.a_num == anum.a_num }">
						<td>${bidnum.bid_price}</td>
					</c:if>
				</c:forEach>

				<!-- 판매자ID -->
				<c:forEach var="sellerId" items="${sellerList}">
					<c:if test="${ sellerId.key == anum.a_num }">
						<td>${sellerId.value}</td>
					</c:if>
				</c:forEach>

				<!-- 입찰결과 -->
				<c:forEach var="bidResult" items="${bidResult}">
					<c:if test="${ bidResult.key == anum.a_num }">
						<td>${bidResult.value}</td>
					</c:if>
				</c:forEach>
				
			</tr>
		</c:forEach>
	</table>
</div>


