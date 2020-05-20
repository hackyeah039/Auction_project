<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>전체신고리스트</h1>
<table border="1">
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
				&id=${vo.m_id}">${vo.m_id}</a></td>
			<td>${vo.singoProcess }</td>
			<td>${vo.singo_date }</td>
		</tr>
	</c:forEach>
</table>
<br>
<div>
<c:choose>
	<c:when test="${startPage>3 }">
		<a href="${cp}/singo/list.jh?pageNum=${startPage-3}&field=${field}&keyword=${keyword}">[이전]</a>
	</c:when>
</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="${cp}/singo/list.jh?pageNum=${i}&field=${field}&keyword=${keyword}">
				<span style='color:blue;'>[${i}]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp}/singo/list.jh?pageNum=${i}&field=${field}&keyword=${keyword}">
				<span>[${i}]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<!-- 다음/끝.. -->
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="${cp}/singo/list.jh?pageNum=${endPage+1}&field=${field}&keyword=${keyword}">[다음]</a>
	</c:when>
</c:choose>
</div>
<br>
<div>
	<form method="post" action="${cp }/singo/list.jh?">
		<select name="field">
			<option value="singojaId" <c:if test="${field=='singojaId'}">selected</c:if>>신고자 아이디</option>	
			<option value="singo_content" <c:if test="${field=='singo_content'}">selected</c:if>>신고내용</option>	
			<option value="sinid" <c:if test="${field=='sinid'}">selected</c:if>>대상자 아이디</option>	
		</select>
		<input type="text" name="keyword" value=${keyword }>
		<input type="submit" value="검색">	
	</form>
</div>