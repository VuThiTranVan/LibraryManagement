package com.framgia.users.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Users.java description table Users
 * 
 * @version 26/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Entity
@Table(name = "Borroweds", catalog = "Library")
public class Borroweds {

	private Integer borrowedId;
	private Users user;
	private String borrowedCode;
	private Date dIntendBorrowed;
	private Date dIntendArrived;
	private Date dateBorrrowed;
	private Date dateArrived;
	private String status;
	private String deleteFlag;
	private Date dateCreate;
	private String userCreate;
	private Date dateUpdate;
	private String userUpdate;
	private Set<BorrowedDetails> borrowedDetailsList = new HashSet<BorrowedDetails>(0);

	public Borroweds() {
	}

	public Borroweds(Integer borrowedId, Users user, String borrowedCode, Date dIntendBorrowed, Date dIntendArrived,
			Date dateBorrrowed, Date dateArrived, String status, String deleteFlag, Date dateCreate,
			String userCreate, Date dateUpdate, String userUpdate, Set<BorrowedDetails> borrowedDetailsList) {
		super();
		this.borrowedId = borrowedId;
		this.user = user;
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
		this.borrowedDetailsList = borrowedDetailsList;
	}

	public Borroweds(Integer borrowedId, Users user, String borrowedCode, Date dIntendBorrowed, Date dIntendArrived,
			Date dateBorrrowed, Date dateArrived, String status, String deleteFlag, Date dateCreate,
			String userCreate, Date dateUpdate, String userUpdate) {
		super();
		this.borrowedId = borrowedId;
		this.user = user;
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

	@Id
	@Column(name = "borrowedId", unique = true, nullable = false)
	public Integer getBorrowedId() {
		return borrowedId;
	}

	public void setBorrowedId(Integer borrowedId) {
		this.borrowedId = borrowedId;
	}

	@Column(name = "borrowedCode", nullable = false, length = 10)
	public String getBorrowedCode() {
		return borrowedCode;
	}

	public void setBorrowedCode(String borrowedCode) {
		this.borrowedCode = borrowedCode;
	}

	@OneToOne(targetEntity=Users.class,cascade=CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dIntendBorrowed", nullable = false)
	public Date getDIntendBorrowed() {
		return dIntendBorrowed;
	}

	public void setDIntendBorrowed(Date dIntendBorrowed) {
		this.dIntendBorrowed = dIntendBorrowed;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dIntendArrived", nullable = false)
	public Date getDIntendArrived() {
		return dIntendArrived;
	}

	public void setDIntendArrived(Date dIntendArrived) {
		this.dIntendArrived = dIntendArrived;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateBorrrowed", nullable = false)
	public Date getDateBorrrowed() {
		return dateBorrrowed;
	}

	public void setDateBorrrowed(Date dateBorrrowed) {
		this.dateBorrrowed = dateBorrrowed;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateArrived", nullable = false)
	public Date getDateArrived() {
		return dateArrived;
	}

	public void setDateArrived(Date dateArrived) {
		this.dateArrived = dateArrived;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "borroweds")
	public Set<BorrowedDetails> getBorrowedDetails() {
		return borrowedDetailsList;
	}

	public void setBorrowedDetails(Set<BorrowedDetails> borrowedDetailsList) {
		this.borrowedDetailsList = borrowedDetailsList;
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


}
