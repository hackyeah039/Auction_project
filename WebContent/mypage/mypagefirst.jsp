<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/MyPageCss.css">
<title>index.jsp</title>
</head>
<body>
<div id = "myTrust">
	<h1>
	당신의 신용도는 ${requestScope.trust }입니다.	
	</h1>
	<ul>
		<li><a href="#">관심 상품 리스트</a></li>
		<li><a href="#">최근 상품 리스트</a></li>
		<li><a href="#">나의 정보 수정하기</a></li>
		<li><a href="#">문의 하기</a></li>
	</ul>
</div>

<div id = "buyDiv">
	<h1>구매현황</h1>
	<a href = "#">구매관리 바로가기</a>
	<table border = "1" >
		<tr>
			<th>입금 요청</th>
			<th>입금 확인 중</th>
			<th>결제 완료/배송준비 중</th>
			<th>배송중</th>
			<th>입찰중</th>
			<th>미입금</th>
		</tr>
		
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>		
	</table>
</div>



<div id = "sellDiv">
	<h1>판매현황</h1>
	<a href="#">판매관리 바로가기</a>
	<table border = "1">
		<tr>
			<th>입금 요청</th>
			<th>입금 확인 중</th>
			<th>배송 요청</th>
			<th>배송 중</th>
			<th>구매 거부</th>
		</tr>
		
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>		
	</table>
</div>
</body>    
</html>
