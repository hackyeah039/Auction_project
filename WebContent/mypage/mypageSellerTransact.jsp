<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id = "page-wrapper">
<div id="leftMenu">
	<jsp:include page="newLeftMenu.jsp"></jsp:include>
</div>

<div id="page-content-wrapper">
    <div class="container-fluid">
      <h1>거래 중인 리스트</h1>
    </div>

	<table  class="table table-bordered" border=1 style="text-align: center; 
		margin-top: 40px">
		<thead class = "thead">

		<tr>
			<th scope="col">NO</th>
			<th scope="col">물품명</th>
			<th scope="col">구매가격</th>
			<th scope="col">낙찰/구매일</th>
			<th scope="col">구매자</th>
			<th scope="col">거래상태</th><!-- 입금확인중, 입금완료,배송요청 -->
			<th scope="col">입금기한</th>
		</tr>

		<c:if test="${getListSize == 0  }">
			<tr> 
				<td colspan="7" scope="row">정보가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<c:set var="i" value="0"/>
		<c:forEach var="anum" items="${forSellerTranList}">
			
			<c:set var="i" value = "${i+1 }"/>
			<tr>
				<td scope="row">${i}</td>

				<!-- 물품명 -->
				<c:forEach var="titleList" items="${auctiontitleList}">
					<c:if test="${ titleList.key == anum }">
						<td>${titleList.value}</td>
					</c:if>
				</c:forEach>

				<!-- 경매 정보(물품명, 구매가격,낙찰일)-->
				<c:forEach var="bidinfo" items="${tranBidList}">
					<c:if test="${ bidinfo.a_num == anum}">
						<td>${bidinfo.bid_price}</td>
						<td>${bidinfo.bid_date}</td>
					</c:if>
				</c:forEach>
				
								
				<!-- 구매자  -->
				<c:forEach var="sellerId" items="${buyerId}">
					<c:if test="${ sellerId.key == anum }">
						<td>${sellerId.value}</td>
					</c:if>
				</c:forEach>

				<!-- 입금기한, 거래상태  -->
				<c:forEach var="payVo" items="${paymentList}">
					<c:if test="${ payVo.key == anum }">
						<c:choose>
							<c:when test="${payVo.value.pay_status == 0 }"><td>입금확인중</td></c:when>
							<c:when test="${payVo.value.pay_status == 1 }"><td><button id="popbutton">배송요청</button></td></c:when>
						</c:choose>
						<td>${payVo.value.pay_deadline}</td>
					</c:if>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>
<script type="text/javascript">

/*
function showPopup(paynum,anum){
	 window.open("${cp}/popup/reqShipPopup.do?paynum="+paynum+"&anum="+anum, "배송요청", 
			 "width=400, height=300, left=100, top=50");
 }
 
*/ 
 
 $(function(){
	    $("#popbutton").click(function(){
	        $('div.modal').modal({
	                      remote : '${cp}/popup/reqShipPopup.do'
	                      
	                });
	    })
	})

</script>