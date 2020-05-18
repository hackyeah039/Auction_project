<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form>
<h1>상품정보</h1>
<table border = 1>
	<tr>
		<th>삼풍명</th>
		<th>가격</th>
		<th>배송구분</th>
	</tr>

	<c:forEach var="anum" items="${orderanumList }">
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
		<td><input type = "text"></td>			
	</tr>
	<tr>
		<th>주소</th>
		<td>
		<input type = "text" name = "zipcode">
		<input type = "button" value = "주소찾기">
		<br>
		<input type = "text" name = "roadname"><br>
		<input type = "text" name = "detailaddr"><br>		
		</td>	
	</tr>
	<tr>
		<th>휴대전화</th>	
		<td><input type = "text" id = "phone"></td>			
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
		<td>${productPrice+shipPrice}</td>		
	</tr>
</table>
<h1>결제 수단 선택</h1>
<div style="border: 1px solid black">
	<input type = "radio" name = "paytype" value = "card" id = "r1">
	<label for="r1">카드</label>
	<input type = "radio" name = "paytype" value = "bankbook" id ="r2" onclick = " bankbookClick()">
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

<input type = "button" value = "test" onclick = "submitClick()">
<input type = "submit" value = "결제하기">
</form>

<script type="text/javascript">
	
	function bankbookClick(){
		var bankbookInfo = document.getElementById("bankbookInfo");
		bankbookInfo.style.display = "block";
	}
	
	function submitClick(){
		var ra = document.getElementsByName("paytype");
		var raval;
		for(var i =0; i<ra.length ; i++){
			if(ra[i].checked){
				raval = ra[i].value;
			}
		}
		
		console.log("test")
		if(raval == "bankbook"){
			var accountHolder = document.getElementById("accountHolder").value;
			if(accountHolder==""){
				console.log("입력하시오..")
			}
		}
	}
</script>