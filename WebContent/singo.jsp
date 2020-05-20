<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	var xml = null;
	
	function errMsg(){
		xml = new XMLHttpRequest();
		xml.onreadystatechange = react
		xml.open('get','${cp }/singo.do?a_num=<%=request.getParameter("a_num")%>',true);
		xml.send();
	}
	function react(){
		var div = document.getElementById("div");//div
		var data = xml.responseXML;
		var errMsg = data.getElementsByTagName("errMsg")[0].firstChild.nodeValue;
		if(errMsg!=null){
			div.innerHTML="";
			div.innerHTML=errMsg;
		}
	}
</script>
</head>
<body>
<div id="div">
 <h1>경매물품 <%=request.getParameter("a_num")%></h1>
 <h1>판매자 번호 : <%=request.getParameter("sel_number")%></h1>
 <textarea name="textareas" rows="10" cols="50" placeholder="신고내용을 적어주시면 됩니다."></textarea>
 <input type="submit" id="inputt" onclick="errMsg()" value="등록">
</div> 
</body>
</html>