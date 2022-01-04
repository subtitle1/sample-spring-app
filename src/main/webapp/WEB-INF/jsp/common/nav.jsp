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