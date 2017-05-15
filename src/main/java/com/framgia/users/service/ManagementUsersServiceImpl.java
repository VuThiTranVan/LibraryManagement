package com.framgia.users.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	// log
	private static final Logger logger = Logger.getLogger(ManagementUsersServiceImpl.class);


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
		try {
			if (userDao.delLogicUser(idUser, userUpd, DateUtil.convertStringtoDateTime(dateUpdate))) {
				result = 1;
			}
		} catch (ParseException e) {
			logger.error("Error delete user: ", e);
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

		try {

			Users userModel = new Users();
			userModel = ConvertDataModelAndBean.converUserBeanToModel(user);

			return userDao.updateUser(userModel);

		} catch (ParseException e) {

			logger.error("Error update user: ", e);
			return false;
		}
	}

	@Transactional
	@Override
	public UserInfo updateForgotPassword(String email) {

		try {
			String token = UUID.randomUUID().toString();

			String passwordConvert = passwordEncoderToString(token);

			Users user = new Users();

			user.setPassWord(passwordConvert);
			user.setEmail(email);
			user.setTokenResetPassword(token);
			user.setUserUpdate(Constant.USER_UPDATE_DEFAULT);

			user = userDao.updatePassword(user);
			if (null != user) {
				return ConvertDataModelAndBean.converUserModelToBean(user);
			}
		} catch (ParseException e) {
			logger.error("Error reset pass to email: ", e);
			return null;
		}
		return null;
	}

	// encode password
	public String passwordEncoderToString(String token) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(token);
		return hashedPassword;
	}

	@Override
	public String findByPassword(int idUser, String password) {
		return userDao.findByPassword(idUser, password);
	}

	@Override
	public String findByToken(int idUser, String token) {
		return userDao.findByToken(idUser, token);
	}

	@Transactional
	@Override
	public boolean updatePassword(int id, String token, String currencePass, String password) {

		try {

			String username;
			if (StringUtils.isNotEmpty(token)) {
				username = findByToken(id, token);
			} else {
				username = findByPassword(id, currencePass);
			}

			if (username != null) {
				Users updUser = new Users();
				updUser.setUserId(id);
				updUser.setUserUpdate(username);
				updUser.setPassWord(passwordEncoderToString(password));
				updUser.setTokenResetPassword(UUID.randomUUID().toString());

				if (userDao.updatePassword(updUser) != null) {
					return true;
				}
			}
		} catch (ParseException e) {
			logger.error("Error reset pass to form: ", e);
			return false;
		}
		return false;
	}
}
