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
		var paynumlist = opener.document.getElementsByName("paynumList");
	
		var msg = "buyerName="+buyer_name+"&price="+amount+"&zip="+buyer_postcode+"&addr1="+buyer_addr+"&addr2="+buyer_addr2+"&phone="+buyer_tel
		for (var i = 0; i < paynumlist.length; i++) {
			msg += "&paynumList="+paynumlist[i].value;
		}
		
		msg += "&card=1";
		
		console.log(msg);
		
		
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
				ajfun();
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		        alert(msg);
		    }

		});
		
		var xhr2 = "";
		
		function ajfun (){
			xhr2 = new XMLHttpRequest;
			xhr2.open('post','${cp}/order/payment.do',true)
			xhr2.onreadystatechange = ajfuncall;
			xhr2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr2.send(msg);
		}

		function ajfuncall(){
			if(xhr2.readyState == 4 && xhr2.status == 200){
				var data = xhr2.responseText;
				var json = JSON.parse(data);
				
			    if(json.result == "success"){
					alert("등록에 성공하였습니다.");
					history.go(-1);
			    }else{
			    	alert("등록에 실패하였습니다. 다시입력해주세요");
			    }	
			}
		}
		
</script>