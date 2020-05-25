<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<h3>전체 회원리스트</h3>
<br><br>
<table border="1" class="table table-hover">
	<tr>
		<th>NO.회원번호</th>
		<th>회원아이디</th>
		<th>회원상태</th>
		<th>가입일</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.m_num }</td>
			<c:choose>
				<c:when test="${vo.m_type==2 }">
					<td>${vo.m_id }</td>
				</c:when>
				<c:otherwise>		
					<td><a href="${cp }/members/detail.jh?m_num=${vo.m_num}">${vo.m_id }</a></td>
				</c:otherwise>
			</c:choose>	
			<td>${vo.typeName }</td>		
			<td>${vo.m_regdate }</td>		
		</tr>
	</c:forEach>
</table>
<br>
<div id="paging">
	<nav aria-label="Page navigation example">
	<ul class="pagination">
	<c:choose>
		<c:when test="${startPage>3 }">
			<li class="page-item">
			<a href="${cp}/members/list.jh?pageNum=${startPage-3}&field=${field}
			&keyword=${keyword}" class="page-link" aria-label="Previous">
			<span aria-hidden="true">&laquo;</span>
       		<span class="sr-only">Previous</span></a>
			</li>
		</c:when>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<li class="page-item">
				<a href="${cp}/members/list.jh?pageNum=${i}&field=${field}&keyword=${keyword}" class="page-link">
				${i}</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item">
				<a href="${cp}/members/list.jh?pageNum=${i}&field=${field}&keyword=${keyword}" class="page-link">
				${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<li class="page-item">
			<a href="${cp}/members/list.jh?pageNum=${endPage+1}&field=${field}
			&keyword=${keyword}" class="page-link" aria-label="Next">
			<span aria-hidden="true">&raquo;</span>
        	<span class="sr-only">Next</span></a>
			</li>
		</c:when>
	</c:choose>
	</ul>
	</nav>
</div>
<br>
<div>
	<form method="post" action="${cp }/members/list.jh?">
		<select name="field" class="custom-select">
			<option value="m_id" <c:if test="${field=='m_id'}">selected</c:if>>회원 아이디</option>	
			<option value="m_name" <c:if test="${field=='m_name'}">selected</c:if>>회원 이름</option>	
			<option value="m_email" <c:if test="${field=='m_email'}">selected</c:if>>회원 이메일</option>	
			<option value="m_phone" <c:if test="${field=='m_phone'}">selected</c:if>>전화번호</option>	
			<option value="m_addr" <c:if test="${field=='m_addr'}">selected</c:if>>주소</option>	
		</select>
		<input type="text" name="keyword" value=${keyword }>
		<input type="submit" value="검색" class="btn btn-outline-success my-2 my-sm-0">	
	</form>
</div>
