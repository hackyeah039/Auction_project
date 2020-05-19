<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>회원리스트</h1>
<table border="1">
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
					<td><a href="${cp }/members.detail.jh?m_num=${vo.m_num}">${vo.m_id }</a></td>
				</c:otherwise>
			</c:choose>		
			<td>${vo.typeName }</td>		
			<td>${vo.m_regdate }</td>
		</tr>
	</c:forEach>
</table>
<br>
<div>
	<c:choose>
		<c:when test="${startPage>3}">
				<a href="${cp}/members.doing.jh?pageNum=${startPage-3}&field=${field}
				&keyword=${keyword}&type=${type}">[이전]</a>
		</c:when>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<span><a href="${cp}/members.doing.jh?pageNum=${i}&field=${field}
				&keyword=${keyword}&type=${type}">
				[${i}]</a></span>
			</c:when>
			<c:otherwise>
				<span><a href="${cp}/members.doing.jh?pageNum=${i}&field=${field}
				&keyword=${keyword}&type=${type}">
				[${i}]</a></span>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 다음버튼 -->
		<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="${cp}/members.doing.jh?pageNum=${endPage+1}&field=${field}
			&keyword=${keyword}&type=${type}">[다음]</a>
		</c:when>
	</c:choose>
</div>
<br>
<!-- 검색기능 -->
<div>
	<c:choose>
	<c:when test="${type==2 }">
		<form method="post" action="${cp }/members.doing.jh?type=${type}">
			<select name="field">
				<option value="m_id" <c:if test="${field=='m_id'}">selected</c:if>>회원 아이디</option>	
			</select>
			<input type="text" name="keyword" value=${keyword }>
			<input type="submit" value="검색">	
		</form>
	</c:when>
	<c:otherwise>
		<form method="post" action="${cp }/members.doing.jh?type=${type}">
			<select name="field">
				<option value="m_id" <c:if test="${field=='m_id'}">selected</c:if>>회원 아이디</option>	
				<option value="m_name" <c:if test="${field=='m_name'}">selected</c:if>>회원 이름</option>	
				<option value="m_email" <c:if test="${field=='m_email'}">selected</c:if>>회원 이메일</option>	
				<option value="m_phone" <c:if test="${field=='m_phone'}">selected</c:if>>전화번호</option>	
				<option value="m_addr" <c:if test="${field=='m_addr'}">selected</c:if>>주소</option>	
			</select>
			<input type="text" name="keyword" value=${keyword }>
			<input type="submit" value="검색">	
		</form>
	</c:otherwise>
	</c:choose>
</div>