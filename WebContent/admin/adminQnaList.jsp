<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>관리자 문의사항 전체 리스트</h1>
<table border="1">
	<tr>
		<th>NO</th>
		<th>문의글 제목</th>
		<th>작성자</th>
		<th>답변상태</th>
		<th>등록일</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.b_num }</td>		
			<td><a href="${cp }/board/detail.jh?b_num=${vo.b_num}&m_id=${vo.m_id}">
			${vo.b_title}</a></td>		
			<td>${vo.m_id}</td>		
			<td>${vo.boardName }</td>
			<td>${vo.b_regdate }</td>
		</tr>
	</c:forEach>
</table>
<br>
<div>
	<c:choose>
		<c:when test="${startPage>3}">
				<a href="${cp}/board/qnalist.jh?pageNum=${startPage-3}&field=${field}&keyword=${keyword}">[이전]</a>
		</c:when>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<span><a href="${cp}/board/qnalist.jh?pageNum=${i}&field=${field}&keyword=${keyword}">
				[${i}]</a></span>
			</c:when>
			<c:otherwise>
				<span><a href="${cp}/board/qnalist.jh?pageNum=${i}&field=${field}&keyword=${keyword}">
				[${i}]</a></span>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 다음버튼 -->
		<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="${cp}/board/qnalist.jh?pageNum=${endPage+1}&field=${field}&keyword=${keyword}">[다음]</a>
		</c:when>
	</c:choose>
</div>
<br>
<!-- 검색기능 -->
<div>
	<form method="post" action="${cp }/board/qnalist.jh">
		<select name="field">
			<option value="id" <c:if test="${field=='id' }">selected</c:if>>작성자</option>
			<option value="b_title" <c:if test="${field=='b_title' }">selected</c:if>>글제목</option>
			<option value="b_content" <c:if test="${field=='b_contnent' }">selected</c:if>>내용</option>
		</select>
		<input type="text" name="keyword" value="${keyword }">
		<input type="submit" value="검색">
	</form>
</div>