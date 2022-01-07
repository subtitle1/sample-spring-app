package com.sample.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.BookDao;
import com.sample.dto.BookDetailDto;
import com.sample.form.Criteria;
import com.sample.vo.Book;
import com.sample.vo.BookPicture;

@Service
public class BookService {
	
	static final Logger logger = LogManager.getLogger(BookService.class);
	
	@Autowired
	private BookDao bookDao;
	
	public List<Book> getAllBookList() {
		return bookDao.getAllBooks();
	}
	
	public int getTotalRows(Criteria criteria) {
		logger.info("검색조건: " + criteria);
		return bookDao.getBooksTotalRows(criteria);
	}
	
	public List<Book> searchBooks(Criteria criteria) {
		logger.info("검색조건: " + criteria);
		return bookDao.searchBooks(criteria);
	}
	
	public void addNewBook(Book book, List<BookPicture> bookPictures) {
		logger.info("book 정보 insert 실행 전: " + book);
		bookDao.insertBook(book);
		logger.info("book 정보 insert 실행 후: " + book);
		
		for (BookPicture picture : bookPictures) {
			picture.setBookNo(book.getNo());
			bookDao.insertBookPicture(picture);
		}
	}
	
	public BookDetailDto getBookDetailwithPicture(int bookNo) {
		BookDetailDto dto = new BookDetailDto();
		
		Book book = bookDao.getBookByNo(bookNo);
		BeanUtils.copyProperties(book, dto);
		
		List<BookPicture> pictures = bookDao.getBookPicturesByBookNo(bookNo);
		dto.setBookPictures(pictures);
		
		return dto;
	}
	
	public Book getBookDetail(int bookNo) {
		return bookDao.getBookByNo(bookNo);
	}
	
	public void updateBookPrice(int bookNo, int price, int discountPrice) {
		Book book = bookDao.getBookByNo(bookNo);
		book.setPrice(price);
		book.setDiscountPrice(discountPrice);
		
		bookDao.updateBook(book);
	}
	
	public void updateBookStock(int bookNo, int amount) {
		Book book = bookDao.getBookByNo(bookNo);
		book.setStock(book.getStock() + amount);
		
		bookDao.updateBook(book);
	}
}
