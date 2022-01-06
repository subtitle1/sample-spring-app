package com.sample.vo;

public class BookPicture {

	private int bookNo;
	private String picture;
	
	public BookPicture() {}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "BookPicture [bookNo=" + bookNo + ", picture=" + picture + "]";
	}
	
}
