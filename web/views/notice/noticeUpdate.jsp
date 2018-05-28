<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.notice.model.vo.*"%>
    <%
    	Notice n = (Notice)request.getAttribute("n");
   
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정 페이지</title>
<style>
	.outer{
		width : 800px;
		height:500px;
		background : black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table{
		width:650px;
		padding:20px;
		color:black;
		border:1px solid white;
	}
	.tableArea{
		background : white;
		width:650px;
		height:350px;
		margin : auto;
	}
</style>
</head>
<body>
<%@ include file="../common/header.jsp"  %>
<% if(m != null && m.getUserId().equals("admin")){ %>
<!-- 관리자가 접근 시 -->
<div class="outer">
	<br />
	<h2 align="center">공지사항 수정 페이지</h2>
	<div class="tableArea">
		<form id="updateForm" method="post">
			<table>
				<tr>
					<td>제목</td>
					<td colspan="3">
						<input type="text" name="title" size="50" value=<%= n.getNtitle() %>/>
						<input type="hidden" name="nno" value="<%= n.getNno() %>"/>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" value=<%=n.getNwriter()%> readonly/></td>
					<td>작성일</td>
					<td><input type="date" name="date" value=<%=n.getNdate() %> readonly/></td>
				</tr>
				<tr>
					<td>내용</td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="content" cols="60" rows="15" style="resize:none;"><%=n.getNcontent()%></textarea>
					</td>				
				</tr>
			</table>
			<br />
			<div align="center">
				<button onclick="complete();">수정완료</button>
				<button onclick="deleteNotice();">글 삭제</button>
			</div>
			<script>
				function complete(){
					$('#updateForm').attr(
							"action","<%=request.getContextPath()%>/update.no");
				}
				
				function deleteNotice(){
					$('#updateForm').attr(
							"action","<%=request.getContextPath()%>/deleteNotice.no");
				}
			</script>
			
		</form>
	</div>
</div>
<%}else{ 
/* 관리자가 아닌 사용자 접근 시 */
request.setAttribute("msg", "잘못된 접근 입니다.");
request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);

 } %>
<%@ include file="../common/footer.jsp"  %>
</body>
</html>






