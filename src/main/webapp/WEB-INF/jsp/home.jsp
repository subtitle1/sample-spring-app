<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title></title>
  	<meta charset="utf-8">
  	<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<link rel="stylesheet" href="/resources/css/s2.css" />
</head>
<body>
<!-- 
	jsp의 pageContext 객체에 속성을 저장한다.
	pageContext.setAttribute("메뉴", "홈");
 -->
<c:set var="menu" value="홈"></c:set>
<%@include file="common/nav.jsp"%>
<div class="container">
	<div class="p-5 mb-4 bg-light rounded-3">
		<div class="container-fluid py-5">
        	<h1 class="display-5 fw-bold">스프링 샘플 애플리케이션</h1>
        	<p class="col fs-4">책정보 조회, 수정, 삭제, 등록 기능을 제공하는 애플리케이션입니다.</p>
        	<a href="book/list.do" class="btn btn-primary btn-lg">도서 목록</a>
      	</div>
	</div>
	
</div>
	
<div class="star-rating">
	<input type="radio" id="5-stars" name="rating" value="5" />
	<label for="5-stars" class="star">&bigstar;</label>
	<input type="radio" id="4-stars" name="rating" value="4" />
	<label for="4-stars" class="star">&bigstar;</label>
	<input type="radio" id="3-stars" name="rating" value="3" />
	<label for="3-stars" class="star">&bigstar;</label>
	<input type="radio" id="2-stars" name="rating" value="2" />
	<label for="2-stars" class="star">&bigstar;</label> 
	<input type="radio" id="1-star" name="rating" value="1" />
	<label for="1-star" class="star">&bigstar;</label>
</div>

</body>
</html>