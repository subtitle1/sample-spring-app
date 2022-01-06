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
<c:set var="menu" value="장바구니" />
<%@ include file="../common/nav.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col">
			<h1>나의 장바구니</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<p>장바구니에 저장된 책 아이템을 확인해보세요</p>
			<table class="table" id="table-cart-items">
				<thead>
					<tr>
						<th style="width: 10%;">순번</th>
						<th style="width: 35%;">제목</th>
						<th style="width: 15%;">출판사</th>
						<th style="width: 15%;" class="text-end pe-2">가격</th>
						<th style="width: 15%;" class="text-end pe-2">할인가격</th>
						<th style="width: 10%;"></th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="">${empty cartItems }
						<tr>
							<td class="text-center" colspan="6">장바구니에 저장된 책 아이템이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="cartItem" items="${cartItems }" varStatus="loop">
							<tr class="text-middle">
								<td>${loop.count }</td>
								<td><a href="../book/detail.do?no=${cartItem.bookNo }" data-book-no="${cartItem.bookNo }">${cartItem.title }</a></td>
								<td>${cartItem.publisher }</td>
								<td class="text-end pe-2"><fmt:formatNumber value="${cartItem.price }" pattern="##,###"></fmt:formatNumber> 원</td>
								<td class="text-end pe-2"><strong class="text-danger"><fmt:formatNumber value="${cartItem.discountPrice }" pattern="##,###"></fmt:formatNumber></strong> 원</td>
								<td class="text-center"><button class="btn btn-outline-danger btn-sm" data-item-no="${cartItem.itemNo }">삭제</button></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="modal fade" id="modal-info-book" tabindex="-1" aria-labelledby="책상세정보 모달" aria-hidden="true">
  		<div class="modal-dialog modal-lg">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="exampleModalLabel">책 상세 정보</h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
					<table class="table">
						<tbody>
							<tr>
								<th style="width: 15%;">등록일</th>
								<td style="width: 35%;"><span id="span-book-created-date">2022년 1월 20일</span></td>
								<th style="width: 15%;">최종 수정일</th>
								<td style="width: 35%;"><span id="span-book-updated-date">2022년 1월 20일</span></td>
							</tr>
							<tr>
								<th>제목</th>
								<td colspan="3"><span id="span-book-title">자바 일주만하면 응수만큼 할 수 있다.</span></td>
							</tr>
							<tr>
								<th>저자</th>
								<td><span id="span-book-author">이응수</span></td>
								<th>출판사</th>
								<td><span id="span-book-publisher">한국출판사</span></td>
							</tr>
							<tr>
								<th>가격</th>
								<td><span id="span-book-price">35,000</span> 원</td>
								<th>할인가격</th>
								<td><span class="fw-bolder text-danger" id="span-book-discount-price">32,000</span> 원</td>
							</tr>
							<tr>
								<th>출판일자</th>
								<td><span id="span-book-pub-date">2021년 12월 14일</span></td>
								<th>도서 재고</th>
								<td><span id="span-book-stock">10</span> 권</td>
							</tr>
						</tbody>
					</table>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	<!-- 오류 메세지 출력 모달창 -->
   <div class="modal fade" id="modal-info-error" tabindex="-1" aria-labelledby="오류 메세지 모달창" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
               <div class="modal-header">
                 <h5 class="modal-title" id="exampleModalLabel">오류 메세지</h5>
                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                  <p class="text-danger"><strong>오류</strong><span id="span-error"></span></p>
               </div>
               <div class="modal-footer">
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
               </div>
            </div>
         </div>
   </div>
</div>
<script type="text/javascript">
	// $(function() { 스크립트 코드 })은 HTML DOM 객체가 준비되면 즉시 코딩된 스크립트가 실행된다. (html dom이 화면에 렌더링 되기 전에 미리 스크립트 작업이 완료된다)
	// 즉, 엘리먼트에 이번트핸들러 함수를 등록하는 것, 특정 엘리먼트를 감추는 것, 서버와 통신해서 초기화면에 필요한 데이터를 획득하는 작업 등 다양한 작업을
	// HTML DOM이 화면에 렌더링되기 전에 수행할 수 있다.
	// 함수 안에서 선언한 모든 변수, 함수, 객체들은 그 함수의 범위를 벗어나지 않는다. 즉, 브라우저 전역객체(window)에 불필요한 변수가 정의되지 않는다.
	// 사용자가 정의한 변수나 함수가 이미 다른 사용자가 정의한 변수나 함수를 덮어쓸 가능성을 없애준다.
	
	// 단점은 html 태그에서 function 안에 선언된 함수를 이용할 수 없다.
	/*
		아래의 코드처럼 사용할 수 없다.
		<button onclick="test();">테스트</button>
		
		$(function() {
			function test() {
				alert("테스트 실행");
			}
		})
		
		아래의 코드는 정상 동작한다.
		<button id="btn-test">테스트</button>
		$(function() {
			$("#btn-test").click(function() {
				alert("테스트 실행");
			})
		})
	*/
	$(function() {
		// 모달객체를 생성한다.
		var bookInfoModal = new bootstrap.Modal(document.getElementById('modal-info-book'), {
		      keyboard: false
		});
		
		var errorInfoModal = new bootstrap.Modal(document.getElementById('modal-info-error'), {
		      keyboard: false
		});
		
		// 장바구니 테이블 안에 있는 a 태그 클릭 시 실행될 이벤트핸들러함수를 등록한다.
		$("#table-cart-items a").click(function(event) {
			event.preventDefault(); // a 태그에서 클릭이벤트가 발생하면 링크된 페이지로 이동하는 기본동작이 일어나지 않게 한다.
			// 지금 이벤트가 발생한 그 엘리먼트의 data-book-no 속성값을 조회한다.
			var bookNo = $(this).attr("data-book-no");
			
			// no라는 이름으로 bookNo를 전달한다
			// 아래의 요청은 "http://localhost/rest/book/detail.do?no=100"와 같은 형태의 요청을 서버로 보낸다.
			// 서버로부터 성공적인 응답이 오면 function(응답데이터) { ... } 함수가 실행된다.
			// jQuery는 서버로부터 성공적인 응답이 오면 응답컨텐츠(json 형식의 텍스트)를 자바스크립트 객체나 배열로 변환한 다음, 함수를 실행할 때 전달한다.
			$.getJSON('/rest/book/detail.do', {no:bookNo}, function(book) {
				console.log(book);
				// 응답으로 받은 책 정보를 모달창 테이블의 각 칸에 표시한다.
				$("#span-book-created-date").text(book.createdDate);
				$("#span-book-updated-date").text(book.updatedDate || ''); // null이면 ''가 표시됨
				$("#span-book-title").text(book.title);
				$("#span-book-author").text(book.author);
				$("#span-book-publisher").text(book.author);
				$("#span-book-price").text(book.price.toLocaleString());
				$("#span-book-discountPrice").text(book.discountPrice.toLocaleString());
				$("#span-book-pubDate").text(book.pubDate);
				$("#span-book-stock").text(book.stock.toLocaleString());
				
				// 모달창을 화면에 표시한다.
				bookInfoModal.show();
			});
		});
		
		// 삭제 버튼을 클릭했을 때 실행될 이벤트 핸들러 함수
		$("#table-cart-items .btn-outline-danger").click(function() {
			// $.getJSON() 메소드 내부의 funtion에서는 this가 이벤트를 발생시킨 엘리먼트가 아니다.
			// 따라서 this를 다른 변수에 미리 담아두고, 그 변수명을 $.getJSON() 메소드 내부의 function에서 사용하자.
			var $btn = $(this);
			var itemNo = $(this).attr("data-item-no");
			$.getJSON("/rest/cart/delete.do", {no:itemNo}, function(response) {
				if (response.status == 'OK') {
					$("#span-item-count").text(response.items[0]);
					$btn.closest('tr').remove();
				} else {
					$("#span-error").text(response.error);
					errorInfoModal.show();
				}
			});
		});
	});
</script>
</body>
</html>