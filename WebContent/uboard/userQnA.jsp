<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id = "page-wrapper">
 <div id="page-content-wrapper">
    <div class="container-fluid" style="margin-top: 50px">
    	<h1>1:1 문의게시판</h1>	
			<table  class="table table-bordered table-hover" border=1 style="text-align: center; 
			margin-top: 40px"> 
				<thead class = "thead">
				<tr>
					<th scope="col">구분</th>
					<th scope="col">제목</th>
					<th scope="col">답변여부</th>
					<th scope="col">등록일</th>
					<th scope="col">답변일</th>
				</tr>
				</thead>

	<c:if test="${BoardListSize == 0 }">
		<tr>
			<td colspan="5" scope="row">정보가 존재하지 않습니다.</td>
		</tr>
	</c:if>
	
	<c:set var="i" value="0"/>
	<c:forEach var="boardvo" items="${BoardList }">
		<c:set var="i" value = "${i+1 }"/>
		<tr>			
			<td>${boardvo.key.b_num}</td>
			<td><a data-toggle="collapse" aria-controls="collapseExample" aria-expanded="false" href="#collapseExample${i}">${boardvo.key.b_title}</a></td>

			<c:choose>
				<c:when test="${boardvo.key.b_status==0}">
					<td>미답변</td>
				</c:when>
				<c:otherwise>
					<td>답변 완료</td>
				</c:otherwise>
			</c:choose>

			<td>${boardvo.key.b_regdate}</td>
			<c:choose>
				<c:when test="${boardvo.key.answerdate == null}">
					<td>0</td>
				</c:when>
				<c:otherwise>
					<td>${boardvo.key.answerdate}</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr class="collapse" aria-expanded="false" aria-controls="collapseExample" id="collapseExample${i}">
			<td  colspan="5" style="text-align: left"><div>질문 : ${boardvo.key.b_content}<br>
			<c:choose>
				<c:when test="${boardvo.value == null }">답변 : <br> 잠시만기다려주세요... </c:when>
				<c:otherwise>답변 : <br>${boardvo.value }</c:otherwise>
			</c:choose>	
			</div></td>
		<tr>
	</c:forEach>
	
</table>

<a href="${cp }/board/userQnAInsert.do"><input type="button" class="btn btn-secondary"
	value="등록하기"></a>
	</div>
	</div>
	</div>

<script type="text/javascript">
	/*
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
*/

</script>
