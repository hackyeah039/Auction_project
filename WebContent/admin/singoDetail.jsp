<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

<h3>신고 내용</h3>
<br><br>

<c:forEach var="vo" items="${list }">
<table border="1" class="table table-striped" >
	<tr>
		<th>신고자 </th>
		<td>${id }</td>
	</tr>
	<tr>
		<th>신고일자 </th>
		<td>${vo.singo_date }</td>
	</tr>
	<tr>
		<th>신고내용 </th>
		<td>${vo.singo_content }</td>
	</tr>
	<tr>
		<th>신고대상자 </th>
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
			<input type="button" value="처리반려" disabled="disabled"  class="btn btn-secondary btn-lg">
			<input type="button" value="처리승인" disabled="disabled"  class="btn btn-primary btn-lg">
		</c:when>
		<c:otherwise>
			<input type="button" value="처리반려" onclick="goSubmit1(this.form)" id="btn1" class="btn btn-secondary btn-lg">
			<input type="button" value="처리승인" onclick="goSubmit2(this.form)" id="btn2" class="btn btn-primary btn-lg">
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