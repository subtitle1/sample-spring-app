<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark mb-3">
	<div class="container">
    	<ul class="navbar-nav me-auto mb-2">
      		<li class="nav-item ${menu eq '홈' ? 'bg-danger ' : '' }">
        		<a class="nav-link ${menu eq '홈' ? 'active ' : '' }" href="/home.do">홈</a>
      		</li>
      		<li class="nav-item ${menu eq '도서' ? 'bg-danger ' : '' }">
        		<a class="nav-link ${menu eq '도서' ? 'active' : '' }" href="/book/list.do">도서 조회</a>
      		</li>
      		<c:if test="${not empty LOGIN_USER }">
               <li class="nav-item ${menu eq '장바구니' ? 'bg-danger ' : '' }">
                 <a class="nav-link position-relative ${menu eq '장바구니' ? 'active' : '' }" href="/cart/list.do">
                    장바구니
                    <span id="span-item-count" class="position-absolute top-10 start-100 translate-middle badge rounded-pill bg-warning">
                      
                    </span>  
                 </a>
               </li>
            </c:if>
      	</ul>
      	<ul class="navbar-nav">
      		<c:if test="${empty LOGIN_USER }">
	      		<li class="nav-item ${menu eq '로그인' ? 'bg-danger ' : '' }">
	        		<a class="nav-link ${menu eq '로그인' ? 'active' : '' }" href="/login.do">로그인</a>
	      		</li>
	      		<li class="nav-item ${menu eq '가입' ? 'bg-danger ' : '' }">
	        		<a class="nav-link ${menu eq '가입' ? 'active' : '' }" href="/register.do">회원가입</a>
	      		</li>
      		</c:if>
      		<c:if test="${not empty LOGIN_USER }">
	      		<li class="nav-item">
	        		<a class="nav-link" href="/logout.do">로그아웃</a>
	      		</li>
      		</c:if>
    	</ul>
  	</div>
</nav>
<script type="text/javascript">
	$(function() {
		// 네브바의 장바구니 메뉴에 현재 장바구니에 저장된 아이템의 개수를 조회해서 표시한다.
		$.getJSON("/rest/cart/counts.do", function(response) {
			/*
				응답데이터: {"status":"OK", "error":null, "items":[8]}
			*/
			$("#span-item-count").text(response.items[0]);
		});
	})
</script>