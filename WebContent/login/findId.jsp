<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>   


<!-- 아이디찾기 -->
<div id="idmain">
<form method="post" onSubmit="findID();return false">
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">이름 : </label>
    <div class="col-sm-10">
      <input type="text" name="m_name" class="form-control" id="inputPassword" placeholder="Name">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">이메일 : </label>
    <div class="col-sm-10">
      <input type="text" name="m_email" class="form-control" id="inputPassword" 
      placeholder="Phone" onkeyup="emailck()">
      <br>
    	<span id="emailckmsg" style="color:red; font-size:15"></span>
    </div>
  </div>
  <div id="btn">
	  <button type="submit" id="findBt" class="btn btn-secondary btn-lg" disabled="disabled">
	  아이디찾기</button>
	  <a href="${cp }/login/login.jsp">
	  <button type="button" class="btn btn-secondary btn-lg">로그인하러가기</button></a>
	</div>
</form>
</div>
<span style="color:red" id="noFindMsg"></span>
<span id="okFindMsg"></span>
<script>
	var emailbool=false;
	var xhr=null;
	function findID() {
		var m_name=document.getElementsByName("m_name")[0].value;
		var m_email=document.getElementsByName("m_email")[0].value;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=findok;
		xhr.open('post','${cp}/login/findId.jh',true);
		xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhr.send('m_name='+m_name+'&m_email='+m_email);
	}
	function findok() {
		if(xhr.readyState==4 && xhr.status==200){
			var noFindMsg=document.getElementById("noFindMsg");
			var okFindMsg=document.getElementById("okFindMsg");
			var msg=xhr.responseText;
			var json=JSON.parse(msg);
			if(json.msg=='ok'){
				noFindMsg.innerHTML="";
				okFindMsg.innerHTML="회원님의 아이디는 ["+json.id+"] 입니다.";
				noFindMsg.innerHTML="";
			}else if(json.msg=='no'){
				okFindMsg.innerHTML="";
				noFindMsg.innerHTML="일치하는 정보가 없습니다.";
				okFindMsg.innerHTML="";
			}
		}		
	}
	
	/*전화번호 숫자만 입력하도록 하는것*/
	var findBt = document.getElementById("findBt");

	function emailck() {
		var email=document.getElementsByName("m_email")[0].value;
		var emailckmsg=document.getElementById("emailckmsg");
		if(email==''){
			emailckmsg.innerHTML="이메일을 입력해주세요.";
			emailbool=false;
		}else if(email.indexOf('@')==-1){
			emailckmsg.innerHTML="올바르지 않은 이메일 형식입니다.";
			emailbool=false;
		}else{
			emailckmsg.innerHTML="";
			emailbool=true;
		}
			bt();
	}
	function bt() {
		if(emailbool==true){
			findBt.disabled=false;
		}else{
			findBt.disabled=true;
		}
	}
	
</script>