<%@page import="semi.vo.yh.SellerVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="semi.dao.yh.SellerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function sendData() {
		if(document.getElementById("useAccount").value != ""){
			var array = document.getElementById("useAccount").value.split(':');
			opener.document.getElementById("account").value = array[0];
			opener.document.getElementById("account").readOnly = true;		
			opener.document.getElementById("sel_number").value = array[1];
			window.close();				
		} else {
			opener.document.getElementById("account").value = "";
			window.close();
		}
	}
</script>
</head>
<body>
<h2>등록된 계좌번호</h2>
<table>
	<tr>
		<td>
			계좌번호
		</td>
		<td>
			<select id ="useAccount">
			<c:choose>
			<c:when test="${size != 0 }">
				<c:forEach var="vo" items="${list }">
					<option value="${vo.account}:${vo.sel_number}">${vo.account }</option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<option value="">없음</option>
			</c:otherwise>
			</c:choose>
			</select>
			<input type="button" value="전달" onclick="sendData()">
		</td>
	</tr>
</table>
</body>
</html>