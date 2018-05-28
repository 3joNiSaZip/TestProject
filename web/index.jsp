<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Web Project</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
	.background {
		width: 100%;
		height: 450px;
	}
	.tile {
		width: 150px;
		height: 150px;
		background: black;
		display: inline-block;
		color: white;
		
	}
	.first-line {
		margin-top: 10%;
	}
	
	.tile1 {
		background: darkgray;
	}
	
	.tile-menu:hover {
		background: skyblue;
		cursor: pointer;
		color: white;
	}
	
	.notile{
		visibility:hidden;
	}
	
	img {
		height: 400px;
		width: 100%;
	}
</style>
</head>
<body>
	<%@ include file="views/common/header.jsp" %>
	
	<div class="background" align="center">
		<div class="first-line">
			<div class="tile tile-menu"></div>
			<div class="tile tile1"></div>
			<div class="tile tile-menu"></div>
			<div class="tile notile"></div>
			<div class="tile tile1"></div>
		</div>
		<div class="second-line">
			<div class="tile notile"></div>
			<div class="tile tile1"></div>
			<div class="tile notile"></div>
			<div class="tile tile1"></div>
			<div class="tile tile-menu"></div>
		</div>
	</div>
	<hr>
	<h2 class="w3-center">Our 4 month's memories</h2>
	<div class="w3-content w3-display-container">
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample5.jpg">
	  <div class="w3-display-bottomright w3-container w3-padding-16 w3-black">
    	앞자리 모범생
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample4.jpg">
	  <div class="w3-display-topleft w3-container w3-padding-16 w3-black">
    	멋쟁이 야간반 이 선생님
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample3.jpg">
	  <div class="w3-display-bottomright w3-container w3-padding-16 w3-black">
    	당신이 없는 강남은 우울 - by 득규
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample2.jpg">
	  <div class="w3-display-topleft w3-container w3-padding-16 w3-black">
    	이 좋은 날 숙제까지..?
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample7.jpg">
	  <div class="w3-display-topright w3-container w3-padding-16 w3-black">
    	OO씨 사진 잘 나왔네요 ㅎㅎ
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample1.jpg">
	  <div class="w3-display-bottomleft w3-container w3-padding-16 w3-black">
    	주말 과제 왜 말했니...
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample6.jpg">
	  <div class="w3-display-bottomright w3-container w3-padding-16 w3-black">
    	이 일은 기억할 것입니다.
  	  </div>
  	</div>
	<button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
	<button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
	<hr>
	</div>
	<br><br><br>
	<h3 align="center">최근 게시글 TOP 5 가 들어갈 예정</h3>
	
	<table id="boardTop5" align="center">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>

	<script>
	$(function(){
		$.ajax({
			url:"boardtop5.do",
			type:"get",
			success : function(data){
				$tableBody = $('#boardTop5 tbody');
				
				$.each(data, function(index, value){
					$tr = $('<tr>');
					$noTd = $('<td>').text(value.bno);
					$titleTd = $('<td>').text(value.btitle);
					$writerTd = $('<td>').text(value.bwriter);
					$dateTd = $('<td>').text(value.bdate);
					$countTd = $('<td>').text(value.bcount);
					
					$tr.append($noTd);
					$tr.append($titleTd);
					$tr.append($writerTd);
					$tr.append($dateTd);
					$tr.append($countTd);
					$tableBody.append($tr);
				});
				
			},error : function(data){
				console.log("TOP 5 불러오기 실패");
			}
		});
	});
	
		var slideIndex = 1;
		showDivs(slideIndex);
		
		function plusDivs(n) {
		  showDivs(slideIndex += n);
		}
		
		function showDivs(n) {
		  var i;
		  var x = document.getElementsByClassName("mySlides");
		  if (n > x.length) {slideIndex = 1}    
		  if (n < 1) {slideIndex = x.length}
		  for (i = 0; i < x.length; i++) {
		     x[i].style.display = "none";  
		  }
		  x[slideIndex-1].style.display = "block";  
		}
	</script>
	<%@ include file="views/common/footer.jsp" %>
</body>
</html>