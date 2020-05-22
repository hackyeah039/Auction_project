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
<!-- action="${cp }/myinfo/update.jh" -->
<form method="post" onsubmit="return clicksubmit();">
	<table border="1" width="500">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="m_id" class="form-control" id="inputPassword" 
			value="${vo.m_id }" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="m_name" class="form-control" id="inputPassword" 
			value="${vo.m_name }" >
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="m_addr" class="form-control" id="inputPassword" 
			value="${vo.m_addr }" >
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="m_phone" class="form-control" id="inputPassword" 
			value="${vo.m_phone }" >
		</tr>
	</table>
	<br>
	<button type="submit" id="findBt" class="btn btn-secondary btn-lg">
	  	수정완료</button>
</form>
</c:forEach>
</div>
<script>

	function nameCk() { 
		var name=document.getElementsByName("m_name")[0].value;
		var nameckmsg=document.getElementById("nameckmsg");
		var nameck= /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
		/*이름에 한글외 문자 입력시 x*/
		if(nameck.test(name)){
			alert("이름은 한글만 입력해주세요.");
			return false;
		}else if(name.length>4){
			alert("이름은 4자 이하로 입력해주세요.");
			return false;
		}else{
			return true;
		}
		
	}
	//주소입력검사
	function addrCk() { 
		var addr=document.getElementsByName("m_addr")[0].value;
		var addrckmsg=document.getElementById("addrckmsg");
		if(addr.length<1){  
			alert("주소를 입력해주세요.");
			return false;	
		}else{
			return true;
		}
	}
	
	function phoneCk() {
		var phone=document.getElementsByName("m_phone")[0].value;
		var phoneckmsg=document.getElementById("phoneckmsg");
		var phoneck=/^[0-9]+$/; 
		if(phone.length>13){
			alert("번호는 13자리 이하로 입력해주세요.");
			return false;
		}else if(phone.length<1){
			alert("번호를 입력해주세요");
			return false;
		}else if(!(phoneck.test(phone))){
			alert("번호는 숫자만 입력해주세요.");
			return false;
		}else{
			return true;
		}
	}
	
	
	var xhr=null;
	function  clicksubmit() {
		if(!nameCk()){
			return false;			
		}else if(!addrCk()){
			return false;	
		}else if(!phoneCk()){
			return false;	
		}else{
			var m_id=document.getElementsByName("m_id")[0].value;
			var m_name=document.getElementsByName("m_name")[0].value;
			var m_addr=document.getElementsByName("m_addr")[0].value;
			var m_phone=document.getElementsByName("m_phone")[0].value;
			xhr=new XMLHttpRequest();
			xhr.onreadystatechange=submitok;
			xhr.open('post','${cp }/myinfo/update.jh',true);
			xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			xhr.send('m_id='+m_id+'&m_name='+m_name+'&m_addr='+m_addr+'&m_phone='+m_phone);
		}
	}
	function submitok() {
		if(xhr.readyState==4 && xhr.status==200){
			var msg=xhr.responseText;
			var json=JSON.parse(msg);
			if(json.msg=='ok'){
				alert("정보 수정이 완료되었습니다.");
			}else{
				alert("정보 수정이 실패되었습니다.");	
			}
		}		
	}
	
	
	
	
	

</script>