<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>틀</title>
</head>
<body>
<div id = "wrap">
	<div id = header style="height: 500px;border: solid 2px black;">
		<%-- <jsp:include page="${requestScope.header}"></jsp:include>--%>
	</div>
	<div id = content>
		<jsp:include page="${requestScope.content }"></jsp:include>	
	</div>
</div>
</body>
</html>