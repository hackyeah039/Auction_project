<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
<div id = "wrap">
	<div id = header>
		<jsp:include page="${requestScope.header}"></jsp:include>
	</div>
	<div class = "container">
		<jsp:include page="${requestScope.content }"></jsp:include>	
	</div>
</div>
</body>
</html>