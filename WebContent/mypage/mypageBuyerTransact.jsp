<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
		onsubmit="return submitClick()" >
		<!-- onsubmit="return submitClick()" -->
		<table border="1" id = "table">		
			<tr>
				<th>NO</th>
				<th>물품명</th>
				<th>구매가격</th>
				<th>낙찰/구매일</th>
				<th>판매자</th>
				<th>입금기한</th>
				<th>거래상태</th>
				<th><input type="checkbox" onclick = "allClick()"></th>
			</tr>

			<c:if test="${getListSize == 0  }">
				<tr>
					<td colspan="8">정보가 존재하지 않습니다.</td>
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
										value="${payVo.value.pay_num }"></td>
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
		<input type="hidden" id="message" name = "message">
		<input type="submit" value="입금하기" id="sbtn">
	</form>

</div>

<script type="text/javascript">
	function submitClick(){
		
		var table = document.getElementById("table");
		var ckb = document.getElementsByName("checkbox");
		var hidden = document.getElementById("message");
		var length = ckb.length;
		var count = 0;
		var message = "";
		
		for(var i = 0 ; i < length; i++){
			if(count > 0){
				message += ":"
			}
			if(ckb[i].checked == true){
				var row = ckb[i].parentNode.parentNode;
                message += row.cells[0].innerHTML;
                message += ":" + row.cells[1].innerHTML;
                message += ":" + row.cells[2].innerHTML;
				count++;		
			}
		}
		
		hidden.value = message;
		
		if(count > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	var checked1 = true;
	var checked2 = false;

	function allClick(){
		
		console.log(checked1);
		
		var temp;
		var ckb = document.getElementsByName("checkbox");
		
		for(var i = 0; i < ckb.length; i++){
			
			ckb[i].checked = checked1;
			
			if(i == ckb.length-1){
				temp = checked1;
				checked1 = checked2;
				checked2 = temp;
			}
			console.log(checked1);
		}	
		
	}
	
</script>
