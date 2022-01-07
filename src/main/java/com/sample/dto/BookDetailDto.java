package com.sample.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sample.vo.BookPicture;

public class BookDetailDto {

	private int no;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private int discountPrice;
	@JsonFormat(pattern = "yyyy년 M월 d일")
	private Date pubDate;
	private int stock;
	@JsonFormat(pattern = "yyyy년 M월 d일")
	private Date updatedDate;
	@JsonFormat(pattern = "yyyy년 M월 d일")
	private Date createdDate;
	private List<BookPicture> BookPictures;
	
	public BookDetailDto() {}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<BookPicture> getBookPictures() {
		return BookPictures;
	}
	public void setBookPictures(List<BookPicture> bookPictures) {
		BookPictures = bookPictures;
	}
	
	@Override
	public String toString() {
		return "BookDetailDto [no=" + no + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", price=" + price + ", discountPrice=" + discountPrice + ", pubDate=" + pubDate + ", stock=" + stock
				+ ", updatedDate=" + updatedDate + ", createdDate=" + createdDate + ", BookPictures=" + BookPictures
				+ "]";
	}
	
}
