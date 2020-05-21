<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송요청</title>
</head>
<body>
	<h1>구매자 정보</h1>
	<table id = "buyerInfo" border = "1">
		<tr>
			<th>이름</th>
			<th>전화번호</th>
			<th>배송지</th>			
		</tr>
		
		<tr>
			
		</tr>
	</table>
	
	<form action = "${cp }/popup/reqShipPopup.do" method="post" >
	<h1>운송장 번호 정보</h1>
	<table>
		<tr>
			<th>택배사</th>
			<td><input type = "text" name = "courier" id = "courier"></td>
		</tr>
		<tr>
			<th>송장번호</th>
			<td><input type = "text" name = "invoicenum" id = "invoicenum"></td>
		</tr>
	</table>
	<input type = "hidden" value = "${anum }" name = "anum">	
	<input type = "button" value = "등록하기" onclick = "clicksub()">
	</form>
</body>
<script type="text/javascript">

	var xhr;

	function clicksub (){
		var courier = document.getElementById("courier").value;
		var invoicenum = document.getElementById("invoicenum").value;
		
		xhr = new XMLHttpRequest;
		xhr.open('post','${cp}/popup/reqShipPopup.do',true)
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				var data = xhr.responseText;
 				var json = JSON.parse(data);
				
			    if(json.message == "success"){
			    	alert("등록하였습니다.");
					window.opener.location.reload();    //부모창 reload
					window.close();    //현재 팝업창 Close		    	
			    }else{
			    	alert("등록에 실패하였습니다. 다시입력해주세요");
			    }	
			}
		}
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send("anum=${anum}&courier="+courier+"&invoicenum="+invoicenum);
	}

</script>
</html>