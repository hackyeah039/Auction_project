<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 최대값 사용을 위해 변수에 담음.
var maxBid="<c:out value='${maxBid}'/>";
//onsubmit 함수 필요 유효성 체크
function chkBidPrice() {
	//최대 가격 체크
	let bid_price = document.getElementsByName("bid_price")[0].value;
	console.log(bid_price);
	if(bid_price.trim() == ""){
		alert("입찰가격을 입력하세요.");
		return false;
	} else if(bid_price.length > 8){
		alert("입찰가격 입력범위를 초과하였습니다.");
		return false;
	} else if(bid_price < maxBid){
		alert("입찰가격이 입찰가능 금액보다 낮을 수 없습니다.");
		return false;
	}
	return true;
} 
</script>
</head>
<body>
<form action="${cp }/Bidding.do" onsubmit="return chkBidPrice()">
<table>
	<tr>
		<td>
			<input type="hidden" name="a_num" value="${a_num }" >
		</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="m_num" value="${m_num }" >	
		</td>
	</tr>
	<tr>
		<td>
			입찰 가능 가격 : ${maxBid }
		</td>
	</tr>	
	<tr>
		<td>
			<input type="text" name="bid_price">		
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="입찰">
		</td>
	</tr>	
</table>
</form>
</body>
</html>