<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#main {background-color: orange;}
	#divleft {background-color: gray; width: 150px; height: 800px;float:left}
	#divright {background-color: #dddddd; width: 700px; height: 800px;float:left}
</style>
<div id="main">
<div id="divleft">
	<span>신고리스트</span>
	<ul>
		<li><a href="">전체리스트</a></li>
		<li><a href="">신고검토중</a></li>
		<li><a href="">처리완료</a></li>
	</ul>
	<span>문의사항(QNA)</span>
	<ul>
		<li><a href="">전체글목록</a></li>
		<li><a href="">답변중</a></li>
		<li><a href="">답변완료</a></li>
	</ul>
	<span>회원리스트</span>
	<ul>
		<li><a href="">전체 회원리스트</a></li>
		<li><a href="">탈퇴처리중</a></li>
		<li><a href="">탈퇴 처리완료</a></li>
	</ul>
</div>
<div id="divright"></div>
</div>