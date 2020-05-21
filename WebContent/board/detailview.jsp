<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	<%
		int a_num=Integer.parseInt(request.getParameter("a_num"));
	%>
	var xhr=null;
	
	function selectdd(){
		var selectone = document.getElementById("numbers");
		var div = document.getElementById("result");
		var selectValue =selectone.options[selectone.selectedIndex].value;
		
		console.log(selectValue);
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function (){
			if(this.readyState == 4 && this.status == 200){
				//div.innerHTML = "<table><tr><th>입찰일시</th><th>입찰자</th><th>금액</th></tr>"
				var data=JSON.parse(xhr.responseText);
				for(var i=0;i<data.length;i++){
				//	div.innerHTML += "<tr><td>${data[i].date}</td><td>${data[i].mnum }</td><td>${data[i].price }</td></tr>";
				}
				//div.innerHTML += "</table>";
			}
		}
		xhr.open('post' , 'history.do?field='+selectValue+'&pageNum=${pageNum}&a_num=${a_num}' , true);
		xhr.send();
	}
	
</script>
<body>
<select id="numbers" name="numbers" onchange="selectdd()" >
  <option value="10" <c:if test="${numbers=='10' }">selected</c:if>>10</option>
  <option value="20" <c:if test="${numbers=='20' }">selected</c:if>>20</option>
  <option value="30" <c:if test="${numbers=='30' }">selected</c:if>>30</option>
</select>
<div id="result">
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
</div>


<c:choose>
	<c:when test="${startPage>5}">
		<a href="history.do?pageNum=${startPage-1 }&a_num=<%=a_num%>">[pre]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>


<c:forEach var="i" begin="${startPage }" end="${endPage }" > 
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="history.do?pageNum=${i } & a_num=<%=a_num%>">
			<span style='color :red'>[${i }]</span></a>
		</c:when>
		
		<c:otherwise>
			<a href="history.do?pageNum=${i }&a_num=<%=a_num%>">
			<span style='color :smokewhite'>[${i }]</span></a>
		</c:otherwise>	
	</c:choose>
</c:forEach>	

<c:choose>
	<c:when test="${endPage<allpages }">
		<a href="history.do?pageNum=${endPage+1 }&a_num=<%=a_num%>">[next]</a>
	</c:when>
	<c:otherwise>
		이후
	</c:otherwise>
</c:choose>

</body>
</html>