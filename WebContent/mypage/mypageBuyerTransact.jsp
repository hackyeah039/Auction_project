<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="simpleList">
	<jsp:include page="simpleList.jsp"></jsp:include>
</div>

<div id="leftMenu">
	<jsp:include page="LeftMenu.jsp"></jsp:include>
</div>
<div id="rightContent">
	<ul>
		<li><a href="#">전체</a></li>
		<li><a href="#">미입금</a></li>
		<li><a href="#">입금완료</a></li>
	</ul>

	<table border="1">
		<tr>
			<th>NO</th>
			<th>물품명</th>
			<th>구매가격</th>
			<th>거래상태</th>
			<th>낙찰/구매일</th>
			<th>판매자</th>
			<th>입금기한</th>
		</tr>

		<c:forEach var="anum" items="${anumlist}">
			<tr>
				<td>${anum }</td>

				<!-- 경매 정보-->
				<c:forEach var="bidinfo" items="${tranBidList}">
					<c:if test="${ bidinfo.a_num == anum }">
						<td>${bidinfo.bid_price}</td>
						<td>${bidinfo.bid_date}</td>
					</c:if>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>