<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <link rel="stylesheet" href="btcss/bootstrap.css">
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="btjs/bootstrap.js"></script>
  <title>Blog Post - Start Bootstrap Template</title>
  <!-- Custom styles for this template -->
  <link href="css/blog-post.css" rel="stylesheet">
  <!-- <link rel= "stylesheet" type="text/css" href="css/main.css"> -->
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
  <%
  	int m_num = 0;
  	
  	if(session.getAttribute("m_num") != null ){
	  	m_num = (Integer)session.getAttribute("m_num");  		
  	}else{
  		session.setAttribute("m_num", 0);
  	}
  	
  	System.out.println("mainview" + m_num);
  %>
  
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
  <script>
	//찜
	function myFunction(){
	 	var allwindow= window.open("${cp}/jjim.do?m_number=${m_number}&a_num=${a_num}&m_num=<%=m_num%>", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=400,width=330,height=200");
	}
	//신고하기
	function singo(){
		var allwindow= window.open("${cp}/board/singo.jsp?sel_number=${seller}&m_num=<%=m_num%>", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=400,left=600,width=450,height=350");
	}
	//경매기록보기
	function bidlist(){
		var allwindow= window.open("${cp}/history.do?&a_num=${a_num }", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=200,left=300,width=600,height=600");
	}
	//입찰하기
	function bidding(){
		var allwindow= window.open("${cp}/Bidding.do?a_num=${a_num }&m_num=<%=m_num%>", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=200,left=300,width=500,height=200");
	}
	//경매시간
	var myVar = setInterval(function () {
		var now = new Date();//현재시간
		var enddate = new Date(${months}+"/"+${day}+"/"+${years});
		var distance =enddate.getTime()-now.getTime();
		
		var days = Math.floor(distance / (1000 * 60 * 60 * 24));
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
	
	function change(){
		var reply=document.getElementsByClassName("reply")[0];
		if(reply.style.visibility!='hidden'){
			reply.style.visibility='visible';
		}else if(reply.style.visibility!='visible'){
			reply.style.visibility='hidden'
		}
	}
	
	
	//문의하기 등록
	var xxml= null;
	
	function register(){
		var xxml = new XMLHttpRequest();
		xxml.open("post","${cp}/register.do",true);
		xxml.onreadystatechange = function(){
			if(xxml.readyState == 4 && xxml.status == 200){
				var xxml2=xxml.responseXML;
				var msg = xxml2.getElementsByTagName("result")[0].firstChild.nodeValue;
			}
		};
		xxml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xxml.send("context="+ encodeURIComponent(document.getElementById("context").value)+"&a_num=${a_num}&mnum=<%=m_num%>&title="+ encodeURIComponent(document.getElementById("title").value));
	}
	
	//답변하기
	function answer2(quenum){
		var allwindow= window.open("${cp}/board/answer.jsp?que_num="+quenum, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=400,left=600,width=450,height=550");
	}
	</script>
</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="containr">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">메인페이지
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Services</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container" id="a">
    <div class="row">

      <!-- Post Content Column -->
      <div class="col-lg-8">

        <!-- Title -->
        <h1 class="mt-4">경매 물품 제목 ${info.a_title}</h1>

        <!-- Author -->
        <p class="lead">
          4조
          <a href="#">김정욱</a>
        </p>

        <hr>
	
		
		<div id="carouselExampleControls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				 <c:forEach var="vo" items="${ipath}" varStatus="loop">
					<div class="carousel-item">
						<img class="d-block w-100" src="/images/${vo}" alt="Second slide">
					</div>
				 </c:forEach>
				 <div class="carousel-item active">
					 <img class="d-block w-100" src="${cp}/image/1.jpg" alt="First slide">
				 </div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>

		</div>
		
		
		
		
      <!-- Sidebar Widgets Column -->
      <div class="col-md-4">
        <!-- Categories Widget -->
        <div class="card my-4">
          <h5 class="card-header">카테고리</h5>
          <div class="card-body">
            <div class="row">
              <div class="col-lg-12">
                <ul class="list-unstyled mb-0">
                  <ol class="breadcrumb">
  					<li class="breadcrumb-item"><a href="#">홈</a></li>
  					<li class="breadcrumb-item"><a href="#">온라인경매</a></li>
  					<li class="breadcrumb-item active">${cate }</li>
				  </ol>
                </ul>
              </div>
            </div>
          </div>
        </div>

        <!-- Side Widget -->
        <div class="card my-4">
          <h5 class="card-header">경매 남은시간 <div id="result" style="width:100%; height:20px"></div></h5>
          <h5 class="card-header">${info.a_startdate } 부터 ${info.a_enddate } 까지</h5>
          <h5 class="card-header">입찰수  : ${bidnum }</h5>
          <h5 class="card-header"><a href="" onclick="bidlist()">경매기록보기</a></h5>
          <h5 class="card-header">물품번호 ${info.a_num }</h5>
          <h5 class="card-header">시작가 ${info.a_startbid }</h5>
          <h5 class="card-header">입찰단위 ${info.a_bidunit }</h5>
          <h5 class="card-header">입찰방식 : 비공개</h5>
          <h5 class="card-header">배송방법 ${ship.s_way } </h5>
          <h5 class="card-header">배송비용 : ${ship.s_price }원 </h5>
          <h5 class="card-header">판매자 ID : <a href="" onclick="singo()">${seller }</a> </h5>
          <h5 class="card-header"><a href="" onclick="bidding()">입찰하기</a> </h5>
          <h5 class="card-header"><a href="" onclick="myFunction()">관심물품 찜하기</a> </h5>
        </div>
		
      </div>
		
		
        <hr>
      <p></p>
<div class="container">
	<div class="table-responsive">
    <table class="table table-hover">
        <thead class="thead-light">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>등록자</th>
                <th>등록일</th>
            </tr>
        </thead>
        <tbody>
          <c:forEach var="vo" items="${list }" varStatus="status">
            <tr class="clickable table-dark" data-toggle="collapse" id="${status.count}" data-target=".${status.count}collapsed">
                <td>${vo.rnum}</td>
				<td>${vo.que_title}</td>
				<td>${vo.m_num}</td>
				<td>${vo.que_regdate }</td>
            </tr>
            <tr class="collapse out budgets ${status.count}collapsed">
                <td><i class="material-icons md-36">contact_support</i></td>
				<td>${vo.que_content }</td>
				<td></td>
				<td>${vo.que_regdate } </td>
            </tr>
            <tr class="collapse out budgets ${status.count}collapsed">
                <td><i class="material-icons md-36">done</i></td>
				<td>${vo.b_content }</td>
				<td></td>
				<c:choose>
					<c:when test="${seller != 0 }">
						<td>${vo.ans_regdate} <button type="button" class="btn btn-primary pull-right" onclick="answer2(${vo.que_num})">답변하기</button></td>					
					</c:when>
					<c:otherwise>
						<td>${vo.ans_regdate}</td>
					</c:otherwise>
				</c:choose>
				
            </tr>
           </c:forEach>
        </tbody>
    </table>
    </div>
</div>


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
				<a href="${cp }/main.do?pageNum=${i}&keyword=${keyword}&a_num=${a_num}">
				<span style='color:blue'>[${i }]</span></a>			
			</c:when>
			<c:otherwise>
				<a href="${cp }/main.do?pageNum=${i}&keyword=${keyword}&a_num=${a_num}">
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
		

    </div>
    <!-- /.row -->
<div>		
        <!-- 문의하기 폼 -->
        <div class="card my-4">
          <h5 class="card-header">문의하기</h5>
          <div class="card-body">
            <form>
              <div class="form-group">
                <textarea class="form-control" rows="1" id="title" placeholder="제목을 입력해주세요"></textarea>
              </div>
              <div class="form-group">
                <textarea class="form-control" rows="5" id="context" placeholder="내용을 입력해주세요"></textarea>
              </div>
              <button type="button" class="btn btn-primary pull-right" onclick="register()">등록</button>
            </form>
          </div>
        </div>
      </div>
</div>

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">4조 &copy; 김정욱</p>
    </div>
    <!-- /.container -->
  </footer>
  

<script type="text/javascript">
  window.onload=function(){
	  var a=document.getElementById("a");
	  a.style.height="1600px";
  }
</script>


</body>

</html>
