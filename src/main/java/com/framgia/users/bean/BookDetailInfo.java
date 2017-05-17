package com.framgia.users.bean;

public class BookDetailInfo {
	private int bookDetailId;
	private AuthorInfo author;
	private BookInfo book;

	public BookDetailInfo(int bookDetailId, AuthorInfo author, BookInfo book) {
		super();
		this.bookDetailId = bookDetailId;
		this.author = author;
		this.book = book;
	}

	
	public BookInfo getBook() {
		return book;
	}


	public void setBook(BookInfo book) {
		this.book = book;
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
