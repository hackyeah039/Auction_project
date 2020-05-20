<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
/*
	window.onload = function(){
		
		
		var xhr = null;
		
		xhr = new XMLHttpRequest;
		xhr.open('get','${cp}/mypage/simplelist.do',true)
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				var data = xhr.responseText;
 				var json = JSON.parse(data);
 				table1 = document.getElementById("table");		
 				table1.rows[1].cells[0].innerHTML = json.bidCount;
 				table1.rows[1].cells[1].innerHTML = json.reqPayCount;
 				table1.rows[1].cells[3].innerHTML = json.saleCount;
 				table1.rows[1].cells[4].innerHTML = json.shipReqCount;
			}
		}
		xhr.send();
		
	}*/
	
</script>

<table border=1 id = "table">
	<tr>
		<th rowspan="2">MY</th>
		<th>입찰중</th>
		<th>입금요청</th>
		<th>배송</th>
		<th>판매중</th>
		<th>배송요청</th>
		<th>구매자 문의</th>
	</tr>
	<tr>
		<td>0</td>
		<td>0</td>
		<td><a href="#">배송조회</a></td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
	</tr>
</table>