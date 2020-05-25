<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>문의글 리스트</h3>
<br><br>
<table border="1" class="table table-hover">
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
<div id="paging">
	<nav aria-label="Page navigation example">
	<ul class="pagination">
	<c:choose>
		<c:when test="${startPage>3}">
			<li class="page-item">
			<a href="${cp}/board/qnadoing.jh?pageNum=${startPage-3}&field=${field}
			&keyword=${keyword}&type=${type}" class="page-link" aria-label="Previous">
			<span aria-hidden="true">&laquo;</span>
       		<span class="sr-only">Previous</span></a>
       		</li>
		</c:when>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<li class="page-item">
				<a href="${cp}/board/qnadoing.jh?pageNum=${i}&field=${field}
				&keyword=${keyword}&type=${type}" class="page-link">
				${i}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item">
				<a href="${cp}/board/qnadoing.jh?pageNum=${i}&field=${field}
				&keyword=${keyword}&type=${type}" class="page-link">
				${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 다음버튼 -->
		<c:choose>
		<c:when test="${endPage<pageCount }">
			<li class="page-item">
			<a href="${cp}/board/qnadoing.jh?pageNum=${endPage+1}&field=${field}
			&keyword=${keyword}&type=${type}" class="page-link" aria-label="Next">
			<span aria-hidden="true">&raquo;</span>
        	<span class="sr-only">Next</span></a></li>
		</c:when>
	</c:choose>
	</ul>
	</nav>
</div>
<br>
<!-- 검색기능 -->
<div>
	<form method="post" action="${cp }/board/qnadoing.jh?type=${type}">
		<select name="field" class="custom-select">
			<option value="id" <c:if test="${field=='id' }">selected</c:if>>작성자</option>
			<option value="b_title" <c:if test="${field=='b_title' }">selected</c:if>>글제목</option>
			<option value="b_content" <c:if test="${field=='b_contnent' }">selected</c:if>>내용</option>
		</select>
		<input type="text" name="keyword" value="${keyword }">
		<input type="submit" value="검색" class="btn btn-outline-success my-2 my-sm-0">
	</form>
</div>