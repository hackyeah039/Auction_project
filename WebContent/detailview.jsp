<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		//int a_num=Integer.parseInt(request.getParameter("a_num"));
		int a_num=15;
	%>
<h1>물품이름 쓰는 곳입니다.</h1>
<P>*경매종료 후 입찰자에 한해 경매기록을 공개합니다.</P>
<div id="test">asd</div>
<select name="numbers" onchange="selectdd()" >
  <option value="10" <c:if test="${numbers=='10' }">selected</c:if>>10</option>
  <option value="20" <c:if test="${numbers=='20' }">selected</c:if>>20</option>
  <option value="30" <c:if test="${numbers=='30' }">selected</c:if>>30</option>
</select>
<table>
		<tr>
			<th>입찰일시</th><th>입찰자</th><th>금액</th>
		</tr>
		<c:forEach var="vo" items='${list }'>
			<tr>
				<td>${vo.getBid_date() }</td>
				<td>${vo.getM_num() }</td>
				<td>${vo.getBid_price() }</td>
			</tr>
		</c:forEach>
</table>

<c:choose>
	<c:when test="${startPage>5}">
		<a href="history.do?pageNum=${startPage-1 }&a_num=<%=a_num%>">[pre]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>


<c:forEach var="i" begin="${startPage }" end="${endPage }"> 
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="history.do?pageNum=${i }&a_num=<%=a_num%>">
			<span style='color :red'>[${i }]</span></a>
		</c:when>
		
		<c:otherwise>
			<a href="history.do?pageNum=${i }&a_num=<%=a_num%>">
			<span style='color :smokewhite'>[${i }]</span></a>
		</c:otherwise>	
	</c:choose>
</c:forEach>	


<c:choose>
	<c:when test="${endPage<paging }">
		<a href="history.do?pageNum=${endPage+1 }&a_num=<%=a_num%>">[next]</a>
	</c:when>
	<c:otherwise>
		이후
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	var xhr=null;
	
	function selectdd(){
		console.log("select함수 맨 처음 줄입니다.");
		${numbers}.value;
		String div=document.getElementById("test");
		div.innerHTML="asd";
		
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function (){
			if(this.readyState == 4 && this.status == 200){
				
			}
		}
		xhr.open('post' , 'history.do?field=${numbers}' , true);
		xhr.send();
	}
</script>
</body>
</html>