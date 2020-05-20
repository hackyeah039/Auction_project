<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table>
	<tr>
		<th>구분</th>
		<th>제목</th>
		<th>답변여부</th>
		<th>등록일</th>
		<th>답변일</th>
	</tr>

	<c:if test="${BoardListSize == 0 }">
		<tr>
			<td colspan="5">정보가 존재하지 않습니다.</td>
		</tr>
	</c:if>
	
	<c:forEach var = "boardvo" items="${BoardList }">
		<tr>
			<td>${boardvo.b_title}</td>
			<td>${boardvo.b_content}</td>
			
			<c:choose>
				<c:when test = "${boardvo.b_status==0}">
					<td>미답변</td>		
				</c:when>
				<c:otherwise>
					<td>답변 완료</td>
				</c:otherwise>			
			</c:choose>
			<c:if test="${not empty boardvo.answerdate}">
				<td>${boardvo.answerdate}</td>			
			</c:if>
		</tr>
	</c:forEach>
	
</table>
