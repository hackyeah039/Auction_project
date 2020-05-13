<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var date1 = "202002070300";
	var date2 = "202002070320";
	
	console.log(date1 < date2);
	
</script>
<table border = "1">
	<tr>
		<th>구매관리</th>
	</tr>
	<tr>
		<td><a href = "${cp }/mypage/buyerBidding.do">입찰중</a></td>
	</tr>
	<tr>
		<td><a href = "#">구매종료</a></td>
	</tr>
	<tr>
		<td><a href = "#">거래진행중</a></td>
	</tr>
	<tr>
		<th> 판매관리</th>
	</tr>
	<tr>
		<td ><a href = "${cp }/mypage/sellerBidding.do">입찰중</a></td>
	</tr>
	<tr>
		<td ><a href = "#">구매종료</a></td>
	</tr>
	<tr>
		<td><a href = "#">거래진행중</a></td>
	</tr>
</table>