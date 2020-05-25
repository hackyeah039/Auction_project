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
<script src="${cp }/Auction/jquery.js"></script>
<title>Insert title here</title>
<style>
	p{
 		width: 100px;
 		height: 30px;
 		border-radius: 10px;
 		text-align: center;
 		padding-top: 8px;
 	}
</style>
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<p class="h4 text-white bg-primary mt-3 mb-3"> <strong>계좌 확인</strong></p>
<table>
	<tr>
		<td style="width: 100px; text-align: center;">
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
			<input type="button" value="전달" onclick="sendData()" class="btn btn-outline-Dark">
		</td>
	</tr>
</table>
</body>
</html>