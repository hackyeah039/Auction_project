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
	<form action="${cp }/order/order.do" method="get"
		onsubmit="return submitClick()">
		<table border="1">
			<tr>
				<th>NO</th>
				<th>물품명</th>
				<th>구매가격</th>
				<th>낙찰/구매일</th>
				<th>판매자</th>
				<th>입금기한</th>
				<th>거래상태</th>
				<th><input type="checkbox"></th>
			</tr>

			<c:if test="${getListSize == 0  }">
				<tr>
					<td colspan="7">정보가 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="anum" items="${tranBidList}">
				<tr>
					<td>${anum.a_num }</td>

					<!-- 물품명 -->
					<c:forEach var="titleList" items="${auctionTitleList}">
						<c:if test="${ titleList.key == anum.a_num }">
							<td>${titleList.value}</td>
						</c:if>
					</c:forEach>

					<!-- 경매 정보(물품명, 구매가격,낙찰일)-->
					<c:forEach var="bidinfo" items="${tranBidList}">
						<c:if test="${ bidinfo.a_num == anum.a_num }">
							<td>${bidinfo.bid_price}</td>
							<!--<c:set var="price" value="${bidinfo.bid_price}" />-->
							<td>${bidinfo.bid_date}</td>
						</c:if>
					</c:forEach>


					<!-- 판매자  -->
					<c:forEach var="sellerId" items="${sellerIdList}">
						<c:if test="${ sellerId.key == anum.a_num }">
							<td>${sellerId.value}</td>
						</c:if>
					</c:forEach>

					<!-- 입금기한, 거래상태  -->
					<c:forEach var="payVo" items="${paymentList}">
						<c:if test="${ payVo.key == anum.a_num }">
							<td>${payVo.value.pay_deadline}</td>
							<c:choose>
								<c:when test="${payVo.value.pay_status == 0 }">
									<td>미입금</td>
									<td><input type="checkbox" id="checkbox" name="checkbox"
										value="${anum.a_num }"></td>
								</c:when>
								<c:when test="${payVo.value.pay_status == 1 }">
									<td>입금</td>
									<td><input type="checkbox" id="checkbox"
										disabled="disabled"></td>
								</c:when>
							</c:choose>
						</c:if>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<br> 
		<input type="submit" value="입금하기" id="sbtn">
	</form>

</div>

<script type="text/javascript">
	function submitClick(){
		
		var ckb = document.getElementsByName("checkbox");
		var length = ckb.length;
		var count = 0;
		
		for(var i = 0 ; i < length; i++){
			if(ckb[i].checked == true){
				count++;		
			}
		}
		
		alert(count);
		
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}
</script>
