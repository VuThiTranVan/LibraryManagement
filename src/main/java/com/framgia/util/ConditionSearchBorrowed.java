package com.framgia.util;

/**
 * ManagementBorrowedBookServiceImpl.java
 * 
 * @version 04/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public class ConditionSearchBorrowed {
	private String txtBorrowedCode;
	private String txtStatus;
	private String txtIntenDateBorrowed;
	private String txtIntenDatePayment;
	private String txtDateBorrowed;
	private String txtDatePayment;

	public ConditionSearchBorrowed(String txtBorrowedCode, String txtStatus, String txtIntenDateBorrowed,
			String txtIntenDatePayment, String txtDateBorrowed, String txtDatePayment) {
		super();
		this.txtBorrowedCode = txtBorrowedCode;
		this.txtStatus = txtStatus;
		this.txtIntenDateBorrowed = txtIntenDateBorrowed;
		this.txtIntenDatePayment = txtIntenDatePayment;
		this.txtDateBorrowed = txtDateBorrowed;
		this.txtDatePayment = txtDatePayment;
	}

	public String getTxtBorrowedCode() {
		return txtBorrowedCode;
	}

	public void setTxtBorrowedCode(String txtBorrowedCode) {
		this.txtBorrowedCode = txtBorrowedCode;
	}

	public String getTxtStatus() {
		return txtStatus;
	}

	public void setTxtStatus(String txtStatus) {
		this.txtStatus = txtStatus;
	}

	public String getTxtIntenDateBorrowed() {
		return txtIntenDateBorrowed;
	}

	public void setTxtIntenDateBorrowed(String txtIntenDateBorrowed) {
		this.txtIntenDateBorrowed = txtIntenDateBorrowed;
	}

	public String getTxtIntenDatePayment() {
		return txtIntenDatePayment;
	}

	public void setTxtIntenDatePayment(String txtIntenDatePayment) {
		this.txtIntenDatePayment = txtIntenDatePayment;
	}

	public String getTxtDateBorrowed() {
		return txtDateBorrowed;
	}

	public void setTxtDateBorrowed(String txtDateBorrowed) {
		this.txtDateBorrowed = txtDateBorrowed;
	}

	public String getTxtDatePayment() {
		return txtDatePayment;
	}

	public void setTxtDatePayment(String txtDatePayment) {
		this.txtDatePayment = txtDatePayment;
	}

}
