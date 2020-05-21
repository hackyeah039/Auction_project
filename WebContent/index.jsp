<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id = "wrap">
	<div id = header>
		<jsp:include page="${cp}/main_sh/layoutTest.jsp"></jsp:include>
	</div>
	<div id = content>
		<jsp:include page="${requestScope.content }"></jsp:include>	
	</div>
</div>
