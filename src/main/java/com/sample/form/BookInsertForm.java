package com.sample.form;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class BookInsertForm {

	private String title;
	private String author;
	private String publisher;
	private int price;
	private int discountPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pubDate;
	private int stock;
	// 2개 이상의 첨부파일이 같은 이름으로 업로드 될 때는 배열 혹은 컬렉션 타입의 변수를 선언한다.
	// MultipartFile 객체는 업로드된 첨부파일의 정보를 제공하는 객체다.
	// MultipartFile 객체는 첨부파일의 유무, 파일명, 파일 사이즈, 파일의 컨텐츠 타입, 파일 데이터, 파일과 연결된 스트림을 제공한다.
	private List<MultipartFile> upfiles;
	
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

	
	public List<MultipartFile> getUpfiles() {
		return upfiles;
	}

	public void setUpfiles(List<MultipartFile> upfiles) {
		this.upfiles = upfiles;
	}

	@Override
	public String toString() {
		return "BookInsertForm [title=" + title + ", author=" + author + ", publisher=" + publisher + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", pubDate=" + pubDate + ", stock=" + stock + ", upfiles="
				+ upfiles + "]";
	}
}
