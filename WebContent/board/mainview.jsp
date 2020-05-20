<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equi="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
	<link rel= "stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" href="btcss/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="btjs/bootstrap.js"></script>
	<script>
		//찜
		function myFunction(){
		 	var allwindow= window.open("${cp}/jjim.do?sel_number=${seller}&a_num=${a_num}", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=200,left=500,width=250,height=100");
		}
		//신고하기
		function singo(){
			var allwindow= window.open("${cp}/board/singo.jsp?sel_number=${seller}&a_num=${a_num}", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=400,left=600,width=400,height=300");
		}
		//경매기록보기
		function bidlist(){
			var allwindow= window.open("${cp }/history.do?a_num=${a_num }", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=400,left=600,width=500,height=400");
		}
		//경매시간
		var myVar = setInterval(function () {
			var now = new Date();//현재시간
			var enddate = new Date(${months}+"/"+${day}+"/"+${years});
			var distance =enddate.getTime()-now.getTime();
			
			var days = Math.floor(distance / (1000 * 60 * 60 * 24));
			days
		  	var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		  	var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		  	var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		  	
		  	
			document.getElementById("result").innerHTML = days + "일 " + hours + "시 "
			  + minutes + "분 " + seconds + "초 ";
			
			if (distance < 0) {
			    clearInterval(myVar);
			    document.getElementById("result").innerHTML = "종료된 경매입니다";
			}
		}, 1000);
		
		//검색
		var xml= new XMLHttpRequest();	
		
		function searchFunction(){
			xml.open("POST","${cp}/main.do?keyword=" +encodeURIComponent(document.getElementById("keyword").value),true);
			xml.onreadystatechange = searchProcess;
			xml.send();
		}
		
		function searchProcess(){
			var table = document.getElementById("ajaxTable");
			if(xml.readyState == 4 && xml.status == 200){
				var result =JSON.parse(xml.responseText);
				for(var i=0;i<result.length();i++){//오브젝트상태
					var row = table.insertRow(0);//내용제목
					var row1 = table.insertRow(1);//내용컨텐트
					var row2 = table.insertRow(2);//Ask컨텐트
					
					var cell1 = row.insertCell(0);//첫번째줄
					var cell2 = row.insertCell(1);
					var cell3 = row.insertCell(2);
					var cell4 = row.insertCell(3);
					
					var cell5 = row1.insertCell(0);//두번째줄
					var cell6 = row1.insertCell(1);
					
					var cell7 = row2.insertCell(0);//세번째줄
					var cell8 = row2.insertCell(1);
					
					cell1.innerHTML += result[i].rnum;
					cell2.innerHTML += result[i].que_title;
					cell3.innerHTML += result[i].m_num;
					cell4.innerHTML += result[i].que_regdate;
					
					cell5.innerHTML += '<img src="${cp }/image/q.svg" class="img-fluid" alt="Responsive image">';
					cell6.innerHTML += result[i].que_content;
					
					cell7.innetHTML += '<img src="${cp }/image/a.svg" class="img-fluid" alt="Responsive image">';
					cell8.innerHTML += result[i].b_content;
				}
			}
		}
		
	</script>
	<script src="js/mainjs.js"></script>
</head>
<body>
홈>온라인경매>${cate }<br>
<br>
경매 물품 제목 ${info.a_title}<br>
<br>
<div class="container">
	<ul class="slider-container simple-list" id="slider">
		<c:forEach var="vo" items="${ipath }">
			<li class ="slide">
				<img src="${cp}/image/${vo}" width="200" height="300">
			</li>
		</c:forEach>
	</ul>
	
	<a href="#" id="prev"></a>
	<a href="#" id="next"></a>
</div>
<br>
 경매기간 ${info.a_startdate } ~~ ${info.a_enddate }<br> 
 경매 남은시간 <div id="result"></div>
물품번호 ${info.a_num }<br>
시작가 ${info.a_startbid }<br>
입찰단위 ${info.a_bidunit }<br>
입찰방식 : 비공개<br>
입찰수  : ${bidnum }<br>
<a href="" onclick="bidlist()">경매기록보기</a> <br>
배송방법 ${ship.s_way }<br>
배송비용 ${ship.s_price }<br>
판매자 ID : <a href="" onclick="singo()">${seller }</a><br>
<a href="">입찰하기</a><br>
<a href="" onclick="myFunction()">관심물품 찜하기</a><br>

<table class="table table-condensed" style="text-align: center; border: 1px soli #dddddd; border-collapse:collapse">
	<thead>
		<tr>
			<th style="background-color: #fafafa; text-align: center;">번호</th>
			<th style="background-color: #fafafa; text-align: center;">제목</th>
			<th style="background-color: #fafafa; text-align: center;">등록자</th>
			<th style="background-color: #fafafa; text-align: center;">등록일</th>
		</tr>
	</thead>
	<tbody id="ajaxTable">
		<c:forEach var="vo" items="${list }">
			<tr data-toggle="collapse" data-target=".demo${vo.rnum }" class="accordion-toggle"><!-- 문의라인 -->
					<td>${vo.rnum }</td>
					<td>${vo.que_title }</td>
					<td>${vo.m_num }</td>
					<td>${vo.que_regdate }</td>
			</tr>
			<tr><!-- 문의내용라인 -->
				 	<td colspan="6" class="hiddenRow">
					<div class="accordian-body collapse p-3" class="demo${vo.rnum }">
						<img src="${cp }/image/q.svg" class="img-fluid" alt="Responsive image">
					</div>
					</td>
				 	<td colspan="6" class="hiddenRow">
					<div class="accordian-body collapse p-3" class="demo${vo.rnum }">
						${vo.que_content }
					</div>
					</td>
				</div>
			</tr>
			<tr><!--답변라인 -->
					<td colspan="6" class="hiddenRow">
					<div class="accordian-body collapse p-3" class="demo${vo.rnum }">
						<img src="${cp }/image/a.svg" class="img-fluid" alt="Responsive image">
					</div>
					</td>
				 	<td colspan="6" class="hiddenRow">
					<div class="accordian-body collapse p-3" class="demo${vo.rnum }">
						${vo.b_content }
					</div>
					</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 이전 -->
<c:choose>
	<c:when test="${startPage>4 }">
		<a href="${cp }/main.do?pageNum=${startPage-1 }">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>


	<!-- 페이지 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage}">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="${cp }/main.do?pageNum=${i}&keyword">
				<span style='color:blue'>[${i }]</span></a>			
			</c:when>
			<c:otherwise>
				<a href="${cp }/main.do?pageNum=${i}&keyword=${keyword}">
				<span style='color:#999'>[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	
<!-- 다음 -->
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="${cp }/main.do?pageNum=${endPage+1 }">[다음]</a>
	</c:when>
	<c:otherwise>
		이후
	</c:otherwise>
</c:choose>


<div class="container">
		<div class="col-xs-2">
			<button class="btn btn-primary" type="button">문의하기</button>
		</div>
	<div class ="form-group row pull-right"><!-- 폼그룹 오른쪽정렬 -->
		<div class="col-xs-8">
			<input class="form-control" type="text" size="20" id="keyword" name="keyword" value="${keyword }" onkeyup="searchFunction()">
		</div>
		<div class="col-xs-2">
			<button class="btn btn-primary" type="button" onclick ="searchFunction()">검색</button>
		</div>
	</div>
</div>

</body>
</html>