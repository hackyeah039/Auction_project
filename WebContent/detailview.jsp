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
	var xhr=null;
	
	function select(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange() = function (){
			if(this.readyState == 4 && this.status == 200){
				
			}
		}
		xhr.open('post' , 'history.do' , true);
		xhr.send();
	}
</script>
<body>
<h1>물품이름 쓰는 곳입니다.</h1>
<P>*경매종료 후 입찰자에 한해 경매기록을 공개합니다.</P>

<select id="numbers" name="numbers">
  <option value="10" <c:if test="${numbers=='10' }">selected</c:if>>10</option>
  <option value="20" <c:if test="${numbers=='20' }">selected</c:if>>20</option>
  <option value="30" <c:if test="${numbers=='30' }">selected</c:if>>30</option>
</select>
<table>
		<tr>
			<th>입찰일시</th><th>입찰자</th><th>금액</th>
		</tr>
		<!-- 여기에 jstl써서  가져오고 Ajax JSON은 selectBox에서 바꿀때 콜백함수-->
		<c:forEach var="vo" items='${list }'>
			<tr>
				<td>${vo.getBid_date() }</td>
				<td>${vo.getM_num() }</td>
				<td>${vo.getBid_price() }</td>
			</tr>
		</c:forEach>
</table>
</body>
</html>