package com.framgia.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framgia.users.dao.PermissionDao;
import com.framgia.users.dao.UserDao;
import com.framgia.users.model.Permissions;
import com.framgia.users.model.Users;


/**
 * ManagementUsersServiceImpl.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@Service("managementUsersService")
@Transactional
public class ManagementUsersServiceImpl implements ManagementUsersService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public Users findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}

	@Override
	public List<Users> findByUsersWithCondition(String txtName, String txtPermission) {
		// TODO Auto-generated method stub
		return userDao.findByUsersWithCondition(txtName, txtPermission);
	}

	@Override
	public List<Permissions> findByAllPermissions() {
		// TODO Auto-generated method stub
		return permissionDao.findByAllPermissions();
	}

	@Override
	public Users findByIdUser(String idUser) {
		// TODO Auto-generated method stub
		return userDao.findByIdUser(idUser);
	}

	@Override
	public int delLogicUser(String idUser, String userUpd, String dateUpdate) {
		// TODO Auto-generated method stub
		return userDao.delLogicUser(idUser, userUpd, dateUpdate);
	}

}
