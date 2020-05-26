<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var xml = null;

function answer(){
	xml = new XMLHttpRequest();
	xml.onreadystatechange = react;
	xml.open("post","${cp}/answer.do",true);
	xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xml.send("context="+encodeURIComponent(document.getElementById("context").value)+"&que_num="+<%=request.getParameter("que_num")%>);
}
function react(){
	var div = document.getElementById("div");//div
	var data = xml.responseXML;
	var Msg = data.getElementsByTagName("Msg")[0].firstChild.nodeValue;
	if(Msg!=null){
		div.innerHTML="";
		div.innerHTML=Msg;
	}
}
</script>
</head>
<body>
<div id="div">
 <textarea name="textareas" rows="10" cols="50" id="context" placeholder="답변내용을 적어주시면 됩니다."></textarea>
 <input type="button" onclick="answer()" value="등록">
</div> 
</body>
</html>