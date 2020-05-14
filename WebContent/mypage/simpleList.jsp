<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<script>
	function td_copy(val){
		
		table1 = document.getElementById("table");
	
	    table1.rows[1].cells[0].innerHTML = "hello";
	
	}
	
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
		<td>0</td>
		<td>0</td>
		<td>0</td>
		<td>0</td>
	</tr>
</table>

<input type = "button" onclick = "td_copy(0)">