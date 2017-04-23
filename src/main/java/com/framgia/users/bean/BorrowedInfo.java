package com.framgia.users.bean;

import java.util.List;

public class BorrowedInfo {

	private int borrowedId;
	private UserInfo userInfo;
	private String borrowedCode;
	private String dIntendBorrowed;
	private String dIntendArrived;
	private String dateBorrrowed;
	private String dateArrived;
	private String status;
	private String deleteFlag;
	private String dateCreate;
	private String userCreate;
	private String dateUpdate;
	private String userUpdate;
	private List<BorrowedDetailInfo> borrowedDetail;

	public BorrowedInfo() {
	}

	public BorrowedInfo(int borrowedId, UserInfo userInfo, String borrowedCode, String dIntendBorrowed, String dIntendArrived,
			String dateBorrrowed, String dateArrived, String status, String deleteFlag, String dateCreate,
			String userCreate, String dateUpdate, String userUpdate, List<BorrowedDetailInfo> borrowedDetail) {
		super();
		this.borrowedId = borrowedId;
		this.userInfo = userInfo;
		this.borrowedCode = borrowedCode;
		this.dIntendBorrowed = dIntendBorrowed;
		this.dIntendArrived = dIntendArrived;
		this.dateBorrrowed = dateBorrrowed;
		this.dateArrived = dateArrived;
		this.status = status;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
		this.borrowedDetail = borrowedDetail;
	}

	public BorrowedInfo(int borrowedId, UserInfo userInfo, String borrowedCode, String dIntendBorrowed, String dIntendArrived,
			String dateBorrrowed, String dateArrived, String status, String deleteFlag, String dateCreate,
			String userCreate, String dateUpdate, String userUpdate) {
		super();
		this.borrowedId = borrowedId;
		this.userInfo = userInfo;
		this.borrowedCode = borrowedCode;
		this.dIntendBorrowed = dIntendBorrowed;
		this.dIntendArrived = dIntendArrived;
		this.dateBorrrowed = dateBorrrowed;
		this.dateArrived = dateArrived;
		this.status = status;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
	}

	public int getBorrowedId() {
		return borrowedId;
	}

	public void setBorrowedId(int borrowedId) {
		this.borrowedId = borrowedId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getBorrowedCode() {
		return borrowedCode;
	}

	public void setBorrowedCode(String borrowedCode) {
		this.borrowedCode = borrowedCode;
	}

	public String getdIntendBorrowed() {
		return dIntendBorrowed;
	}

	public void setdIntendBorrowed(String dIntendBorrowed) {
		this.dIntendBorrowed = dIntendBorrowed;
	}

	public String getdIntendArrived() {
		return dIntendArrived;
	}

	public void setdIntendArrived(String dIntendArrived) {
		this.dIntendArrived = dIntendArrived;
	}

	public String getDateBorrrowed() {
		return dateBorrrowed;
	}

	public void setDateBorrrowed(String dateBorrrowed) {
		this.dateBorrrowed = dateBorrrowed;
	}

	public String getDateArrived() {
		return dateArrived;
	}

	public void setDateArrived(String dateArrived) {
		this.dateArrived = dateArrived;
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

	public List<BorrowedDetailInfo> getBorrowedDetail() {
		return borrowedDetail;
	}

	public void setBorrowedDetail(List<BorrowedDetailInfo> borrowedDetail) {
		this.borrowedDetail = borrowedDetail;
	}

}
