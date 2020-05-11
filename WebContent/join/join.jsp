<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<style>
	th {text-align: left;}
</style>
<form method="post" action="">
	<table border="1">
		<tr>
			<th>* 이름 : </th>
			<td><input type="text" name="name"><span>테스트용 메세지</span></td>
		</tr>
		<tr>
			<th>* 아이디 : </th>
			<td><input type="text" name="id">
			<input type="button" value="아이디 중복검사" id="idckBt"></td>
		</tr>
		<tr>
			<th>* 비밀번호 : </th>
			<td><input type="password" name="pwd"><span>테스트용 메세지</span></td>
		</tr>
		<tr>
			<th>* 비밀번호 확인 : </th>
			<td><input type="password" name="pwdck"><span>테스트용 메세지</span></td>
		</tr>
		<tr>
			<th>* 이메일 : </th>
			<td><input type="text" name="email1">@<input type="text" name="email2">
			<span>테스트용 메세지</span></td>
		</tr>
		<tr>
			<th>* 주소 : </th>
			<td><input type="text" name="addr"><span>테스트용 메세지</span></td>
		</tr>
		<tr>
			<th>* 연락처 : </th>
			<td><input type="text" name="phone"><span>테스트용 메세지</span></td>
		</tr>
	</table>
	<br>
	<input type="button" value="취소"><!-- 취소버트 누르면 메인으로..? -->
	<input type="submit" value="가입">
</form>
<script>
	var idckBt=document.getElementById("idckBt");



</script>