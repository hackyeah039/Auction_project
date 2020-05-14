<%@page import="semi.vo.yh.sellerVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="semi.dao.yh.SellerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function senddata() {
		opener.document.getElementById("account").value = document.getElementById("useAccount").value
	}
</script>
</head>
<body>
<%
	int m_num = Integer.parseInt(request.getParameter("m_num"));
	SellerDao sdao = SellerDao.getInstance();
	ArrayList<sellerVo> list = sdao.listAccount(m_num);
%>
<h2>기존 등록 계좌번호</h2>
<table>
	<tr>
		<td>
			계좌번호
		</td>
		<td>
			<select id ="useAccount">
<%
				if(list != null){
					for(int i = 0; i<list.size() ; i++){
%>
					<option><%=list.get(i).getAccount() %></option>
<%
					}
				} else {
%>
					<option></option>
<%
				}
%>
			</select>
			<input type="button" value="전달" onclick="senddata()">
		</td>
	</tr>
</table>
</body>
</html>