<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id = "page-wrapper">
<div id="leftMenu">
	<jsp:include page="newLeftMenu.jsp"></jsp:include>
</div>

<div id="page-content-wrapper">
    <div class="container-fluid">
      <h1>구매종료 리스트</h1>
    </div>
	
	<table  class="table table-bordered" border=1 style="text-align: center; 
		margin-top: 40px"> 
		<thead class = "thead">
		<tr>
			<th scope="col">NO</th>
			<th scope="col">물품명</th>
			<th scope="col">조회</th>
			<th scope="col">마감일</th>
			<th scope="col">입찰</th>
			<th scope="col">낙찰가격</th>
			<th scope="col">판매자</th>
			<th scope="col">입찰결과</th>
		</tr>

		<c:if test="${getListSize == 0  }">
			<tr>
				<td colspan="8" scope="row">>정보가 존재하지 않습니다.</td>
			</tr>
		</c:if>

		<c:set var="i" value="0"/>
		<c:forEach var="anum" items="${completedTranList}">
			<c:set var="i" value = "${i+1 }"/>
			<tr>
				<td scope="row">${i}</td>

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
</div>

