<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="javax.websocket.SendResult"%>
<%@page import="org.apache.catalina.ha.backend.Sender"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	
</style>    
    
    
<div id="main">

<span>비밀번호 입력 후 버튼을 눌러주세요.</span><br>
<span>입찰중인 상품이 있는 경우 탈퇴 처리가 불가능 합니다. </span><br>
<span style="color:red">탈퇴 처리 후 취소는 불가합니다.</span>
<br><br><br>
<!-- action="${cp }/myinfo/out.jh" -->
	<form method="post" onsubmit="return pwdCk(event);">
		<input type="password" name="nowpwd" class="form-control" id="inputPassword" 
		placeholder="Password" >
		<span id="nowpwdmsg" style="color:red; font-size:15"></span>
		<input type="hidden" name="m_num" value="${m_num }">
		<br><br><br>
		<button type="submit" id="findBt" class="btn btn-secondary btn-lg">탈퇴하기</button>
	</form>
</div>
<script>
		var nowpwdbool=false;
		var xhrpwd=null;
		function pwdCk(event) {
			event.preventDefault();
			var nowpwd=document.getElementsByName("nowpwd")[0].value;
			xhrpwd=new XMLHttpRequest();
			xhrpwd.onreadystatechange=nowpwdok;
			xhrpwd.open('post','${cp}/myinfo/pwdCheck.jh',true);
			xhrpwd.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			xhrpwd.send('nowpwd='+nowpwd+'&m_num=${m_num}');
		}
		
		function nowpwdok() {
			var nowpwdmsg=document.getElementById("nowpwdmsg");
			var nowpwd=document.getElementsByName("nowpwd")[0].value;
			if(xhrpwd.readyState==4 && xhrpwd.status==200){
				var msg=xhrpwd.responseText;
				var json=JSON.parse(msg);
				if(json.msg=='error'){
					alert("비밀번호가 일치하지 않습니다.");
				}else if(json.msg=='ok'){
					out();
				}
			}		
		}
		
		var xhr=null;
		function out() {
			var m_num=document.getElementsByName("m_num")[0].value;
			xhr=new XMLHttpRequest();
			xhr.onreadystatechange=outok;
			xhr.open('post','${cp }/myinfo/out.jh',true);
			xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			xhr.send('m_num='+m_num);
		}
		function outok() {
			if(xhr.readyState==4 && xhr.status==200){
				var msg=xhr.responseText;
				var json=JSON.parse(msg);
				if(json.msg=='ok'){
					alert("탈퇴요청 처리가 완료되었습니다!");
					/*탈퇴처리 완료 후 메인으로 바로 페이지 이동*/	
					window.location.replace('${cp}/main_sh/layoutTest.jsp');	
				}else{
					alert("입찰중인 상품이 있을 경우 탈퇴 처리 불가능 합니다!");	
				}
			}		
		}
</script>  