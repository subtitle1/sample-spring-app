<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title></title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<c:set var="menu" value="도서"></c:set>
<%@include file="../common/nav.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col">
			<h1>책등록 폼</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<p>등록할 책정보를 폼에 입력하세요</p>
			<!-- 
				폼 입력요소에 <input type="file" />인 입력요소(첨부파일 업로드)가 있을 경우,
				반드시 method="post" enctype="multipart/form-data"로 설정한다.
			 -->
			<form class="border bg-light p-3" method="post" action="insert.do" enctype="multipart/form-data">
				<div class="mb-3">
					<label class="form-label">제목</label>
					<input type="text" class="form-control" name="title" />
				</div>
				<div class="mb-3">
					<label class="form-label">저자</label>
					<input type="text" class="form-control" name="author" />
				</div>
				<div class="mb-3">
					<label class="form-label">출판사</label>
					<input type="text" class="form-control" name="publisher" />
				</div>
				<div class="mb-3">
					<label class="form-label">가격</label>
					<input type="number" class="form-control" name="price" min="1000" max="1000000" value="1000"/>
				</div>
				<div class="mb-3">
					<label class="form-label">할인가격</label>
					<input type="number" class="form-control" name="discountPrice" min="1000" max="1000000" value="1000"/>
				</div>
				<div class="mb-3">
					<label class="form-label">출판일</label>
					<input type="date" class="form-control" name="pubDate" />
				</div>
				<div class="mb-3">
					<label class="form-label">입고수량</label>
					<input type="number" class="form-control" name="stock" value="1"/>
				</div>
				<div class="mb-3">
					<label class="form-label">책 사진</label>
					<input type="file" class="form-control" name="upfiles"/>
				</div>
				<div class="mb-3">
					<label class="form-label">책 사진</label>
					<input type="file" class="form-control" name="upfiles"/>
				</div>
				<div class="mb-3">
					<label class="form-label">책 사진</label>
					<input type="file" class="form-control" name="upfiles"/>
				</div>
				<div class="text-end">
					<a href="list.do" class="btn btn-secondary">취소</a>
					<button type="submit" class="btn btn-primary">등록</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>