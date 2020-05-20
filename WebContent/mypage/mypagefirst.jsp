<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id = "myTrust">
	<h1>
	당신의 신용도는 ${requestScope.trust }입니다.	
	</h1>
	<ul>
		<li><a href="${cp }/interest/interest.do">관심 상품 리스트</a></li>
		<li><a href="#">최근 상품 리스트</a></li>
		<li><a href="#">나의 정보 수정하기</a></li>
		<li><a href="#">문의 하기</a></li>
		<li><a href="#">탈퇴 하기</a></li>
	</ul>
</div>

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
		<td>${bidCount }</td>
		<td>${reqPayCount }</td>
		<td><a href="#">배송조회</a></td>
		<td>${saleCount }</td>
		<td>${shipReqCount }</td>
		<td>0</td>
	</tr>
</table>

<a href = "${cp }/mypage/buyerBidding.do"><input type="button" id = "btn1" value = "구매관리 바로가기" ></a>
<a href = "${cp }/mypage/sellerBidding.do"><input type="button" id = "btn2" value = "판매관리 바로가기" ></a>


