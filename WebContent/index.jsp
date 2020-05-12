<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${cp }/css/Mypage.css">
<title>index.jsp</title>
</head>
<body>
<div id = "wrap">
	<div id = header>
		<jsp:include page="${header }"></jsp:include>
	</div>
	<div id = content>
		<jsp:include page="${content }"></jsp:include>	
	</div>
</div>
</body>
</html>