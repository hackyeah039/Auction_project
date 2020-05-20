<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table border="1" id="boardtable">
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

	<c:forEach var="boardvo" items="${BoardList }">
		<tr onclick="clickRow(this)">
			<td>${boardvo.b_num}</td>
			<td>${boardvo.b_title}</td>

			<c:choose>
				<c:when test="${boardvo.b_status==0}">
					<td>미답변</td>
				</c:when>
				<c:otherwise>
					<td>답변 완료</td>
				</c:otherwise>
			</c:choose>

			<td>${boardvo.b_regdate}</td>
			<c:choose>
				<c:when test="${boardvo.answerdate == null}">
					<td>0</td>
				</c:when>
				<c:otherwise>
					<td>${boardvo.answerdate}</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>
</table>

<a href="${cp }/board/userQnAInsert.do"><input type="button"
	value="등록하기"></a>

<script type="text/javascript">
	
	var check = true;
	var order= "0";
	
	function clickRow(obj){
		
		var table = document.getElementById("boardtable");
		var bnum = table.rows[obj.rowIndex].cells[0].outerText;
		
		xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				check[obj.rowIndex]
				if(check){
					var tr = table.insertRow(order+1);
					var cell = tr.insertCell(0);
					var data = xhr.responseText;
					var json = JSON.parse(data);
					
					cell.innerHTML += "질문 : ";
					cell.innerHTML += json.question+"<br>";
					cell.innerHTML += "답변 : ";
					cell.innerHTML += json.answer;
					cell.colSpan = 5;
					
					//check = false;
				}else{
					table.deleteRow(order+1);			
					//check = true;
				}
			}
		}
		
		if(obj.rowIndex == order){
			check = !check;
		}else{
			check = check;			
		}
		
		order = obj.rowIndex
		
		xhr.open('post','${cp}/board/userQnA.do',true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send("bnum="+bnum);	
	}

</script>
