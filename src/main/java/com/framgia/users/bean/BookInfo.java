package com.framgia.users.bean;

/**
 * BookInfo.java description infor data insert table Book
 * 
 * @version 22/04/2017
 * @author phan.van.hieu@framgia.com
 */
public class BookInfo {

	private int bookId;
	private String categoriesName;
	private String publishersName;
	private String bookCode;
	private int categoriesId;
	private int publishersId;
	private String name;
	private Float price;
	private String statusBook;
	private int numberBook;
	private int numberBorrowed;
	private int numberRest;
	private int numberPage;
	private String deleteFlag;
	private String dateCreate;
	private String userCreate;
	private String dateUpdate;
	private String userUpdate;

	public BookInfo() {
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public int getCategoriesId() {
		return categoriesId;
	}

	public void setCategoriesId(int categoriesId) {
		this.categoriesId = categoriesId;
	}

	public int getPublishersId() {
		return publishersId;
	}

	public void setPublishersId(int publishersId) {
		this.publishersId = publishersId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getStatusBook() {
		return statusBook;
	}

	public void setStatusBook(String statusBook) {
		this.statusBook = statusBook;
	}

	public int getNumberBook() {
		return numberBook;
	}

	public void setNumberBook(int numberBook) {
		this.numberBook = numberBook;
	}

	public int getNumberPage() {
		return numberPage;
	}

	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getCategoriesName() {
		return categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}

	public String getPublishersName() {
		return publishersName;
	}

	public void setPublishersName(String publishersName) {
		this.publishersName = publishersName;
	}

	public int getNumberBorrowed() {
		return numberBorrowed;
	}

	public void setNumberBorrowed(int numberBorrowed) {
		this.numberBorrowed = numberBorrowed;
	}

	public int getNumberRest() {
		return numberRest;
	}

	public void setNumberRest(int numberRest) {
		this.numberRest = numberRest;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public String getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

}
