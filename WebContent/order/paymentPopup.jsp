<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
<!--

//-->

		var name = opener.document.getElementById("name").value;
		var amount = opener.document.getElementById("amount").innerText;
		var buyer_name = opener.document.getElementById("buyerName").value;
		var buyer_tel = opener.document.getElementById("phone").value;
		var buyer_postcode = opener.document.getElementById("addr1").value;
		var buyer_addr = opener.document.getElementById("addr2").value;
		var buyer_addr2 = opener.document.getElementById("addr3").value;
	
		
		
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
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		    	
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		        alert(msg);
		    }

		});
		
</script>
<form action = "${cp }/order/payment.do" method = "post"">
	<input type = "hidden" name = "buyername">
	<input type = "hidden" name = "buyertel">
	<input type = "hidden" name = "buyer">
	<input type = "hidden" name = "addr">
</form>