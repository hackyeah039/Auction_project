<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="card" style="width: 50rem; margin : auto; margin-top : 50px">
	<div class = "card-body">
		<h3 class = "card-title" style="text-align: center">결제하기</h3>

<form action = "${cp }/order/payment.do" method = "post" onsubmit="return submitClick()" id = "frm">  
<h5>상품정보</h5>

<c:forEach var = "paynum" items = "${paynumList }">
	<input type = "hidden" name = "paynumList" value = "${paynum }">
</c:forEach>

	<table  class="table table-bordered" border=1 style="text-align: center; 
		margin-top: 40px"> 
		<thead class = "thead">
			<tr>
				<th scope="col">삼풍명</th>
				<th scope="col">가격</th>
				<th scope="col">배송구분</th>
			</tr>
		</thead>

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
<h5>배송 정보</h5>

		<div class="form-group">
	    	<label for="buyerName">수령인</label>
	   	 	<input type="text" class="form-control" id="buyerName"  name = "buyerName" value = "${memberInfo.m_name }" >
	  	</div>	
		<div class="form-group">
			<div class = "form-row">
				<div class = "form-group col-md-3">
	    			<label>주소</label>
	   	 			<input type="text" class="form-control" id="addr1"  name = "zip" placeholder="우편번호" readonly="readonly">
	   	 		</div>
	   	 		<div style="margin-top: 32px">
	   	 			<input type = "button" onclick ="openZipSearch()"  class="btn btn-primary" value = "주소찾기">
	   	 		</div>
	   	 	</div>
	   	 	<input type = "text" class="form-control" name = "addr1" readonly="readonly" placeholder="주소" id = "addr2"><br>
			<input type = "text" class="form-control" name = "addr2" placeholder="상세주소" id = "addr3"><br>		
	  	</div>	

		<div class="form-group">
	    	<label for="phone">휴대전화</label>
	   	 	<input type = "text" class="form-control"  name = "phone" id = "phone" value = ${memberInfo.m_phone }>
	  	</div>	

<h5>총 결제 금액</h5>
	<table  class="table table-bordered" border=1 style="text-align: center; 
		margin-top: 40px"> 
		<thead class = "thead">
	
			<tr>
				<th scope="col">상품금액</th>
				<th scope="col">배송금액</th>
				<th scope="col">총결제 금액</th>
			</tr>
		</thead>
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

<h5>결제 수단 선택</h5>
<div>
	<input type = "radio" name = "paytype" value = "card" id = "r1" onchange = "bankbookClick(event)">
	<label for="r1">카드</label>
	<input type = "radio" name = "paytype" value = "bankbook" id ="r2" onchange = "bankbookClick(event)">
	<label for="r2">무통장입금</label>
</div>
<div class = "card" id = "bankbookInfo" style="display:none">		
 	<div class="col-auto my-2">
      <label class="mr-sm-2" for="bank">입금하실 은행</label>
      <select class="custom-select mr-sm-2" id="bank" name = "bank">
        <option value="0">선택</option>
        <option value="1">국민은행</option>
        <option value="2">신한은행</option>
        <option value="3">우리은행</option>
      </select>
    </div>
    <div class="form-group">
	    	<label for="title">예금자 성명</label>
	   	 	<input type="text" class="form-control" id="accountHolder">
	</div>			
</div>
<input type = "hidden" value = "${productPrice+shipPrice}" name = "price">
<input type = "submit" class="btn btn-primary" value = "결제하기">
</form>
</div>
</div>


<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>

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
			var bank = document.getElementById("bank").value;
			if(accountHolder=="" || bank == 0 ){
				alert("정보를 입력해주세요");
				return false;
			}
		}
		
		if(raval == "card"){
			clickCard();
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
	<!--

	//-->
	
	
		var name = document.getElementById("name").value;
		var amount =document.getElementById("amount").innerText;
		var buyer_name = document.getElementById("buyerName").value;
		var buyer_tel = document.getElementById("phone").value;
		var buyer_postcode = document.getElementById("addr1").value;
		var buyer_addr = document.getElementById("addr2").value;
		var buyer_addr2 = document.getElementById("addr3").value;
		var paynumlist = document.getElementsByName("paynumList");
	
		var msg = "buyerName="+buyer_name+"&price="+amount+"&zip="+buyer_postcode+"&addr1="+buyer_addr+"&addr2="+buyer_addr2+"&phone="+buyer_tel
		for (var i = 0; i < paynumlist.length; i++) {
			msg += "&paynumList="+paynumlist[i].value;
		}
		
		msg += "&card=1";
		
		console.log(msg);
		
		function clickCard() {
			var IMP = window.IMP;
			IMP.init('imp47317782');
			IMP.request_pay({
			    pg : 'html5_inicis',
			    pay_method : 'card',
			    merchant_uid : 'merchant_' + new Date().getTime(),
			    name : name,
			    amount : amount ,
			    buyer_name : buyer_name,
			    buyer_tel : buyer_tel,
			    buyer_addr : buyer_addr,
			    buyer_postcode : buyer_postcode 
			}, function(rsp){
			    if ( rsp.success ) {
			        var msg2 = '결제가 완료되었습니다.';
			        msg2 += '고유ID : ' + rsp.imp_uid;
			        msg2 += '상점 거래ID : ' + rsp.merchant_uid;
			        msg2 += '결제 금액 : ' + rsp.paid_amount;
			        msg2 += '카드 승인번호 : ' + rsp.apply_num;
			        document.getElementById("frm").submit();
			    } else {
			        var msg2 = '결제에 실패하였습니다.';
			        msg += '에러내용 : ' + rsp.error_msg;
			        alert(msg2);
			    }
	
			});
		
		}
			
</script>