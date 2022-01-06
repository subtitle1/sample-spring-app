package com.sample.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.form.BookInsertForm;
import com.sample.form.Criteria;
import com.sample.pagination.Pagination;
import com.sample.service.BookService;
import com.sample.vo.Book;
import com.sample.vo.BookPicture;

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
	public String save(BookInsertForm form) throws IOException {
		String saveDirectory = "D:\\중앙HTA\\spring-workspace\\spring-mybatis\\src\\main\\webapp\\resources\\images";
		
		logger.debug("입력폼 정보: " + form);
		
		List<BookPicture> bookPictures = new ArrayList<>();
		
		List<MultipartFile> upfiles = form.getUpfiles();
		for (MultipartFile multipartFile : upfiles) {
			// MultipartFile의 isEmpty() 메소드는 해당 객체에 첨부파일 정보가 없으면 true를 반환한다.
			if (!multipartFile.isEmpty()) {		
				// Multipartfile 객체에서 업로드된 첨부파일의 이름을 조회한다.
				String filename = multipartFile.getOriginalFilename();
				// 책의 사진정보를 저장하는 BookPicture 객체를 생성하고, 첨부파일 이름을 저장한다.
				BookPicture bookPicture = new BookPicture();
				bookPicture.setPicture(filename);
				
				bookPictures.add(bookPicture);
				
				// 업로드된 첨부파일을 프로젝트의 images 폴더에 저장하기
				// MultipartFile 객체는 임시 디렉토리에 임시파일 상태로 저장된 첨부파일을 읽어오는 스트립을 제공한다.
				InputStream in = multipartFile.getInputStream();
				
				// 지정된 폴더에 첨부파일명으로 파일을 출력하는 스트립 생성하기
				FileOutputStream out = new FileOutputStream(new File(saveDirectory, filename));

				// DB에는 파일이름만 저장됨
				FileCopyUtils.copy(in, out);
			}
		}
		
		Book book = new Book();
		// BookInsertForm 객체의 멤버변수에 저장된 값을 Book 객체의 멤버변수에 복사한다.
		// 멤버변수의 타입과 멤버변수의 이름이 일치하는 값이 복사되며, 이름은 같은데 타입이 서로 다르면 예외가 발생한다.
		BeanUtils.copyProperties(form, book);
		// 책 정보와 책 사진 정보를 서비스 메소드에 전달해서 저장시킨다.
		bookService.addNewBook(book, bookPictures);		
		
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








