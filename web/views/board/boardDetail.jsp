<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.board.model.vo.*, java.util.*"%>
<% Board b = (Board)request.getAttribute("b");
	ArrayList<BoardComment> clist = (ArrayList<BoardComment>)request.getAttribute("clist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
   td {
      border:1px solid white;
   }

   .tableArea {
      border:1px solid white;
      width:800px;
      height:350px;
      margin-left:auto;
      margin-right:auto;
   }
   #content {
      height:230px;
   }
   .replyArea {
      width:800px;
      /* height:300px; */
      color:white;
      background:black;
      margin-left:auto;
      margin-right:auto;
   }
   
   a{
   	text-decoration : none;
   	color:white;
   }
   
   a:active, a:hover{
   	color:yellow;
   }
   
   a:visited{
   	color : hotpink; 
   }
</style>
<title>게시글 상세 내용</title>
</head>
<body>
   <%@ include file="../common/header.jsp" %>
   <div class="outer">
      <br>
      <h2 align="center">게시글 상세 내용</h2>
      <div class="tableArea">
            <table align="center" width="800px">
               <tr>
                  <td>제목 </td>
                  <td colspan="5"><span><%= b.getBtitle() %></span></td>
               </tr>
               <tr>
                  <td>작성자 </td>
                  <td><span><%= b.getBwriter() %></span></td>
                  <td>작성일</td>
                  <td><span><%= b.getBdate() %></span></td>
                  <td>조회수 </td>
                  <td><span><%= b.getBcount() %></span></td>
               </tr>
               <% if (b.getBoardfile() != null &&  b.getBoardfile() != "") { %>
               <tr>
                  <td>첨부파일 </td>
                  <td colspan="5"><a href="/myWeb/resources/bUploadFiles/<%= b.getBoardfile() %>" download><%= b.getBoardfile() %></a></td>
               </tr>
               <% } %>
               <tr>
                  <td colspan="6">내용 </td>
               </tr>
               <tr>
                  <td colspan="6">
                     <p id="content"><%= b.getBcontent() %>
                  </td>
               </tr>
            </table>
            <br>
      </div>
      <div align="center">
         <button onclick="location.href='<%= request.getContextPath() %>/selectList.bo'">메뉴로 돌아가기</button>
         <% if(m != null && m.getUserName().equals(b.getBwriter())){ %>
         <button onclick="location.href='<%= request.getContextPath() %>/bUpView.bo?num='+<%=b.getBno()%>">수정하기</button>
         <% } %>
      </div>
   </div>
   <div class="replyArea">
         <div id="replySelectArea">
         	<table id="replySelectTable" border="1" width="800px" align="center">
         		<% if(clist != null) {for(BoardComment bco : clist){ %>
         			<tr>
         				<td>작성자</td>
         				<td><%=bco.getCwriter() %></td>
         				<td>작성일</td>
         				<td><%=bco.getCdate() %></td>
         			</tr>
         			<tr class="comment">
         				<td colspan="4">
         				<textarea class="reply-content" cols="106" rows="3" reaonly="readonly"><%=bco.getCcontent() %></textarea>
         				</td>
         			</tr>
         			<%if(m.getUserName().equals(bco.getCwriter())){ %>
         			<tr>
         				<td colspan="4" align="center">
         					<input type="hidden" name=cno value="<%=bco.getCno() %>" />
         					<button type="button" class="updateBtn" onclick="updateReply(this);">수정하기</button>
         					<button type="button" class="updateConfirm" onclick="updateConfirm(this);" style="display:none;">수정완료</button> &nbsp; &nbsp;
         					<button type="button" class="deleteBtn" onclick="deleteReply(this);">삭제하기</button>
         				</td>
         			</tr>
         			<% } %>
         		<% } } %>
         	</table>
         </div>
   <!-- yechan... -->
      <div class="replyWriteArea">
      	<form action="<%= request.getContextPath() %>/insertComment.bo" method="post">
      		<input type="hidden" name="writer" value="<%=m.getUserId()%>"/>
      		<input type="hidden" name="bno" value="<%=b.getBno() %>" />
      		
         <table align="center">
            <tr>
               <td>댓글 작성</td>
               <td><textArea rows="3" cols="80" name="replyContent" id="replyContent"></textArea></td>
               <td><button type="submit" id="addReply">댓글 등록</button></td>
            </tr>
         </table>
         
      	</div>

         </form>
      
   </div>
   <script>
   	function updateReply(obj){
		// 현재 위치와 가장 근접한 textarea 접근하기
   		$(obj).parent().parent().prev().find('textarea').removeAttr('readonly');
		
		// 수정 완료 버튼을 화면 보이게 하기
		$(obj).siblings('.updateConfirm').css('display','inline');
		
		// 수정하기 버튼 숨기기
		$(obj).css('display','none');
   	}
   	function updateConfirm(obj){
   		//댓글의 내용 가져오기
   		var content = $(obj).parent().parent().prev().find('textarea').val();
   		
   		//댓글의 번호 가져오기
   		var cno = $(obj).siblings('input').val();
   		
   		//게시글 번호 가져오기
   		var bno = '<%=b.getBno() %>';
   		
   		location.href ="<%= request.getContextPath() %>/updateComment.bo?cno="+cno+"&bno="+bno+"&content="+content;
   	}
   	function deleteReply(obj){
   		
   	//댓글의 번호 가져오기
   		var cno = $(obj).siblings('input').val();
   		
   		//게시글 번호 가져오기
   		var bno = '<%=b.getBno() %>';
   		
   		location.href="<%= request.getContextPath() %>/deleteComment.bo?cno="+cno+"&bno="+bno;
   		
   	}
   </script>
   <%@ include file="../common/footer.jsp" %>
</body>
</html>