<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.board.model.vo.*, java.util.*"%>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCont = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
	.outer{
		width:900px;
		height:600px;
		background:black;
		color:white;
		margin-top:50px;
		margin-left:auto;
		margin-right:auto;
	}
	table{
		padding : 20px;
		border : 1px solid white;
		text-align: center;
	}
	.tableArea{
		width: 750px;
		height: 350px;
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
	<%@ include file ="../common/header.jsp" %>
	<div class="outer">
		<br />
		<h2 align="center">게시판 목록</h2>
		<div class="tableArea">
			<table align="center" id="listArea">
				<tr>
					<th width="100px">글번호</th>
					<th width="300px">글제목</th>
					<th width="100px">작성자</th>
					<th width="150px">작성일</th>
					<th width="100px">조회수</th>
					<th width="100px">첨부파일</th>
				</tr>
				<% for(Board b : list){ %>
					<tr>
						<td><%=b.getBno()%></td>
						<input type="hidden" name="bno" value="<%=b.getBno()%>"/>
						<td><%=b.getBtitle() %></td>
						<td><%=b.getBwriter() %></td>
						<td><%=b.getBdate() %></td>
						<td><%=b.getBcount() %></td>
						<td><%= (b.getBoardfile() != null && b.getBoardfile() !="") ? "O" : "X" %></td>
					</tr>
				<% } %>
				
			</table>
		</div>
		
		<!-- 페이징처리할 부분 -->
		<div class="pagingArea" align="center">
			<!-- 가장 첫 페이지로 이동 -->
			<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=1'">[처음]</button>
			
			<!-- 한페이지 씩 앞으로 이동 -->
			<% if(currentPage <= 1){ %>
				<button disabled>&lt;</button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%=currentPage -1%>'">&lt;</button>
			<% }%>
			
			<!-- 각 페이지 별 리스트 작성 -->
			<% for(int i = startPage;i<=endPage;i++){ %>
				<% if(i == currentPage) { %>
					<button disabled><%=i %></button>
				<% } else{ %>
					<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%=i %>'"><%=i %></button>
				<% } %>
			<% } %>
			
			<!-- 한페이지 씩 뒤로 이동 -->
			<% if(currentPage >= maxPage){ %>
				<button disabled>&gt;</button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%=currentPage +1%>'">&gt;</button>
			<% }%>
			
			<!-- 가장 마지막 페이지로 이동 -->
			<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%=maxPage%>'">[마지막]</button>
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
			<% if(m != null ){ %>
				<button onclick="location.href='views/board/boardInsertForm.jsp'">게시글 작성</button>
			<% } %>
		
	</div>
	</div>
	
	
	<%@ include file ="../common/footer.jsp" %>
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
			location.href="<%=request.getContextPath()%>/selectOne.bo?num="+num;
		});
	});
	</script>
</body>
</html>








