package com.sample.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.BookService;
import com.sample.vo.Book;

/*
 * @RestController
 * 		- 요청핸들러 메소드가 반환하는 값을 응답메세지의 body에 포함시킨다.
 * 		- jackson-databind 라이브러리가 프로젝트에 포함되어 있으면 요청핸들러 메소드가 반환하는 값을 json 형식의 텍스트로 변환한 다음
 * 		  응답메세지의 body부에 포함시켜서 클라이언트에게 보낸다.
 * 		- @RestController를 사용하는 대신 아래와 같이 적어도 동일하게 동작한다.
 * 
 				@Controller // @RestController 대신 @Controller를 적었다 -> @ResponseBody를 지정해 주어야 함
 				@RequestMapping("/rest/book")
 				public class BooksRestController {
 					@Autowired
 					private BookService bookService;
 					
 					@GetMapping("detail.do")
 					// @ResponseBody는 응답객체의 body에 담아서 전달하는 방식
 					public @ResponseBody Book detail(int no) {
 						Book book = bookService.getBookDetail(no);
 						return Book;
 					}
 					
 					@GetMapping("detail.do")
 					@ResponseBody
 					public Book detail(int no) {
 						Book book = bookService.getBookDetail(no);
 						return Book;
 					}
 				}
 */
@RestController
@RequestMapping("/rest/book")
public class BookRestController {

	@Autowired
	private BookService bookService;

	@GetMapping("/detail.do")
	public Book detail(int no) {
		Book book = bookService.getBookDetail(no);
		return book;
	}
}
