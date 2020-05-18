<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>판매자 아이디  &nbsp;&nbsp; <%=request.getParameter("seller") %></h1> 

<form action ="singo.do">
 <textarea name="textareas" rows="10" cols="50" placeholder="신고내용을 적어주시면 됩니다."></textarea>
 <input type="submit" value="등록">
</form>
</body>
</html>