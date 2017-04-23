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
 * Author.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Entity
@Table(name = "Authors", catalog = "Library")
public class Author {
	private Integer authorsId;
	private String authorsName;
	private String sex;
	private String email;
	private String description;
	private String phone;
	private Date birthday;
	private String address;
	private String deleteFlag;
	private Date dateCreate;
	private String userCreate;
	private Date dateUpdate;
	private String userUpdate;
	private Set<BookDetail> bookDetail = new HashSet<BookDetail>(0);
	
	public Author() {
	}

	public Author(Integer authorsId, String authorsName, String sex, String email, String description, String phone,
			Date birthday, String address, String deleteFlag, Date dateCreate, String userCreate, Date dateUpdate,
			String userUpdate, Set<BookDetail> bookDetail) {
		super();
		this.authorsId = authorsId;
		this.authorsName = authorsName;
		this.sex = sex;
		this.email = email;
		this.description = description;
		this.phone = phone;
		this.birthday = birthday;
		this.address = address;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
		this.bookDetail = bookDetail;
	}

	@Id
    @Column(name = "authorsId", unique = true, nullable = false)
	public Integer getAuthorsId() {
		return authorsId;
	}

	@Column(name = "authorsName", nullable = false, length = 100)
	public String getAuthorsName() {
		return authorsName;
	}

	@Column(name = "sex", nullable = false, length = 1)
	public String getSex() {
		return sex;
	}

	@Column(name = "email", nullable = false, length = 30)
	public String getEmail() {
		return email;
	}

	@Column(name = "description", nullable = true, length = 100)
	public String getDescription() {
		return description;
	}

	@Column(name = "phone", nullable = true, length = 11)
	public String getPhone() {
		return phone;
	}

	@Column(name = "birthday", nullable = true)
	public Date getBirthday() {
		return birthday;
	}

	@Column(name = "address", nullable = true, length = 100)
	public String getAddress() {
		return address;
	}
	
	@Column(name = "deleteFlag", nullable = false, length = 1)
	public String getDeleteFlag() {
		return deleteFlag;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreate", nullable = false)
	public Date getDateCreate() {
		return dateCreate;
	}

	@Column(name = "userCreate", nullable = false, length = 30)
	public String getUserCreate() {
		return userCreate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateUpdate", nullable = false)
	public Date getDateUpdate() {
		return dateUpdate;
	}

	@Column(name = "userUpdate", nullable = false, length = 30)
	public String getUserUpdate() {
		return userUpdate;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
	public Set<BookDetail> getBookDetail() {
		return bookDetail;
	}

	public void setAuthorsId(Integer authorsId) {
		this.authorsId = authorsId;
	}

	public void setAuthorsName(String authorsName) {
		this.authorsName = authorsName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public void setBookDetail(Set<BookDetail> bookDetail) {
		this.bookDetail = bookDetail;
	}
	
}
