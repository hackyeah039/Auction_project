<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border = "1">
	<tr>
		<th>구매관리</th>
	</tr>
	<tr>
		<td><a href = "${cp }/mypage/buyerBidding.do">입찰중</a></td>
	</tr>
	<tr>
		<td><a href = "${cp }/mypage/buyerTranCompleted.do">구매종료</a></td>
	</tr>
	<tr>
		<td><a href = "${cp }/mypage/buyerTransact.do">거래진행중</a></td>
	</tr>
	<tr>
		<th> 판매관리</th>
	</tr>
	<tr>
		<td ><a href = "${cp }/mypage/sellerBidding.do">입찰중</a></td>
	</tr>
	<tr>
		<td ><a href = "${cp }/mypage/sellerTranCompleted.do">구매종료</a></td>
	</tr>
	<tr>
		<td><a href = "${cp }/mypage/sellerTransact.do">거래진행중</a></td>
	</tr>
</table>