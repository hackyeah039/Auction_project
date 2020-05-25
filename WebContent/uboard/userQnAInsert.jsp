<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="card" style="width: 50rem; margin : auto; margin-top : 50px">
	<div class = "card-body">
	<h3 class = "card-title">1:1 문의게시판</h3>	
	<form action="${cp }/board/userQnAInsert.do" method="post" onsubmit="return clickSubmit()">
		<div class="form-group">
	    	<label for="title">제목</label>
	   	 	<input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요" name = "bTitle">
	  	</div>	
		 <div class="form-group row">
			 <label for="staticEmail" class="col-sm-2 col-form-label">작성자</label>
			 <div class="col-sm-10">
		      <input type="text" readonly class="form-control-plaintext" value="${id }">
		     </div>
	     </div>	
		 <div class="form-group">
	    	<label for="content">내용</label>
	    	<textarea class="form-control" id="content" rows="30" placeholder = "내용을 입력해주세요" name = "bContent"></textarea>
	  	</div>
	
	<input type="submit" value="등록하기" class="btn btn-secondary" >
	<input type="button" value="취소하기" class="btn btn-secondary" onclick="location.href='${cp}/mypage/simplelist.do'">
	</form>
	</div>
</div>


<script type="text/javascript">
<!--

//-->
	function clickSubmit() {
		var title = document.getElementById("title").vale
		var content = document.getElementById("content").vale
		if(title == "" || content == ""){
			return false;
		}
		return true;
	}

</script>