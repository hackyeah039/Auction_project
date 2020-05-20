<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form action="${cp }/board/userQnAInsert.do" method="post" onsubmit="return clickSubmit()">
<table border="1">
	<tr>
		<th>제목</th>
		<td><input type = "text" name = "bTitle" id="title"></td>
	</tr>	
	<tr>
		<th>작성자</th>
		<td><input type = "text" value ="${id }" readonly="readonly"></td>
	</tr>	
	<tr>
		<th>내용</th>
		<td><textarea cols="100" rows="30" name = "bContent" id = "content"></textarea></td>
	</tr>
	
	
</table>

<input type="submit" value="등록하기">
<input type="button" value="취소하기" onclick="location.href='${cp}/mypage/simplelist.do'">
</form>

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