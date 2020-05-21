<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>   

<!-- 비밀번호 찾기 -->
<form method="post" onSubmit="findPwd();return false">
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">아이디 : </label>
    <div class="col-sm-10">
      <input type="text" name="m_id" class="form-control" id="inputPassword" placeholder="Id">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">이름 : </label>
    <div class="col-sm-10">
      <input type="text" name="m_name" class="form-control" id="inputPassword" placeholder="Name">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">전화번호 : </label>
    <div class="col-sm-10">
      <input type="text" name="m_phone" class="form-control" id="inputPassword" 
      placeholder="Phone" onkeyup="phoneCk()">
      <br>
    	<span id="phoneckmsg" style="color:red; font-size:15"></span>
    </div>
  </div>
  <div id="btn">
	  <button type="submit" id="findBt" class="btn btn-secondary btn-lg" disabled="disabled">
	  비밀번호 찾기</button>
	  <a href="${cp }/login/login.jsp">
	  <button type="button" class="btn btn-secondary btn-lg">로그인하러가기</button></a>
	</div>
</form>
<span style="color:red" id="noFindMsg"></span>
<span id="okFindMsg"></span>
<script>
	var phonebool=false;
	var xhr=null;
	function findPwd() {
		var m_id=document.getElementsByName("m_id")[0].value;
		var m_phone=document.getElementsByName("m_phone")[0].value;
		var m_name=document.getElementsByName("m_name")[0].value;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=findok;
		xhr.open('post','${cp}/login/findPwd.jh',true);
		xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhr.send('m_id='+m_id+'&m_phone='+m_phone+'&m_name='+m_name);
	}
	function findok() {
		if(xhr.readyState==4 && xhr.status==200){
			var noFindMsg=document.getElementById("noFindMsg");
			var okFindMsg=document.getElementById("okFindMsg");
			var msg=xhr.responseText;
			var json=JSON.parse(msg);
			if(json.msg=='ok'){
				noFindMsg.innerHTML="";
				okFindMsg.innerHTML="회원님의 비밀번호는 ["+json.pwd+"] 입니다.";
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
	function bt() {
		if(phonebool==true){
			findBt.disabled=false;
		}else{
			findBt.disabled=true;
		}
	}
	
</script>