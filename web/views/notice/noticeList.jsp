<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import ="java.util.*, com.kh.jsp.notice.model.vo.*"%>
    
<%
	ArrayList<Notice> list =(ArrayList<Notice>)request.getAttribute("list");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<style>
	.outer{
		width:800px;
		height:500px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table{
		border: 1px solid white;
		text-align:center;
	}
	.tableArea{
		width:650px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
	.searchArea{
		width:650px;
		margin-left:auto;
		margin-right:auto;
	}

	
</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>

<div class="outer">
	<br />
	<h2 align="center">공지 사항</h2>
	<div class="tableArea">
		<table align="center" id="listArea">
			<tr>
				<th>글 번호</th>
				<th width="300px">글 제목</th>
				<th width="100px">작성자</th>
				<th>조회수</th>
				<th width="100px">작성일</th>
			</tr>
			<% for(Notice n: list){ %>
			<tr>
				<td><%=n.getNno() %></td>
				<td><%=n.getNtitle() %></td>
				<td><%=n.getNwriter() %></td>
				<td><%=n.getNcount()%></td>
				<td><%=n.getNdate() %></td>
			</tr>
			<% } %>
		</table>
	</div>
	<div class="searchArea" align="center">
		<select id="searchCondition" name="searchCondition">
			<option value="">---</option>
			<option value="writer">작성자</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>
		<input type="search" id="keyword" placeholder="키워드를 입력하세요"/>
		<button type="button" onclick="search();">검색하기</button>
	<% if(m != null && m.getUserId().equals("admin")){ %>
		<button onclick="location.href='views/notice/noticeInsertForm.jsp'">공지글 작성</button>
	<% } %>
	
	</div>
</div>

<script>
	$(function(){
		$('#listArea td').mouseenter(function(){
			$(this).parent().css({
				"background":"darkgray",
				"cursor" : "pointer"	
			});
		}).mouseout(function(){
			$(this).parent().css({
				"background": "black",
			});
		}).click(function(){
			var num = $(this).parent().children().eq(0).text();
			location.href="<%=request.getContextPath()%>/selectOne.no?num="+num;
		});
	});
	function search(){
		location.href='<%=request.getContextPath()%>/searchNotice.no?con='+$('#searchCondition').val()+'&keyword='+$('#keyword').val();
	}
</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>