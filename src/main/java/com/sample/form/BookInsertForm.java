package com.sample.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BookInsertForm {

	private String title;
	private String author;
	private String publisher;
	private int price;
	private int discountPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pubDate;
	private int stock;
	
	public BookInsertForm() {}

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

	@Override
	public String toString() {
		return "BookInsertForm [title=" + title + ", author=" + author + ", publisher=" + publisher + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", pubDate=" + pubDate + ", stock=" + stock + "]";
	}
	
}
