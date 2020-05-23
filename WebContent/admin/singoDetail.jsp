<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>신고세부</h1>
<c:forEach var="vo" items="${list }">
<table border="1">
	<tr>
		<th>신고자: </th>
		<td>${id }</td>
	</tr>
	<tr>
		<th>신고일자: </th>
		<td>${vo.singo_date }</td>
	</tr>
	<tr>
		<th>신고내용: </th>
		<td>${vo.singo_content }</td>
	</tr>
	<tr>
		<th>신고대상자: </th>
		<td>${vo.singo_id }</td>
	</tr>
</table>
<br>
<form method="post">
	<input type="hidden" name="id" value="${vo.singo_id}"><!-- 신고대상자 아이디 -->
	<input type="hidden" name="singo_num" value="${vo.singo_num}">
	<input type="hidden" name="test" value="test">
	
	<c:choose>
		<c:when test="${vo.singo_status>=1}">
			<input type="button" value="처리반려" disabled="disabled">
			<input type="button" value="처리승인" disabled="disabled">
		</c:when>
		<c:otherwise>
			<input type="button" value="처리반려" onclick="goSubmit1(this.form)" id="btn1">
			<input type="button" value="처리승인" onclick="goSubmit2(this.form)" id="btn2">
		</c:otherwise>
	</c:choose>
</form>
</c:forEach>
<script>
	
	function goSubmit1(form) { /*처리 반려 서블릿으로 이동*/
		form.action="${cp}/singo/return.jh";
		form.submit();
		return true;
	}
	function goSubmit2(form) { /*처리 승인 서블릿으로 이동*/
		form.action="${cp}/singo/approval.jh";
		form.submit();
		return true;
	}
</script>