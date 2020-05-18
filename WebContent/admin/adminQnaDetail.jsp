<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h1>QnA세부내용</h1>
<c:forEach var="vo" items="${list }">
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${vo.b_num }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${id }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${vo.b_title}</td>
		</tr>
		<tr>
			<th>문의내용</th>
			<td>${vo.b_content}</td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td>${vo.b_regdate}</td>
		</tr>
	</table>
<br>
<div id="dap" >
</div>
<c:choose>
	<c:when test="${vo.b_status == 0}"><!-- 답글이 없는 경우-->
		<div id="inputDap">  
		<input type="hidden" name="b_num" value="${vo.b_num}" id="b_num"><!-- 문의게시글 번호 -->
		<textarea rows="5" cols="50" name="b_dap" id="b_dap"></textarea>
		<input type="button" value="등록" onclick="insertDap()">
		</div>
	</c:when>
	<c:otherwise> <!-- 답글있는 경우 -->
		<input type="hidden" name="b_num" value="${vo.b_num }" id="b_num">
		<div id="dap">
			
		</div>
	</c:otherwise>
</c:choose>
</c:forEach>
<script>

	/*답글등록*/
	var xhr=null;
	function insertDap() {
		var b_num=document.getElementById("b_num").value;
		var b_dap=document.getElementById("b_dap").value;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=dapOk;
		xhr.open('post','${cp}/board.approval.jh',true);
		xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhr.send('b_num='+b_num+'&b_dap='+b_dap);
	}
	function dapOk() {
		if(xhr.readyState==4 && xhr.status==200){
			var msg=xhr.responseText;
			var json=JSON.parse(msg);
			if(json.msg=='ok') {
				getDap();
			}else{
				alert("등록실패");
			}
		}
	}
	/*리스트 불러오기*/
	var xhrDap=null;
	function getDap() {
		xhrDap=new XMLHttpRequest();
		xhrDap.onreadystatechange=daplistOk;
		var b_num=document.getElementById("b_num").value;
		xhrDap.open('post','${cp}/admin.getAnswer.jh',true);
		xhrDap.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhrDap.send('b_num='+b_num);
	}
	function daplistOk() {
		if(xhrDap.readyState == 4 && xhrDap.status == 200){
			var xml=xhrDap.responseText;
			var json=JSON.parse(xml);
			var dap=document.getElementById("dap");
			var inputDap=document.getElementById("inputDap");
			var div=document.createElement("div");
			console.log(json.b_dap);
			if(typeof json.b_dap == "undefined" || json.b_dap ==null || json.b_dap ==""){
				dap.innerHTML="";
			}
			div.innerHTML="답변내용:"+json.b_dap+"<br>"+
							"답변날짜:"+json.answerdate+"<br>";
			div.className="comm";
			dap.appendChild(div);
			inputDap.innerHTML="";
		}
	}
	window.onload = function test() {
		getDap();
	}
	
</script>





