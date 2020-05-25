<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${result == 'success'}">
		<div class="card" style="width: 50rem; margin: auto; margin-top: 50px" >
		  <div class="card-body">
		    <h5 class="card-title">주문/결제</h5>
		    <h6 class="card-subtitle mb-2 text-muted">주문이 완료 되었습니다.</h6>
		    <p class="card-text">${buyerName }님  주문이 완료 되었습니다.
			<c:choose>
				<c:when test="${bank == 0 }">
					<p><br>감사합니다.</p>
				</c:when>
				<c:otherwise>
				    <p><br>예금 계좌 : ${bank } : 0000000000 <br> 예금주 : (주) 4조auction)</p>
				</c:otherwise>
			</c:choose>
			<p>결제금액 : ${price }원</p>
			<p><a href = "${cp }/mypage/buyerTransact.do">돌아가기</a></p>
		  </div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="card" style="width: 18rem;">
		  <div class="card-body">
		    <h5 class="card-title">주문/결제</h5>
		    <h6 class="card-subtitle mb-2 text-muted">error : 결제 실패</h6>
		  </div>
		</div>
	</c:otherwise>
</c:choose>

