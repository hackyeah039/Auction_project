<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>회원 상세페이지</h1>

<c:forEach var="vo" items="${list }">
<table border="1">
	<tr>
		<th>회원번호</th>
		<td>${vo.m_num }</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td>${vo.m_id }</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${vo.m_name }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${vo.m_email }</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${vo.m_phone }</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${vo.m_addr }</td>
	</tr>
	<tr>
		<th>가입일</th>
		<td>${vo.m_regdate }</td>
	</tr>
</table>
<br>
<c:choose>
	<c:when test="${vo.m_type==1 }">
		<form method="post" action="${cp }/members.out.jh">
		<input type="hidden" name="m_num" value="${vo.m_num }">
		<input type="submit" value="탈퇴승인">
		</form>
	</c:when>
</c:choose>
</c:forEach>