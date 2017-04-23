package com.framgia.users.bean;

/**
 * PublisherInfo.java description infor data insert table Book
 * 
 * @version 22/04/2017
 * @author phan.van.hieu@framgia.com
 */
public class PublisherInfo {
	private String publishersName;
	private String phone;
	private String email;
	private String address;
	
	public PublisherInfo() {
	}

	public String getPublishersName() {
		return publishersName;
	}

	public void setPublishersName(String publishersName) {
		this.publishersName = publishersName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

