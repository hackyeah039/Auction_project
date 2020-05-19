<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header.jsp 테스트용 -->
<style>
	li { display: inline-block; font-size: 30px;}
</style>
<script type="text/javascript">
	//입찰하기 팝업 띄우기 
	function showBidding() {
		//?a_num=${a_num} 추가 
		window.open("${cp}/Bidding.do", "_blank", "top=200,left=500,height = 150, width = 280");
	}
</script>
<div>
	<ul>
		<li><a href="${cp }/home.do">홈</a></li>
		<li><a href="${cp }/InsertAuction.do">글작성</a></li>
		<li><a href="" onclick="showBidding()">입찰하기</a></li>
	</ul>
</div>