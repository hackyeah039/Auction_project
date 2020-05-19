<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	th {text-align: left;}
</style>

<h1>회원가입</h1>
<div>
<form method="post" action="${cp }/join/insert.jh">
	<table border="1" >
		<tr>
			<th>* 이름 : </th>
			<td><input type="text" name="name" id="name" onkeyup="nameck()">
			<span id="nameckmsg" style="color:red; font-size:10"></span></td>
		</tr>
		<tr>
			<th>* 아이디 : </th>
			<td><input type="text" name="id" id="id" onkeyup="idck()">
			<span id="idckmsg" style="color:red; font-size:10"></span></td>
		</tr>
		<tr>
			<th>* 비밀번호 : </th>
			<td><input type="password" name="pwd" id="pwd" onkeyup="pwdCk(this)">
			<span id="pwdckmsg" style="color:red; font-size:10"></span></td>
		</tr>
		<tr>
			<th>* 비밀번호 확인 : </th>
			<td><input type="password" name="pwdck" id="pwdck" onkeyup="pwdDubleCk()">
			<span id="pwdckmsg2" style="color:red; font-size:10"></span></td>
		</tr>
		<tr>
			<th>* 이메일 : </th>
			<td><input type="text" name="email" id="email" onkeyup="emailCk()">
			<span id="emailckmsg" style="color:red; font-size:10"></span></td>
		</tr>
		<tr>
			<th>* 주소 : </th>
			<td><input type="text" name="addr" id="addr" onkeyup="addrCk()">
			<span id="addrckmsg" style="color:red; font-size:10"></span></td>
		</tr>
		<tr>
			<th>* 연락처 : </th>
			<td><input type="text" name="phone" id="phone" onkeyup="phoneCk()">
			<span id="phoneckmsg" style="color:red; font-size:10"></span></td>
		</tr>
	</table>
	<br>
	<a href="${cp }/home.jiho"><input type="button" value="취소"></a>
	<input type="submit" value="가입" id="insertBt" disabled="disabled">
</form>
</div>
<script>
	/*가입버튼 boolean*/
		var namebool=false;
		var idbool=false;
		var pwdbool=false;
		var emailbool=false;
		var addrbool=false;
		var phonebool=false;
		
		/*이름 유효성 검사*/
		function nameck() { 
			var name=document.getElementById("name").value;
			var nameckmsg=document.getElementById("nameckmsg");
			var nameck= /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
			if(name.length>=4){  /*이름 4자 이상이면x*/
				nameckmsg.innerHTML="이름은 4자 이하로 입력해주세요.";
				namebool=false;			
			}else{
				nameckmsg.innerHTML="";
				namebool=true;
			}
			/*이름에 한글외 문자 입력시 x*/
			if(nameck.test(name)){
				nameckmsg.innerHTML="이름은 한글만 입력해주세요.";
				namebool=false;	
			}else{
				nameckmsg.innerHTML="";
				namebool=true;
			}
			bt();
		}
		//주소입력검사
		function addrCk() { 
			var addr=document.getElementById("addr").value;
			var addrckmsg=document.getElementById("addrckmsg");
			if(addr.length<1){  
				addrckmsg.innerHTML="주소를 입력해주세요.";
				addrbool=false;			
			}else{
				addrckmsg.innerHTML="";
				addrbool=true;
			}
			bt();
		}
		//아이디 중복검사
		var xhrid=null;
		function idck() { 
			var id=document.getElementById("id").value;
			xhrid=new XMLHttpRequest();
			xhrid.onreadystatechange=idckok;
			xhrid.open('post','${cp}/join/idck.jh',true);
			xhrid.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			console.log(id);
			xhrid.send('id='+id);
		}
		function idckok() {
			var idckmsg=document.getElementById("idckmsg");
			var id=document.getElementById("id").value;
			var id_alphanumber = /^[A-Za-z0-9]*$/ ;  
			if(xhrid.readyState==4 && xhrid.status==200){
				var msg=xhrid.responseText;
				var json=JSON.parse(msg);
				if(json.msg=='error'){
					idckmsg.innerHTML="사용중인 아이디 입니다.";
					idbool=false;
				}else if(!(id.length>=5)){
					idckmsg.innerHTML="아이디는 5자 이상으로 입력해주세요.";
					idbool=false;
				}else if(!(id.length<=10)){
					idckmsg.innerHTML="아이디는 10자리 이하로 입력해주세요.";
					idbool=false;
				}else if(!(id_alphanumber.test(id))){
					idckmsg.innerHTML="아이디는 영문과 숫자만 입력해주세요.";
					idbool=false;
				}else{
					idckmsg.innerHTML="";
					idbool=true;
				}
				bt();
			}		
		}
		/*비밀번호 유효성검사*/
		function pwdCk(pwd) {
			var pwd=pwd.value;
			var pwdckmsg=document.getElementById("pwdckmsg");
			var pwd_alphanumber = /^[A-Za-z0-9]*$/ ; 
			if(!(pwd.length>=8 && pwd.length<=25)){
				pwdckmsg.innerHTML="비밀번호는 8~25자리 사이로 입력해주세요.";
				return;
			}else if(!(pwd_alphanumber.test(pwd))){
				pwdckmsg.innerHTML="비밀번호는 영문과 숫자만 입력해주세요.";
				return;
			}else{
				pwdckmsg.innerHTML="";
			}
		}
		/*비밀번호 확인하기*/
		function pwdDubleCk() {
			var pwd=document.getElementById("pwd").value;
			var pwdck=document.getElementById("pwdck").value;
			var pwdckmsg2=document.getElementById("pwdckmsg2");
			if(!(pwd==pwdck)){
				pwdckmsg2.innerHTML="입력한 비밀번호와 다릅니다.";
				pwdbool=false;
			}else{
				pwdckmsg2.innerHTML="";
				pwdbool=true;
			}
			bt();
		}
		/*이메일 중복검사*/
		var xhremail=null;
		function emailCk() {
			var email=document.getElementById("email").value;
			xhremail=new XMLHttpRequest();
			xhremail.onreadystatechange=emailckOk;
			xhremail.open('post','${cp}/join/emailck.jh',true);
			xhremail.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			xhremail.send('email='+email);
		}
		function emailckOk() {
			var emailckmsg=document.getElementById("emailckmsg");
			var email=document.getElementById("email").value;
			if(xhremail.readyState==4 && xhremail.status==200){
				var msg=xhremail.responseText;
				var json=JSON.parse(msg);
				if(json.msg=='error'){
					emailckmsg.innerHTML="사용중인 이메일 입니다.";
					emailbool=false;
				}else if(email==''){
					emailckmsg.innerHTML="이메일을 입력해주세요.";
					emailbool=false;
				}else if(email.indexOf('@')==-1){
					emailckmsg.innerHTML="올바르지 않은 이메일 형식입니다.";
					emailbool=false;
				}else{
					emailckmsg.innerHTML="";
					emailbool=true;
				}
			}
			bt();
		}
		
		var btn = document.getElementById("insertBt");

		function phoneCk() {
			var phone=document.getElementById("phone").value;
			var phoneckmsg=document.getElementById("phoneckmsg");
			var phoneck=/^[0-9]+$/; 
			if(phone.length>13){
				phoneckmsg.innerHTML="번호는 13자리 이하로 입력해주세요.";
				phonebool=false;
			}else if(phone.length<1){
				phoneckmsg.innerHTML="번호를 입력해주세요";
				phonebool=false;
			}else if(!(phoneck.test(phone))){
				phoneckmsg.innerHTML="번호는 숫자만 입력해주세요.";
				phonebool=false;
			}else{
				phoneckmsg.innerHTML="";
				phonebool=true;
			}
				bt();
		}
		function bt() {
			if(phonebool==true && namebool==true && idbool==true && pwdbool==true 
					&& emailbool==true && addrbool==true){
				insertBt.disabled=false;
			}else{
				btn.disabled=true;
			}
		}
</script>
