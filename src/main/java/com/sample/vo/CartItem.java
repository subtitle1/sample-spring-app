package com.sample.vo;

public class CartItem {

	private int no;
	private int userNo;
	private int bookNo;
	
	public CartItem() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	@Override
	public String toString() {
		return "CartItem [no=" + no + ", userNo=" + userNo + ", bookNo=" + bookNo + "]";
	}
	
}
