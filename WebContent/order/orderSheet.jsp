<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action = "${cp }/order/payment.do" method = "post" onsubmit="return submitClick()"> 
<h1>상품정보</h1>

<c:forEach var = "paynum" items = "${paynumList }">
	<input type = "hidden" name = "paynumList" value = "${paynum }">
</c:forEach>

<table border = 1>
	<tr>
		<th>삼풍명</th>
		<th>가격</th>
		<th>배송구분</th>
	</tr>

	<c:forEach var="anum" items="${orderanumList }">
		<input type = "hidden" id = "name" value = "${fn:length(orderanumList) }개의 상품 주문">
		<tr>
			<!-- 물품명 -->
			<c:forEach var="titleList" items="${titleList}">
				<c:if test="${titleList.key == anum}">
					<td>${titleList.value }</td>
				</c:if>
			</c:forEach>

			<!-- 가격 -->
			<c:forEach var="priceList" items="${priceList}">
				<c:if test="${priceList.key == anum}">
					<td>${priceList.value}</td>
				</c:if>
			</c:forEach>
			<!-- 배송 -->
			<c:forEach var="shipinfoList" items="${shipinfoList}">
				<c:if test="${shipinfoList.key == anum}">
					<td>${shipinfoList.value.s_way}</td>
				</c:if>
			</c:forEach>
		</tr>
	</c:forEach>

</table>
<h1>배송 정보</h1>
<table border = 1>
	<tr>
		<th>수령인 </th>	
		<td><input type = "text" id = "buyerName" name ="buyerName" value = "${memberInfo.m_name }"></td>			
	</tr>
	<tr>
		<th>주소</th>
		<td>
		<input type = "text" id = "addr1" name = "zip" placeholder="우편번호" readonly="readonly">
		<input type = "button" onclick ="openZipSearch()" value = "주소찾기">
		<br>
		<input type = "text" name = "addr1" readonly="readonly" id = "addr2"><br>
		<input type = "text" name = "addr2" placeholder="상세주소" id = "addr3"><br>		
		</td>	
	</tr>
	<tr>
		<th>휴대전화</th>	
		<td><input type = "text" name = "phone" id = "phone" value = ${memberInfo.m_phone }></td>			
	</tr>
</table>
<h1>총 결제 금액</h1>
<table border="1">
	<tr>
		<th>상품금액</th>
		<th>배송금액</th>
		<th>총결제 금액</th>
	</tr>
	<tr>
		<!-- 상품금액 -->
		<c:forEach var="priceList" items="${priceList}">
			<c:set var = "productPrice" value="${productPrice+priceList.value }"/>
		</c:forEach>
		<td>${productPrice}</td>
		<!-- 배송 금액 -->
		<c:forEach var="shipinfoList" items="${shipinfoList}">
				<c:set var = "shipPrice" value="${shipPrice+shipinfoList.value.s_price }"/>
		</c:forEach>	
		<td>${shipPrice}</td>

		<!-- 총 금액 -->
		<!-- 
		<td id="amount">${productPrice+shipPrice}</td>				
		 -->
		<td id="amount">${productPrice+shipPrice}</td>		
	</tr>
</table>
<h1>결제 수단 선택</h1>
<div style="border: 1px solid black">
	<input type = "radio" name = "paytype" value = "card" id = "r1" onchange = "bankbookClick(event)">
	<label for="r1">카드</label>
	<input type = "radio" name = "paytype" value = "bankbook" id ="r2" onchange = "bankbookClick(event)">
	<label for="r2">무통장입금</label>
</div>

<div id = "bankbookInfo" style="display:none">
	<table>
		<tr>
			<th>입금하실 은행</th>
			<td>
				<select>
					<option value = "1" selected>국민은행</option>		
					<option value = "2">신한은행</option>		
					<option value = "3">우리은행</option>		
				</select>
			</td>
		</tr>
		<tr>
			<th>예금자 성명</th>
			<td><input type = "text" id = "accountHolder"></td>
		</tr>
	</table>
</div>
<input type = "submit" value = "결제하기">
</form>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">

	function bankbookClick(e){
		var bankbookInfo = document.getElementById("bankbookInfo")
		console.log("t"+e.target.value);
		if(e.target.value == 'bankbook'){
			bankbookInfo.style.display = "block";			
		}else{
			bankbookInfo.style.display = "none";						
		}
	}
	
	function submitClick(){
		var ra = document.getElementsByName("paytype");
		var raval;
		
		for(var i =0; i<ra.length ; i++){
			if(ra[i].checked){
				raval = ra[i].value;
			}
		}
		
		var amount = document.getElementById("amount").innerText;
		var buyerName = document.getElementById("buyerName").value;
		var phone = document.getElementById("phone").value;
		var buyername = document.getElementById("buyerName").value;
		var addr1 = document.getElementById("addr1").value;
		var addr2 = document.getElementById("addr2").value;
		var addr3 = document.getElementById("addr3").value;
		
		if(buyername == "" || addr1 == "" || addr2 == "" || addr3 == "" 
				|| ra.length == 0 || buyerName == "" || phone == "" ){
			alert("정보를 입력해주세요");
			return false;
		}
		
		if(raval == "bankbook"){
			var accountHolder = document.getElementById("accountHolder").value;
			if(accountHolder==""){
				alert("정보를 입력해주세요");
				return false;
			}
		}
		
		if(raval == "card"){
			window.open('${cp}/order/paymentPopup.jsp','결제창','width=800, height=800, resizable = no');
			return false;
		}
		
		return true;	
	}
	
	function openZipSearch() {
		new daum.Postcode({
			oncomplete: function(data) {
				$('[name=zip]').val(data.zonecode); // 우편번호 (5자리)
				$('[name=addr1]').val(data.address);
				$('[name=addr2]').val(data.buildingName);
			}
		}).open();
	}

</script>