<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<style>
	.form{margin-top: 100px}
	
</style>	
<div id="form">
<c:forEach var="vo" items="${list }">
	<table border="1" width="500">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="m_id" class="form-control" id="inputPassword" 
			value="${vo.m_id }" readonly="readonly">
			</td>
			<td></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="m_name" class="form-control" id="inputPassword" 
			value="${vo.m_name }" onkeyup="nameCk()" readonly="readonly">
			<span id="nameckmsg" style="color:red; font-size:15"></span></td>
	  		<td><button type="button" id="namebt" class="btn btn-secondary btn-lg" 
	  		onclick="nameupdate()">수정</button></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="m_email" class="form-control" id="inputPassword" 
			value="${vo.m_email }" onkeyup="emailCk()">
			<span id="emailckmsg" style="color:red; font-size:15"></span></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="m_addr" class="form-control" id="inputPassword" 
			value="${vo.m_addr }" onkeyup="addrCk()">
			<span id="addrckmsg" style="color:red; font-size:15"></span></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="m_phone" class="form-control" id="inputPassword" 
			value="${vo.m_phone }" onkeyup="phoneCk()">
			<span id="phoneckmsg" style="color:red; font-size:10"></span></td>
		</tr>
	</table>
	<br>
	<a href="${cp }/main_sh/layoutTest.jsp"><button type="button" id="btn" class="btn btn-secondary btn-lg">취소</button></a>
	<button type="submit" id="findBt" class="btn btn-secondary btn-lg" disabled="disabled">
	  	수정완료</button>
</c:forEach>
</div>
<script>
	var namebool=false;
	var pwdbool=false;
	var emailbool=false;
	var addrbool=false;
	var phonebool=false;
	
	/*이름수정버튼*/
	function nameupdate() {
		var namebt=document.getElementById("namebt");
		var m_name=document.getElementById("m_name").readOnly=false;
		namebt.innerHTML="완료";
		
	}
	
	function nameCk() { 
		var name=document.getElementsByName("m_name")[0].value;
		var nameckmsg=document.getElementById("nameckmsg");
		var nameck= /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
		console.log("이름사이즈"+name.length);
		/*이름에 한글외 문자 입력시 x*/
		if(nameck.test(name)){
			nameckmsg.innerHTML="이름은 한글만 입력해주세요.";
			namebool=false;	
		}else{
			nameckmsg.innerHTML="";
			namebool=true;
		}
		if(name.length>4){  /*이름 4자 이상이면x*/
			nameckmsg.innerHTML="이름은 4자 이하로 입력해주세요.";
			namebool=false;			
		}else{
			nameckmsg.innerHTML="";
			namebool=true;
		}
		bt();
	}
	//주소입력검사
	function addrCk() { 
		var addr=document.getElementsByName("m_addr")[0].value;
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
	/*이메일 중복검사*/
	var xhremail=null;
	function emailCk() {
		var email=document.getElementsByName("m_email")[0].value;
		xhremail=new XMLHttpRequest();
		xhremail.onreadystatechange=emailckOk;
		xhremail.open('post','${cp}/join/emailck.jh',true);
		xhremail.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhremail.send('email='+email);
	}
	
	function emailCk() {
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
	
	function phoneCk() {
		var phone=document.getElementsByName("m_phone")[0].value;
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
	
	var btn = document.getElementById("findBt");
	
	function bt() {
		if(phonebool==true && namebool==true && emailbool==true && addrbool==true){
			btn.disabled=false;
		}else{
			btn.disabled=true;
		}
	}




</script>