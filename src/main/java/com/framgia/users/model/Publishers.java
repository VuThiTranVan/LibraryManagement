package com.framgia.users.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Categories.java description table Categories
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Entity
@Table(name = "Publishers", catalog = "Library")
public class Publishers {
	private Integer publishersId;
	private String publishersName;
	private String phone;
	private String email;
	private String address;
	private String deleteFlag;
	private Date dateCreate;
	private String userCreate;
	private Date dateUpdate;
	private String userUpdate;
	private Set<Book> book = new HashSet<Book>(0);
	
	public Publishers() {
	}

	

	public Publishers(Integer publishersId, String publishersName, String phone, String email, String address,
			String deleteFlag, Date dateCreate, String userCreate, Date dateUpdate, String userUpdate, Set<Book> book) {
		super();
		this.publishersId = publishersId;
		this.publishersName = publishersName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
		this.book = book;
	}



	@Id
    @Column(name = "publishersId", unique = true, nullable = false)
	public Integer getPublishersId() {
		return publishersId;
	}

	public void setPublishersId(Integer publishersId) {
		this.publishersId = publishersId;
	}

	@Column(name = "publishersName", nullable = false, length = 100)
	public String getPublishersName() {
		return publishersName;
	}

	public void setPublishersName(String publishersName) {
		this.publishersName = publishersName;
	}

	@Column(name = "phone", nullable = false, length = 11)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", nullable = false, length = 30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", nullable = false, length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "deleteFlag", nullable = false, length = 1)
	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreate", nullable = false)
	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Column(name = "userCreate", nullable = false, length = 30)
	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateUpdate", nullable = false)
	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	@Column(name = "userUpdate", nullable = false, length = 30)
	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publishers")
	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}
	
}
