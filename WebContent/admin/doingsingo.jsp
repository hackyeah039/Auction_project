<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

<h3>신고 리스트</h3>
<br><br>
<table border="1" class="table table-hover">
	<tr>
		<th>NO</th>
		<th>신고자 아이디</th>
		<th>처리상태</th>
		<th>신고일자</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.singo_num }</td>
			<td><a href="${cp}/singo/detail.jh?singo_num=${vo.singo_num}
				&id=${vo.m_id}">
			${vo.m_id}</a></td>
			<td>${vo.singoProcess }</td>
			<td>${vo.singo_date }</td>
		</tr>
	</c:forEach>
</table>
<br>
<div>
<c:choose>
	<c:when test="${startPage>3 }">
		<a href="${cp}/singo/doing.jh?pageNum=${startPage-3}&type=${type}&field=${field}&keyword=${keyword}">[이전]</a>
	</c:when>
</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="${cp}/singo/doing.jh?pageNum=${i}&type=${type}&field=${field}&keyword=${keyword}">
				<span style='color:blue;'>[${i}]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp}/singo/doing.jh?pageNum=${i}&type=${type}&field=${field}&keyword=${keyword}">
				<span>[${i}]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<!-- 다음/끝.. -->
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="${cp}/singo/doing.jh?pageNum=${endPage+1}&type=${type}&field=${field}&keyword=${keyword}">[다음]</a>
	</c:when>
</c:choose>
</div>
<br>
<div>
	<form method="post" action="${cp }/singo/doing.jh?&type=${type}">
		<select name="field">
			<option value="singojaId" <c:if test="${field=='singojaId'}">selected</c:if>>신고자 아이디</option>	
			<option value="singo_content" <c:if test="${field=='singo_content'}">selected</c:if>>신고내용</option>	
			<option value="sinid" <c:if test="${field=='sinid'}">selected</c:if>>대상자 아이디</option>	
		</select>
		<input type="text" name="keyword" value=${keyword }>
		<input type="submit" value="검색">	
	</form>
</div>

