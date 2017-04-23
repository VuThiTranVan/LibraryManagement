package com.framgia.users.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Users.java description table Users
 * 
 * @version 16/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Entity
@Table(name = "Users", catalog = "Library") 
public class Users {
	private Integer userId;
	private String userName;
	private String passWord;
	private String birthDate;
	private String name;
	private String address;
	private String phone;
	private String sex;
	private String email;
	private String deleteFlag;
	private String dateCreate;
	private String userCreate;
	private String dateUpdate;
	private String userUpdate;
	private Permissions permissions;
	
	public Users(){
		
	}
	
	public Users(Integer userId, String userName, String passWord, String birthDate, String name,
			String address, String phone, String sex, String email, String deleteFlag, String dateCreate,
			String userCreate, String dateUpdate, String userUpdate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.birthDate = birthDate;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.sex = sex;
		this.email = email;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
	}	
	
	public Users(Integer userId, String userName, String passWord, String birthDate, String name, String address,
			String phone, String sex, String email, String deleteFlag, String dateCreate, String userCreate,
			String dateUpdate, String userUpdate, Permissions permissions) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.birthDate = birthDate;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.sex = sex;
		this.email = email;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userCreate = userCreate;
		this.dateUpdate = dateUpdate;
		this.userUpdate = userUpdate;
		this.permissions = permissions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "permissionsId", nullable = false)
	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	@Column(name = "userName", nullable = false, length = 30)
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "passWord", nullable = false, length = 60)
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Column(name = "birthDate", nullable = false)
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "address", nullable = true, length = 100)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "phone", nullable = true, length = 11)
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "sex", nullable = false, length = 1)
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "email", nullable = false, length = 30)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "deleteFlag", nullable = false, length = 1)
	public String getDeleteFlag() {
		return deleteFlag;
	}
	
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	@Column(name = "dateCreate", nullable = false)
	public String getDateCreate() {
		return dateCreate;
	}
	
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	@Column(name = "userCreate", nullable = false, length = 30)
	public String getUserCreate() {
		return userCreate;
	}
	
	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}
	
	@Column(name = "dateUpdate", nullable = false)
	public String getDateUpdate() {
		return dateUpdate;
	}
	
	public void setDateUpdate(String dateUpdate) {
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
