package com.framgia.users.dao;

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

	// Search user with function login
	Users findByUserName(String username);

	// Search user with no condition
	List<Users> findByAllUser();
	
	// Search user with input text search
	List<Users> findByUsersWithCondition(String txtName, String txtPermission);
	
	Users findByIdUser(String idUser);
	// add user by from
	int delLogicUser(String idUser, String userUpd);
	
	// update information user by from
	int updateUser(Users uses);

}
