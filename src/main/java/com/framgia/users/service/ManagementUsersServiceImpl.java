package com.framgia.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.framgia.users.bean.PermissionInfo;
import com.framgia.users.bean.UserInfo;
import com.framgia.users.dao.PermissionDao;
import com.framgia.users.dao.UserDao;
import com.framgia.users.model.Permissions;
import com.framgia.users.model.Users;
import com.framgia.util.Constant;
import com.framgia.util.ConvertDataModelAndBean;
import com.framgia.util.DateUtil;

/**
 * ManagementUsersServiceImpl.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@Service("managementUsersService")
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
	public List<UserInfo> findByUsersWithCondition(String txtName, int txtPermission) {
		// TODO Auto-generated method stub
		List<Users> userList = userDao.findByConditon(txtName, txtPermission);

		List<UserInfo> userInfo = new ArrayList<UserInfo>();

		for (Users item : userList) {

			UserInfo user = new UserInfo();

			user = ConvertDataModelAndBean.converUserModelToBean(item);

			userInfo.add(user);

		}

		return userInfo;
	}

	@Override
	public List<PermissionInfo> findByDelFlg() {

		// get value permission role for select box
		List<PermissionInfo> permissionInfo = new ArrayList<PermissionInfo>();

		List<Permissions> permissionList = permissionDao.findByDelFlg();

		for (Permissions item : permissionList) {

			PermissionInfo per = new PermissionInfo();

			per.setPermissionsId(item.getPermissionsId());
			per.setPermissionName(item.getPermissionName());

			permissionInfo.add(per);
		}

		return permissionInfo;
	}

	@Transactional
	@Override
	public int delLogicUser(int idUser, String userUpd, String dateUpdate) {
		int result = 0;
		if (userDao.delLogicUser(idUser, userUpd, DateUtil.convertStringtoDateTime(dateUpdate))) {
			result = 1;
			TransactionAspectSupport.currentTransactionStatus().isCompleted();

		} else {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
		}

		return result;
	}

	@Override
	public UserInfo findByIdUser(int idUser) {

		UserInfo user = new UserInfo();

		Users userModel = userDao.findById(idUser);

		if (null != userModel && userModel.getUserId() != null) {
			user = ConvertDataModelAndBean.converUserModelToBean(userModel);
		}

		// TODO Auto-generated method stub
		return user;
	}

	@Transactional
	@Override
	public boolean updateUser(UserInfo user) {

		Users userModel = new Users();
		userModel = ConvertDataModelAndBean.converUserBeanToModel(user);

		boolean flagUpd = userDao.updateUser(userModel);

		if (flagUpd) {
			TransactionAspectSupport.currentTransactionStatus().isCompleted();
		} else {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
		}

		return flagUpd;
	}

	@Transactional
	@Override
	public UserInfo updateForgotPassword(String email) {

		String token = UUID.randomUUID().toString();

		String passwordConvert = passwordEncoderToString(token);

		Users user = new Users();

		user.setPassWord(passwordConvert);
		user.setEmail(email);
		user.setUserUpdate(Constant.USER_UPDATE_DEFAULT);

		user = userDao.updatePassword(user);

		if (null != user) {
			TransactionAspectSupport.currentTransactionStatus().isCompleted();

			return ConvertDataModelAndBean.converUserModelToBean(user);
		} else {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();

			return null;
		}
	}

	// encode password
	public String passwordEncoderToString(String token) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(token);
		return hashedPassword;
	}
}
