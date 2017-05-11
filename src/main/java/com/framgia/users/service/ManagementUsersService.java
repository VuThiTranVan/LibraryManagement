package com.framgia.users.service;

import java.util.List;

import com.framgia.users.bean.PermissionInfo;
import com.framgia.users.bean.UserInfo;
import com.framgia.users.model.Users;

/**
 * ManagementUsersService.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public interface ManagementUsersService {

	// Search user with function login
	Users findByUserName(String username);

	// Search Permission with no condition
	List<PermissionInfo> findByDelFlg();

	// Search user with input text search
	List<UserInfo> findByUsersWithCondition(String txtName, int txtPermission);

	// delete logic user
	int delLogicUser(int idUser, String userUpd, String dateUpdate);

	// Search user with idUser
	UserInfo findByIdUser(int idUser);

	// update information user by from
	boolean updateUser(UserInfo uses);
	
	UserInfo updateForgotPassword(String email);
}
