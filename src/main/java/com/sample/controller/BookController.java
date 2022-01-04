package com.sample.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.form.BookInsertForm;
import com.sample.form.Criteria;
import com.sample.pagination.Pagination;
import com.sample.service.BookService;
import com.sample.vo.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	
	static final Logger logger = LogManager.getLogger(BookController.class);

	@Autowired
	BookService bookService;
	
	@GetMapping("/insert.do")
	public String form() {
		
		return "book/form.jsp";
	}
	
	@PostMapping("/insert.do")
	public String save(BookInsertForm form) {
		logger.debug("입력폼 정보: " + form);
		
		Book book = new Book();
		BeanUtils.copyProperties(form, book);
		bookService.addNewBook(book);		
		
		return "redirect:list.do";
	}
	
	
	/*
	 * 요청방식 : GET
	 * 요청URL : /book/list.do
	 * 요청파라미터 : opt, value
	 * 이동할 뷰페이지 : /WEB-INF/jsp/book/list.jsp
	 * 뷰페이지에 전달되는 데이터 : List<Book>
	 * 
	 * @RequestParam(name = "요청파라미터 이름", required = 필수 요청파라미터 여부, defaultValue = "기본값"
	 * name: 요청파라미터 이름이다.
	 * required: 필수 요청 파라미터인지 여부, 기본값은 true
	 * defaultValue: 
	 */
	@GetMapping("/list.do")
	public String list(@RequestParam(name="page", required=false, defaultValue="1") String page, Model model, Criteria criteria) {
		
		// 검색 조건에 해당하는 총 데이터 개수 조회
		int totalRecords = bookService.getTotalRows(criteria);
		// 현재 페이지번호와 총 데이터개수 전달해서 페이징 처리에 필요한 정보를 제공하는 객체 생성
		Pagination pagination = new Pagination(page, totalRecords);
		
		// 요청한 페이지에 대한 조회 범위를 criteria에 저장
		criteria.setBeginIndex(pagination.getBegin());
		criteria.setEndIndex(pagination.getEnd());
		logger.info("페이지번호: " + page);
		logger.info("검색 조건: " + criteria);
		
		// 검색 조건(opt, value)과 조회범위(beginIndex, endIndex)가 포함된 Criteria를 서비스에 전달해서 데이터 조회
		List<Book> books = bookService.searchBooks(criteria);
		
		model.addAttribute("books", books);
		model.addAttribute("pagination", pagination);
		
		return "book/list.jsp";
	}
	
	/*
	 * 요청방식 : GET
	 * 요청URL : /book/detail.do
	 * 요청파라미터 : no
	 * 이동할 뷰페이지 : /WEB-INF/jsp/book/detail.jsp
	 * 뷰페이지에 전달되는 데이터 : Book
	 */
	@GetMapping("/detail.do")
	public String detail(int no, Model model) {
		Book book = bookService.getBookDetail(no);
		model.addAttribute("book", book);
		
		return "book/detail.jsp";
	}
	
	/*
	 * 요청방식 : GET
	 * 요청URL : /book/modify.do
	 * 요청파라미터 : no
	 * 이동할 뷰페이지 : /WEB-INF/jsp/book/modifyform.jsp
	 * 뷰페이지에 전달되는 데이터 : Book
	 */
	@GetMapping("/modify.do")
	public String modifyform(int no, Model model) {
		Book book = bookService.getBookDetail(no);
		model.addAttribute("book", book);
		
		return "book/modifyform.jsp";
	}
	
	/*
	 * 요청방식 : POST
	 * 요청URL : /book/updatePrice.do
	 * 요청파라미터 : no, price, discountPrice
	 * 재요청URL : detail.do
	 */
	@PostMapping("/updatePrice.do")
	public String updatePrice(int no, int price, int discountPrice) {
		bookService.updateBookPrice(no, price, discountPrice);
		
		return "redirect:detail.do?no=" + no;
	}
	
	/*
	 * 요청방식 : POST
	 * 요청URL : /book/updateStock.do
	 * 요청파라미터 : no, amount
	 * 재요청URL : detail.do
	 */
	@PostMapping("/updateStock.do") 
	public String updateStock(int no, int amount, RedirectAttributes redirectAttributes){
		Book book = bookService.getBookDetail(no);
		redirectAttributes.addFlashAttribute("boook", book);
		
		bookService.updateBookStock(no, amount);
		
		return "redirect:detail.do?no=" + no;
	}
}








