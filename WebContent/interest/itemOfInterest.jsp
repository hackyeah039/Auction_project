<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 
<form action="${cp}/interest/interest.do" method="post" onsubmit="return submitClick()">
 -->
 <div id = "page-wrapper">
 <div id="page-content-wrapper">
    <div class="container-fluid" style="margin-top: 50px">
	<h1>관심물품</h1>
		<table  class="table table-bordered table-hover" border=1 style="text-align: center; 
		margin-top: 40px"> 
		<thead class = "thead">
			<tr>
				<th scope="col">no</th>
				<th scope="col">제목</th>
				<th scope="col">현재가</th>
				<th scope="col">입찰</th>
				<th scope="col">조회</th>
				<th scope="col">마감일</th>
				<th scope="col">판매자</th>
				<th scope="col">구분</th>
				<th scope="col"><input type="checkbox" onclick = "allClick()"></th>
			</tr>
		</thead>
		
		<c:if test="${getListSize == 0  }">
			<tr>
				<td colspan="9" scope="row">정보가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<c:set var="i" value="0"/>
		<c:forEach var="anum" items="${interProductList}">
			<c:set var="i" value = "${i+1 }"/>
			<tr>
				
				<td scope="row">${i}</td>
				
				<!--물품제목  -->
				<td><a href="${cp }/main.do?anum=${anum.a_num}">${anum.a_title }</a></td>
				
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
	<div>
		<input type = "button" value = "삭제하기" onclick = "deleteClick()"class="btn btn-secondary" >
	<div>
	</div>
</div>
</div>
</div>
</div>

	
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
		var count = 0;
		
		for(var i = 0; i < checkbox.length; i++){
			console.log(i);
			
			if(checkbox[i].checked == true){
				if(count==0){
					msg += "checkbox=" 								
				}else{
					msg += "&checkbox=" 								
				}
				msg += checkbox[i].value;
				count++;
			}
			console.log("msg : " + msg);
		}	
		
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				var data = xhr.responseText;
 				var json = JSON.parse(data);
				
			    if(json.message == "success"){
			    	var table = document.getElementById("interesttable");
					for(var i = 1; i < table.rows.length; i++){
						console.log("i : " +i);
						var chkbox = table.rows[i].cells[8].childNodes[1].checked;
						
						if(chkbox){
							table.deleteRow(i);
							i--;
						}
					}
			    	
			    }else{
			    	alert("삭제에 실패하였습니다.");
			    }	
			}
		};
		xhr.open('post','${cp}/interest/interest.do',true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send(msg);	
	}
	
	
	
</script>