<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header.jsp 테스트용 -->
<style>
	li { display: inline-block; font-size: 30px;}
</style>
<script type="text/javascript">
	//입찰하기 팝업 띄우기 
	function showBidding() {
		//?a_num=${a_num} 추가 세션에서 값을 받아와야함. 
		window.open("${cp}/Bidding.do", "_blank", "top=200,left=500,height = 150, width = 500");
	}
</script>
<div>
	<ul>
		<li><a href="${cp }/home.do">홈</a></li>
		<li><a href="${cp }/InsertAuction.do">글작성</a></li>
		<li><a href="" onclick="showBidding()">입찰하기</a></li>
		<li>
		<form method="post" action="${cp }/list">
			<select name="field">
				<option value="content" <c:if test="${field=='content'}"> selected </c:if>> 내용</option>
				<option value="title" <c:if test="${field=='title'}"> selected </c:if>> 제목</option>
				<option value="writer" <c:if test="${field=='writer'}"> selected </c:if>> 판매자 </option>
			</select>
			<input type="text" name="keyword" value="${keyword }">
			<input type="submit" value="검색">
		</form>
		</li>
	</ul>
</div>