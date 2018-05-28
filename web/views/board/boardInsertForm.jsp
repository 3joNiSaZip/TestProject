<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
   .outer{
      width:900px;
      height:500px;
      background:black;
      color:white;
      margin-left:auto;
      margin-right:auto;
      margin-top:50px;
   }
   table {
      padding: 15px;
      border:1px solid white;
   }

   .tableArea {
      width:500px;
      height:350px;
      margin-left:auto;
      margin-right:auto;
   }
</style>

</head>
<body>
   <%@ include file="../common/header.jsp" %>
   <% if(m != null){ %>
   <div class="outer">
      <br>
      <h2 align="center">게시판 작성</h2>
      <div class="tableArea">
         <form action="<%= request.getContextPath() %>/insert.bo" method="post" enctype="multipart/form-data">
            <table>
               <tr>
                  <td>제목 </td>
                  <td colspan="3"><input type="text" size="51" name="title"></td>
               </tr>
               <tr>
                  <td>작성자 </td>
                  <td colspan="3"><%=m.getUserName() %></td>
               </tr>
               <tr>
                  <td>첨부파일 </td>
                  <td colspan="3">
                     <input type="file" name="file" id="file" />
                  </td>
               </tr>
               <tr>
                  <td>내용 </td>
                  <td colspan="3">
                     <textarea name="content" cols="52" rows="15" style="resize:none;"></textarea>
                  </td>
               </tr>
            </table>
            <br>
            <div align="center">
               <button type="reset">취소하기</button>
               <button type="submit">등록하기</button>
            </div>
            
         </form>
         
      </div>
   </div>
   <% }else{ 
      request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
      request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);
   } %>
   <%@ include file="../common/footer.jsp" %>
</body>
</html>