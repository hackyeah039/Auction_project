<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<link rel="stylesheet" type="text/css" href="${cp }/css/index.css">
</head>
<body>
<div id="wrap">
<div id="top">
	<div>
		<jsp:include page="${top }"/>
	</div>	
</div>
<div id="main">
	<div>
		<jsp:include page="${content }"/>
	</div>
</div>
<div id="footer">
	<div>
		<jsp:include page="${footer }"></jsp:include>
	</div>
</div>
</div>
</body>
</html>