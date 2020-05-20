<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>   

<!-- 아이디찾기 -->
<form method="post" action="${cp }/login/findId.jh">
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">이름 : </label>
    <div class="col-sm-10">
      <input type="text" name="m_id" class="form-control" id="inputPassword" placeholder="Name">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">전화번호 : </label>
    <div class="col-sm-10">
      <input type="text" name="m_phone" class="form-control" id="inputPassword" placeholder="Phone">
    </div>
  </div>
  <button type="submit" class="btn btn-secondary btn-lg">아이디찾기</button>
</form>