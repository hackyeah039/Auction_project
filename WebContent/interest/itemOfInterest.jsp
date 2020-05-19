<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 
<form action="${cp}/interest/interest.do" method="post" onsubmit="return submitClick()">
 -->
	<h1>관심물품</h1>
	<table border = "1">
		<tr>
			<th>no</th>
			<th>제목</th>
			<th>현재가</th>
			<th>입찰</th>
			<th>조회</th>
			<th>판매자</th>
			<th>마감일</th>
			<th>구분</th>
			<th><input type="checkbox" onclick = "allClick()"></th>
		</tr>
		
		<c:if test="${getListSize == 0  }">
			<tr>
				<td colspan="8">정보가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<c:forEach var="anum" items="${interProductList}">
			<tr>
				<!--물품번호  -->
				<td>${anum.a_num }</td> 
				
				<!--물품제목  -->
				<td>${anum.a_title } </td>
				
				<!--현재가  -->
				<c:forEach var = "bidvo" items="${bidInfoList }">
					<c:if test="${bidvo.key == anum.a_num }">
						<td>${bidvo.value.bid_price }</td>
					</c:if>			
				</c:forEach>
	
				<!--입찰 수  -->
				<c:forEach var = "bidcount" items="${bidCountList }">
					<c:if test="${bidcount.key == anum.a_num }">
						<td>${bidcount.value}</td>
					</c:if>						
				</c:forEach>			
	
				<!--조회 -->
				<td>${anum.a_check } </td>
	
				<!--마감일 -->
				<td>${anum.a_enddate } </td>
				
				<!-- 판매자 Id -->
				<c:forEach var = "sellerIdList" items="${sellerIdList }">
					<c:if test="${sellerIdList.key == anum.a_num }">
						<td>${sellerIdList.value}</td>
					</c:if>						
				</c:forEach>
				
				<!-- 구분 -->			
				<c:choose>
					<c:when test="${anum.bidstatus==1}">
						<td>입찰 중</td>
					</c:when>
					<c:otherwise>
						<td>경매 마감</td>				
					</c:otherwise>
				</c:choose>
				
				<td>
					<input type = "checkbox" name = "checkbox" value = "${anum.a_num }">
				</td>	
			</tr>	
		</c:forEach>
	</table>
	<input type = "button" value = "삭제하기" onclick = "deleteClick()" >
<!-- 
</form>
 -->

<script>
	var checked1 = true;
	var checked2 = false;
	
	function allClick(){
		
		console.log(checked1);
		
		var temp;
		var ckb = document.getElementsByName("checkbox");
		
		for(var i = 0; i < ckb.length; i++){
			
			ckb[i].checked = checked1;
			
			if(i == ckb.length-1){
				temp = checked1;
				checked1 = checked2;
				checked2 = temp;
			}
			console.log(checked1);
		}	
		
	}

	/*ajax*/
	
	function deleteClick(){
		
		var checkbox = document.getElementsByName("checkbox");
		var msg = "";
		
		for(var i = 0; i < checkbox.length; i++){
			console.log(i);
			
			if(i==0){
				msg += "checkbox=" 								
			}else{
				msg += "&checkbox=" 								
			}
			
			
			
			if(checkbox[i].checked == true){
				msg += checkbox[i].value
			}
			console.log("msg : " + msg);
		}	
	/*	
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = getResult;
		xhr.open('post','${cp}/interest/interest.do',true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send();	
	*/
	}
	
</script>