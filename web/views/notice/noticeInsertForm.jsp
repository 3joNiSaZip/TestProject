<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<style>
	.outer{
		width:800px;
		height:500px;
		background : black;
		color: white;
		margin-left : auto;
		margin-right : auto;
		margin-top : 50px;
	}
	table{
		border : 1px solid white;
	}
	.tableArea{
		width : 650px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<% if(m !=null && m.getUserId().equals("admin")){ %>
		<div class="outer">
			<br />
			<h2 align="center">공지사항 작성</h2>
			<div class="tableArea">
				<form action="<%=request.getContextPath()%>/insert.no" method="post">
					<table>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title"/></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><%= m.getUserName() %>
							<input type="hidden" name="userId" value="<%= m.getUserId() %>"/>
							</td>
						</tr>
						<tr>
							<td>작성일</td>
							<td><input type="date" name="date" /></td>
						</tr>
						<tr>
							<td>내용</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea name="content" cols="60" rows="15"></textarea>
							</td>
						</tr>
					</table>
					<br />
					<div align="center">
						<button type="submit">등록하기</button>
						<button type="reset">취소하기</button>
					</div>
				</form>
			</div>
		</div>
	<%}else {
		request.setAttribute("msg","잘못된 경로로 접근하셨습니다.");
		request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);
			
	} %>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>