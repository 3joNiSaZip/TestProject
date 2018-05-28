<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.board.model.vo.*, java.util.*"%>
<% Board b = (Board)request.getAttribute("b"); %>
<% 
	ArrayList<Attachment> fileList = (ArrayList<Attachment>)request.getAttribute("fileList");
	Attachment titleImg = fileList.get(0);
	Attachment detailImg1 = fileList.get(1);
	Attachment detailImg2 = fileList.get(2);
	Attachment detailImg3 = fileList.get(3);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 상세보기</title>
<style>
	.outer {
		width:1000px;
		height:650px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	.detail td{
		text-align:center;
		width:1000px;
		border:1px solid white;
	}
	#titleImgArea {
		width:500px;
		height:300px;
		margin-left:auto;
		margin-right:auto;
	}
	#contentArea {
		height:30px;
	}
	.detailImgArea {
		width:250px;
		height:210px;
		margin-left:auto;
		margin-right:auto;
	}
	#titleImg {
		width:500px;
		height:300px;
	}
	.detailImg {
		width:250px;
		height:180px;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="outer">
		<table class="detail" align="center">
			<tr>
				<td width="50px">제목</td>
				<td colspan="5"><label><%= b.getBtitle() %></label></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><label><%= b.getBwriter() %></label></td>
				<td>조회수</td>
				<td><label><%= b.getBcount() %></label></td>
				<td>작성일</td>
				<td><label><%= b.getBdate() %></label></td>
			</tr>
			<tr>
				<td>대표사진</td>
				<td colspan="4">
					<div id="titleImgArea" align="center">
						<img id="titleImg" src="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=titleImg.getChangeName()%>">
					</div>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=titleImg.getChangeName()%>" download><button type="button">다운로드</button></a>
				</td>
			</tr>
			<tr>
				<td>사진메모</td>
				<td colspan="6">
					<p id="contentArea"><%= b.getBcontent() %></p>
				</td>
			</tr>
		</table>
		<table class="detail">
			<tr>
				<td>
					<div class="detailImgArea">
						<img id="detailImg1" class="detailImg" src="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=detailImg1.getChangeName()%>">
						<a href="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=detailImg1.getChangeName()%>" download><button type="button">다운로드</button></a>
					</div>
				</td>
				<td>
					<div class="detailImgArea">
						<img id="detailImg2" class="detailImg" src="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=detailImg2.getChangeName()%>">
						<a href="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=detailImg2.getChangeName()%>" download><button type="button">다운로드</button></a>
					</div>
				</td>
				<td>
					<div class="detailImgArea">
						<img id="detailImg3" class="detailImg" src="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=detailImg3.getChangeName()%>">
						<a href="<%=request.getContextPath()%>/resources/thumbnail_uploadFiles/<%=detailImg3.getChangeName()%>" download><button type="button">다운로드</button></a>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>