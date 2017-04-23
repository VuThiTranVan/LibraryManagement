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
@Table(name = "Categories", catalog = "Library")
public class Categories {
	private Integer categoriesId;
	private Integer catSubId;
	private String categoriesCode;
	private String name;
	private String deleteFlag;
	private Date dateCreate;
	private String userCreate;
	private Date dateUpdate;
	private String userUpdate;
	private Set<Book> book = new HashSet<Book>(0);
	
	public Categories() {
	}

	public Categories(Integer categoriesId, Integer catSubId, String categoriesCode, String name, String deleteFlag,
			Date dateCreate, String userCreate, Date dateUpdate, String userUpdate, Set<Book> book) {
		super();
		this.categoriesId = categoriesId;
		this.catSubId = catSubId;
		this.categoriesCode = categoriesCode;
		this.name = name;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
		this.book = book;
	}

	@Id
    @Column(name = "categoriesId", unique = true, nullable = false)
	public Integer getcategoriesId() {
		return categoriesId;
	}

	public void setcategoriesId(Integer categoriesId) {
		this.categoriesId = categoriesId;
	}

    @Column(name = "catSubId", unique = true, nullable = false)
	public Integer getCatSubId() {
		return catSubId;
	}

	public void setCatSubId(Integer catSubId) {
		this.catSubId = catSubId;
	}

	@Column(name = "categoriesCode", nullable = false, length = 10)
	public String getCategoriesCode() {
		return categoriesCode;
	}

	public void setCategoriesCode(String categoriesCode) {
		this.categoriesCode = categoriesCode;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categories")
	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}
	
}
