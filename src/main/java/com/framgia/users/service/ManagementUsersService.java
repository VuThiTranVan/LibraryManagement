package com.framgia.users.service;

import java.util.List;

import com.framgia.users.model.Permissions;
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
	List<Permissions> findByAllPermissions();

	// Search user with input text search
	List<Users> findByUsersWithCondition(String txtName, String txtPermission);
}
