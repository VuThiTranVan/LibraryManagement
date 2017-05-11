package com.framgia.users.dao;

import java.util.Date;
import java.util.List;

import com.framgia.users.model.Users;

/**
 * UserDao.java
 * 
 * @version 16/04/2017
 * @author phan.van.hieu@framgia.com
 * 
 *         Update: vu.thi.tran.van@framgia.com 17/04/2017
 */
public interface UserDao {

	// Search user with function login _ HieuPV
	Users findByUserName(String username);

	// Search user with input text search
	List<Users> findByConditon(String txtName, int txtPermission);

	// add user by from
	boolean delLogicUser(int idUser, String userUpd, Date dateUpdate);

	// search user find by id by from
	Users findById(int idUser);

	// update information user by from
	boolean updateUser(Users uses);

	// forgot password
	Users updatePassword (Users user);
}
