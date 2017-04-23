package com.framgia.users.dao;

import java.util.List;

import com.framgia.users.model.Permissions;

/**
 * ManagementUsersController.java
 * 
 * @version 19/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
public interface PermissionDao {
	
	// Search permission
	List<Permissions> findByAllPermissions();
}
