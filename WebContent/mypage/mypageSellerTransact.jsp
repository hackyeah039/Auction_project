<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

/*
function showPopup(paynum,anum){
	 window.open("${cp}/popup/reqShipPopup.do?paynum="+paynum+"&anum="+anum, "배송요청", 
			 "width=400, height=300, left=100, top=50");
 }
 
*/ 

var xhr;

function clicksub (paynum, anum){
	
	xhr = new XMLHttpRequest;
	xhr.open('get','${cp}/popup/reqShipPopup.do?paynum='+paynum+'&anum='+anum,true)
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			var json = JSON.parse(data);
			
			var addr = json.addr.split(":")
			
			$("#anum").val(json.anum);
			$("#name").html(json.name);
			$("#addr").html("우편번호 : "+addr[0]+"<br>"+"주소 : " + addr[1] + addr[2]);
			$("#phone").html(json.phone);
			
			$("#myModal").modal('show');			
		}
	}
	xhr.send();
}

var xhr2;

function clicksub2 (){
	var anum = document.getElementById("anum").value;
	var courier = document.getElementById("courier").value;
	var invoicenum = document.getElementById("invoicenum").value;
	
	xhr2 = new XMLHttpRequest;
	xhr2.open('post','${cp}/popup/reqShipPopup.do',true)
	xhr2.onreadystatechange = onclicksub2;
	xhr2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr2.send("anum="+anum+"&courier="+courier+"&invoicenum="+invoicenum);
}

function onclicksub2(){
	if(xhr2.readyState == 4 && xhr2.status == 200){
		var data = xhr2.responseText;
		var json = JSON.parse(data);
	    if(json.message == "success"){
	    	alert("등록하였습니다.");
			$('#myModal').modal('hide');   //현재 팝업창 Close		    	
	    }else{
	    	alert("등록에 실패하였습니다. 다시입력해주세요");
	    }	
	}
}

</script>
<div id = "page-wrapper">
<jsp:include page="newLeftMenu.jsp"></jsp:include>

<div id="page-content-wrapper">
    <div class="container-fluid">
      <h3>거래 중인 리스트</h3>
    </div>

	<table  class="table table-bordered table-hover" style="text-align: center; 
		margin-top: 40px">
		<thead class = "thead">

		<tr>
			<th scope="col">NO</th>
			<th scope="col">물품명</th>
			<th scope="col">구매가격</th>
			<th scope="col">낙찰/구매일</th>
			<th scope="col">구매자</th>
			<th scope="col">거래상태</th><!-- 입금확인중, 입금완료,배송요청 -->
			<th scope="col">입금기한</th>
		</tr>

		<c:if test="${getListSize == 0  }">
			<tr> 
				<td colspan="7" scope="row">정보가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<c:set var="i" value="0"/>
		<c:forEach var="anum" items="${forSellerTranList}">
			
			<c:set var="i" value = "${i+1 }"/>
			<tr>
				<td scope="row">${i}</td>

				<!-- 물품명 -->
				<c:forEach var="titleList" items="${auctiontitleList}">
					<c:if test="${ titleList.key == anum }">
						<td><a href = '${cp}/main.do?a_num=${titleList.value}'>${titleList.value}</a></td>
					</c:if>
				</c:forEach>

				<!-- 경매 정보(물품명, 구매가격,낙찰일)-->
				<c:forEach var="bidinfo" items="${tranBidList}">
					<c:if test="${ bidinfo.a_num == anum}">
						<td>${bidinfo.bid_price}</td>
						<td>${bidinfo.bid_date}</td>
					</c:if>
				</c:forEach>
				
								
				<!-- 구매자  -->
				<c:forEach var="sellerId" items="${buyerId}">
					<c:if test="${ sellerId.key == anum }">
						<td>${sellerId.value}</td>
					</c:if>
				</c:forEach>

				<!-- 입금기한, 거래상태  -->
				<c:forEach var="payVo" items="${paymentList}">
					<c:if test="${ payVo.key == anum }">
						<c:choose>
							<c:when test="${payVo.value.pay_status == 0 }"><td>입금확인중</td></c:when>
							<c:when test="${payVo.value.pay_status == 1 }"><td><button type="button" class="btn btn-primary" id="openModalBtn" onclick = "clicksub (${payVo.value.pay_num }, ${anum})"> 배송요청</button></td></c:when>
						</c:choose>
						<td>${payVo.value.pay_deadline}</td>
					</c:if>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>
</div>





<!-- Modal content-->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>

        <div class="modal-body">
			  <h4 class="modal-title" id="title">구매자 정보</h4>
	          <table  class="table table-bordered" style="margin-top: 10px">
				<tr>
					<th class = "thead">이름</th>
					<td id="name"></td>
				</tr>		
				<tr>
					<th class = "thead">전화번호</th>
					<td id = "phone"></td>
				</tr>				
				<tr>
					<th class = "thead">배송지</th>					
					<td id = "addr"></td>
				</tr>
			</table>
			
			<h4>운송장 번호 정보</h4>
			<input type = "hidden" id="anum" name = "anum">	
			
			<div class="form-group">
			    <label for="courier">택배사</label>
				<input type = "text" name = "courier" id = "courier" class="form-control">			
  			</div>
  			
	  		<div class="form-group">
			    <label for="invoicenum">운송장번호</label>
				<input type = "text" name = "invoicenum" id = "invoicenum" class="form-control">			
	  		</div>
  
        </div>

        <div class="modal-footer">
        	<input type = "button" value = "등록하기" class="btn btn-primary" onclick = "clicksub2()">
         	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>  
