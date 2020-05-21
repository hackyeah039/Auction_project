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
		var div = document.getElementById("result");
		var selectone = document.getElementById("numbers");
		var selectValue =selectone.options[selectone.selectedIndex].value;
		console.log(selectValue +"선택한 값입니다.");
		document.getElementById("textA").value=selectValue;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function (){
			if(this.readyState == 4 && this.status == 200){
				div.innerHTML = "";
				var data=JSON.parse(xhr.responseText);
				for(var i=0;i<data.length;i++){
					div.innerHTML += "<tr><td>"+"입찰일시 : "+data[i].date+"</td><td>"+"  회원번호 : "+data[i].mnum+"</td><td>"+" 가격  : "+data[i].price+"</td></tr><br>";
				}
			}
		}
		xhr.open('post' , 'history.do' , true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send("field="+selectValue+"&pageNum=${pageNum}&a_num=${a_num}");
	}
	
</script>
<body>
<select id="numbers" onchange="selectdd()" >
  <option value="10" <c:if test="${field==10 }">selected</c:if>>10</option>
  <option value="20" <c:if test="${field==20 }">selected</c:if>>20</option>
  <option value="30" <c:if test="${field==30 }">selected</c:if>>30</option>
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

<div>
	<c:choose>
		<c:when test="${startPage>5}">
			<a href="history.do?pageNum=${startPage-1 }&a_num=<%=a_num%>">[pre]</a>
		</c:when>
		<c:otherwise>
			이전
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" > 
		<input type="hidden" id="textA">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a onclick ="selectdd()">
				<span style='color :red'>[${i }]</span></a>
			</c:when>
			
			<c:otherwise>
				<a onclick ="selectdd()">
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
</div>
</body>
</html>