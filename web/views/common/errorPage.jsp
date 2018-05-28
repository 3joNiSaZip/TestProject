<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service ERROR</title>
<style>
.outer{
		padding: 20px;
		width:600px;
		height:500px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
span {
	font-size:21pt;
	background: white;
	color: navy;
}
</style>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="outer">
		<h1>서비스 에러 발생!!!</h1>
		<h3 style="color:hotpink;">ERROR : <%=msg %></h3>
		<h4><span>서</span>비스 수행 중 에러가 발생했습니다. 전송 값을 확인해 보시고,<br>
		이상이 없을 시 부서 담당자에게 연락하시기 바랍니다.</h4>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>