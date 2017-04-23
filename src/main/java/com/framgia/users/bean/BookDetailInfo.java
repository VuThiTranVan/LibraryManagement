package com.framgia.users.bean;

public class BookDetailInfo {
	private int bookDetailId;
	private AuthorInfo author;

	public BookDetailInfo(int bookDetailId, AuthorInfo author) {
		super();
		this.bookDetailId = bookDetailId;
		this.author = author;
	}

	public int getBookDetailId() {
		return bookDetailId;
	}

	public void setBookDetailId(int bookDetailId) {
		this.bookDetailId = bookDetailId;
	}

	public BookDetailInfo() {
	}

	public AuthorInfo getAuthor() {
		return author;
	}

	public void setAuthor(AuthorInfo author) {
		this.author = author;
	}

}
