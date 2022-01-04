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
			<h1>도서 목록 <a href="insert.do" class="btn btn-outline-primary btn-sm float-end mt-2">도서 등록</a></h1>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="col">
			<form id="form-search-book" class="row row-cols-lg-auto g-3 align-items-center" method="get" action="list.do">
				<input type="hidden" name="page" value="1" />
				<div class="col-12">
					<select class="form-select" name="opt">
						<option value="" selected disabled="disabled">검색조건을 선택하세요</option>
						<option value="제목" ${'제목' eq param.opt ? 'selected' : ''}> 제목으로 검색</option>
						<option value="저자" ${'저자' eq param.opt ? 'selected' : ''}> 저자 검색</option>
						<option value="출판사" ${'출판사' eq param.opt ? 'selected' : ''}> 출판사로 검색</option>
						<option value="최소가격" ${'최소가격' eq param.opt ? 'selected' : ''}> 최소가격으로 검색</option>
						<option value="최대가격" ${'최대가격' eq param.opt ? 'selected' : ''}> 최대가격으로 검색</option>
					</select>
				</div>
				<div class="col-12">
					<input type="text" class="form-control" name="value" value="${param.value }">
				</div>
				<div class="col-12">
					<button type="button" class="btn btn-outline-primary btn-sm" id="btn-search-book">검색</button>
				</div>
			</form>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="col">
			<p>최신 도서 목록을 표시합니다</p>
			<table class="table">
				<thead>
					<tr>
						<th style="width: 10%;">순번</th>
						<th style="width: 45%;">제목</th>
						<th style="width: 15%;">저자</th>
						<th style="width: 15%;" class="text-end pe-3">가격</th>
						<th style="width: 15%;" class="text-end pe-3">할인가격</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty books }">
						<tr>
							<td class="text-center" colspan="5">등록된 도서 정보가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="book" items="${books }" varStatus="loop">
							<tr>
								<td>${loop.count }</td>
								<td><a href="detail.do?no=${book.no}" class="text-none">${book.title }</a></td>
								<td>${book.author }</td>
								<td class="text-end pe-3"><fmt:formatNumber value="${book.price }" pattern="##,###"/> 원</td>
								<td class="text-end pe-3"><strong class="text-danger"><fmt:formatNumber value="${book.discountPrice }" pattern="##,###"/></strong> 원</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	
	<c:if test="${pagination.totalRecords gt 0 }">
		<!-- 페이지 내비게이션 표시 -->
		<div class="row mb-3">
			<div class="col">
				<nav>
		  			<ul class="pagination justify-content-center">
		    			<li class="page-item ${pagination.existPrev ? '' : 'disabled' }">
		      				<a class="page-link" href="list.do?page=${pagination.prevPage }" data-page="${pagination.prevPage }">이전</a>
		    			</li>
	
		    			<c:forEach var="num" begin="${pagination.beginPage }" end="${pagination.endPage }">
			    			<li class="page-item ${pagination.pageNo eq num ? 'active' : '' }">
			    				<a class="page-link" href="list.do?page=${num }" data-page="${num }">${num }</a>
			    			</li>	    			
		    			</c:forEach>
	
		    			<li class="page-item ${pagination.existNext ? '' : 'disabled' }">
		      				<a class="page-link" href="list.do?page=${pagination.nextPage }" data-page="${pagination.nextPage }">다음</a>
		    			</li>
		  			</ul>
				</nav>
			</div>
		</div>
	</c:if>
</div>
<script type="text/javascript">
	/* 폼에서 onsubmit 이벤트가 발생해서 폼이 제출될 때 실행될 이벤트핸들러 함수를 등록한다.
	$("#form-search-book").submit(function() {	// form에서 onsubmit이벤트가 발생했을 때, 반환값에 따라서 form 입력값이 서버로 제출되거나 제출되지 않을 수 있다.
		var opt = $("select[name=opt]").val();
		var value = $.trim($(":input[name=value]").val());
		
		if (opt && value) {
			return true;		// 이벤트핸들러 함수가 true를 반환하면 form의 입력값이 서버로 제출된다.
		}
		
		alert("검색조건 혹은 검색어를 입력하세요");
		return false;			// 이벤트핸들러 함수가 false를 반환하면 form의 입력값이 서버로 제출되지 않는다.
	});
	*/
	
	// 검색버튼을 클릭했을 때 실행될 이벤트핸들러 함수를 등록한다.
	$("#btn-search-book").click(function() {
		// 검색옵션값과 입력값을 조회한다.
		var opt = $("select[name=opt]").val();
		var value = $.trim($(":input[name=value]").val());
		
		// 검색옵션값과 입력값이 모두 존재하면 페이지번호를 1로 설정하고 폼에서 onsubmit 이벤트를 발생시켜서 폼 입력값이 서버로 제출되게 한다.
		if (opt && value) {
			$(":input[name=page]").val("1");
			$("#form-search-book").trigger("submit");
		} else {
			alert("검색조건 혹은 검색어를 입력하세요");					
		}
		
	});
	
	// 페이지내비게이션의 링크를 클릭했을 때 실행될 이벤트핸들러 함수를 등록한다.
	$(".pagination a").click(function(event) {
		event.preventDefault();
		// 클릭한 페이지내비게이션의 페이지번호 조회하기
		var pageNo = $(this).attr("data-page");
		// 검색폼의 히든필드에 클릭한 페이지내비게이션의 페이지번호 설정
		$(":input[name=page]").val(pageNo);
		
		// 검색폼에 onsubmit 이벤트 발생시키기
		$("#form-search-book").trigger("submit");
	})
</script>
</body>
</html>