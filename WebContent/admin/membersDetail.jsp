<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

<h3>회원 상세페이지</h3>
<br>
<c:forEach var="vo" items="${list }">
<table border="1" class="table table-hover">
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
		<th>신뢰도</th>
		<td>${vo.trust }</td>
	</tr>
	<tr>
		<th>가입일</th>
		<td>${vo.m_regdate }</td>
	</tr>
</table>
<br>
<c:choose>
	<c:when test="${vo.m_type==1 }">
		<form method="post" action="${cp }/members/out.jh">
		<input type="hidden" name="m_num" value="${vo.m_num }">
		<input type="submit" value="탈퇴승인" class="btn btn-primary btn-lg">
		</form>
	</c:when>
</c:choose>
</c:forEach>