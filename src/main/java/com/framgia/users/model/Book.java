package com.framgia.users.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Book.java description table Book
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Entity
@Table(name = "Books", catalog = "Library")
public class Book {
	private Integer bookId;
	private String bookCode;
	private Categories categories;
	private Publishers publishers;
	private String name;
	private Float price;
	private String statusBook;
	private Integer numberBook;
	private Integer numberBorrowed;
	private Integer numberRest;
	private Integer numberPage;
	private String deleteFlag;
	private Date dateCreate;
	private String userCreate;
	private Date dateUpdate;
	private String userUpdate;
	private Set<BookDetail> bookDetail = new HashSet<BookDetail>(0);
	
	public Book() {
	}

	public Book(Integer bookId, String bookCode, Categories categories, Publishers publishers, String name, Float price,
			String statusBook, Integer numberBook, Integer numberBorrowed, Integer numberRest, Integer numberPage,
			String deleteFlag, Date dateCreate, String userCreate, Date dateUpdate, String userUpdate,
			Set<BookDetail> bookDetail) {
		super();
		this.bookId = bookId;
		this.bookCode = bookCode;
		this.categories = categories;
		this.publishers = publishers;
		this.name = name;
		this.price = price;
		this.statusBook = statusBook;
		this.numberBook = numberBook;
		this.numberBorrowed = numberBorrowed;
		this.numberRest = numberRest;
		this.numberPage = numberPage;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
		this.bookDetail = bookDetail;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bookId", unique = true, nullable = false)
	public Integer getBookId() {
		return bookId;
	}

	@Column(name = "bookCode", nullable = false, length = 10)
	public String getBookCode() {
		return bookCode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoriesId", nullable = false)
	public Categories getCategories() {
		return categories;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publishersId", nullable = false)
	public Publishers getPublishers() {
		return publishers;
	}

	@Column(name = "name", nullable = true, length = 100)
	public String getName() {
		return name;
	}

	@Column(name = "price", nullable = true)
	public Float getPrice() {
		return price;
	}

	@Column(name = "statusBook", nullable = false, length = 1)
	public String getStatusBook() {
		return statusBook;
	}

	@Column(name = "numberBook", nullable = true)
	public Integer getNumberBook() {
		return numberBook;
	}

	@Column(name = "numberBorrowed", nullable = true)
	public Integer getNumberBorrowed() {
		return numberBorrowed;
	}

	@Column(name = "numberRest", nullable = true)
	public Integer getNumberRest() {
		return numberRest;
	}

	@Column(name = "numberPage", nullable = true)
	public Integer getNumberPage() {
		return numberPage;
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

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public void setPublishers(Publishers publishers) {
		this.publishers = publishers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setStatusBook(String statusBook) {
		this.statusBook = statusBook;
	}

	public void setNumberBook(Integer numberBook) {
		this.numberBook = numberBook;
	}

	public void setNumberBorrowed(Integer numberBorrowed) {
		this.numberBorrowed = numberBorrowed;
	}

	public void setNumberRest(Integer numberRest) {
		this.numberRest = numberRest;
	}

	public void setNumberPage(Integer numberPage) {
		this.numberPage = numberPage;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	public Set<BookDetail> getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(Set<BookDetail> bookDetail) {
		this.bookDetail = bookDetail;
	}

}
