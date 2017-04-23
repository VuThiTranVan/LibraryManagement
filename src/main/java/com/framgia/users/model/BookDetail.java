package com.framgia.users.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BookDetail.java description table BookDetails
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Entity
@Table(name = "BookDetails", catalog = "Library")
public class BookDetail {
	private Integer bookDetailId;
	private Book book;
	private Author author;
	private String deleteFlag;
	private Date dateCreate;
	private String userCreate;
	private Date dateUpdate;
	private String userUpdate;

	public BookDetail() {
	}

	public BookDetail(Integer bookDetailId, Book book, Author author, String deleteFlag, Date dateCreate,
			String userCreate, Date dateUpdate, String userUpdate) {
		super();
		this.bookDetailId = bookDetailId;
		this.book = book;
		this.author = author;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bookDetailId", unique = true, nullable = false)
	public Integer getBookDetailId() {
		return bookDetailId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bookId", nullable = false)
	public Book getBook() {
		return book;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authorsId", nullable = false)
	public Author getAuthor() {
		return author;
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

	public void setBookDetailId(Integer bookDetailId) {
		this.bookDetailId = bookDetailId;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setAuthor(Author author) {
		this.author = author;
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

}
