<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id = "page-wrapper" style="height: 100%">

<jsp:include page="newLeftMenu.jsp"></jsp:include>

	<div id="page-content-wrapper" style="float: left ;">
    	<div class="container-fluid">
     	 	<h3>입찰 중 리스트</h3>
   		</div>
	
		<table  class="table table-bordered table-hover" border=1 style="text-align: center; 
			margin-top: 40px"> 
			<thead class = "thead">
				<tr>
					<th scope="col">NO</th>
					<th scope="col">물품명</th>
					<th scope="col">판매자</th>
					<th scope="col">마감일</th>
					<th scope="col">조회</th>
					<th scope="col">현재가</th>
					<th scope="col">입찰</th>
					<th scope="col">입찰순위</th>
				</tr>
			</thead>

			<c:if test="${getListSize == 0  }">
				<tr>
					<td colspan="8" scope="row">정보가 존재하지 않습니다.</td>
				</tr>
			</c:if>
			
			<c:set var="i" value="0"/>
			<c:forEach var="anum" items="${bidlist}">
				<c:set var="i" value = "${i+1 }"/>
				<tr>
					<td scope="row">${i}</td>
	
					<!-- 경매 정보-->
					<c:forEach var = "bidinfo" items = "${BiddingInfoList}">
						<c:if test="${ bidinfo.key == anum }">
							<td><a href = '${cp}/main.do?a_num=${anum}'>${bidinfo.value.a_title}</a></td>
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
</div>
