package com.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sample.form.Criteria;
import com.sample.vo.Book;
import com.sample.vo.BookPicture;

@Mapper
public interface BookDao {

	void insertBook(Book book);
	void updateBook(Book book);
	int getBooksTotalRows(Criteria criteria);
	List<Book> searchBooks(Criteria criteria);
	List<Book> getAllBooks();
	List<Book> searchBooksByTitle(String title);
	List<Book> searchBooksByPrice(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);
	Book getBookByNo(int no);
	
	void insertBookPicture(BookPicture bookPicture);
	List<BookPicture> getBookPicturesByBookNo(int bookNo);
}
