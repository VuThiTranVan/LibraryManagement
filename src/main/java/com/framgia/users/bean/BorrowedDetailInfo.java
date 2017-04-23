package com.framgia.users.bean;

import com.framgia.users.model.Borroweds;

/**
 * BorrowedDetailInfo.java
 * 
 * @version 03/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
public class BorrowedDetailInfo {
	private int borrowedDetailId;
	private BorrowedInfo borrowedInfo;
	private BookInfo bookInfo;
	private String status;
	private String deleteFlag;
	private String dateCreate;
	private String userCreate;
	private String dateUpdate;
	private String userUpdate;

	public BorrowedDetailInfo(int borrowedDetailId, BorrowedInfo borrowedInfo, BookInfo bookInfo, String status,
			String deleteFlag, String dateCreate, String userCreate, String dateUpdate, String userUpdate) {
		super();
		this.borrowedDetailId = borrowedDetailId;
		this.borrowedInfo = borrowedInfo;
		this.bookInfo = bookInfo;
		this.status = status;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
	}

	public BorrowedDetailInfo(int borrowedDetailId, Borroweds borrowed, String status, String deleteFlag,
			String dateCreate, String userCreate, String dateUpdate, String userUpdate) {
		super();
		this.borrowedDetailId = borrowedDetailId;
		this.status = status;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
	}

	public BorrowedDetailInfo() {
	}

	public int getBorrowedDetailId() {
		return borrowedDetailId;
	}

	public void setBorrowedDetailId(int borrowedDetailId) {
		this.borrowedDetailId = borrowedDetailId;
	}

	public BorrowedInfo getBorrowed() {
		return borrowedInfo;
	}

	public void setBorrowed(BorrowedInfo borrowedInfo) {
		this.borrowedInfo = borrowedInfo;
	}

	public BookInfo getBook() {
		return bookInfo;
	}

	public void setBook(BookInfo book) {
		this.bookInfo = book;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
