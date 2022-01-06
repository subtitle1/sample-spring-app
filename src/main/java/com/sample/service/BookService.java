package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.BookDao;
import com.sample.form.Criteria;
import com.sample.vo.Book;
import com.sample.vo.BookPicture;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	public List<Book> getAllBookList() {
		return bookDao.getAllBooks();
	}
	
	public int getTotalRows(Criteria criteria) {
		return bookDao.getBooksTotalRows(criteria);
	}
	
	public List<Book> searchBooks(Criteria criteria) {
		return bookDao.searchBooks(criteria);
	}
	
	public void addNewBook(Book book, List<BookPicture> bookPictures) {
		bookDao.insertBook(book);
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
