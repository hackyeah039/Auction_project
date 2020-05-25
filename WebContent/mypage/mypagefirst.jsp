<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	
<div class="card border-secondary mb-3" style="width: 100%; margin-top: 50px;" >
  
  <div class="card-header">
		<h3 style="text-align: center">${id }님의 신용도는 ${trust }입니다.</h3>
		<ul class="nav">
			<li class="nav-item">
				<a class="nav-link active" href="${cp }/interest/interest.do">관심상품 리스트</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${cp }/board/userQnA.do">1:1 문의 하기</a>
			</li>
		</ul>
	</div>
	
  <div class="card-body text-secondary">
    <h5 class="card-title">My list</h5>
    <table class="table table-bordered" style="text-align: center;">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">입찰중</th>
	      <th scope="col">입금요청</th>
	      <th scope="col">판매중</th>
	      <th scope="col">배송요청</th>
	      <th scope="col">구매자 문의</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td>${bidCount }</td>
	      <td>${reqPayCount }</td>
	      <td>${saleCount }</td>
      	  <td>${shipReqCount }</td>
		  <td>${quesCount }</td>
	    </tr>
	  </tbody>
	</table>
  </div>

</div>

<div id = "button" style="margin-left: 450px">
	<button type="button" class="btn btn-primary btn-lg"
		onClick="location.href='${cp }/mypage/buyerBidding.do'">구매관리바로가기</button>
	<button type="button" class="btn btn-secondary btn-lg" 
	onClick="location.href='${cp }/mypage/sellerBidding.do?type=1'">판매관리바로가기</button>
</div>

<!-- 
<a href = "${cp }/mypage/buyerBidding.do"><input type="button" id = "btn1" value = "구매관리 바로가기" ></a>
<a href = "${cp }/mypage/sellerBidding.do"><input type="button" id = "btn2" value = "판매관리 바로가기" ></a>
 -->