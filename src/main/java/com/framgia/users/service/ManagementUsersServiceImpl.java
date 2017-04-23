package com.framgia.users.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.framgia.users.bean.PermissionInfo;
import com.framgia.users.bean.UserInfo;
import com.framgia.users.dao.PermissionDao;
import com.framgia.users.dao.UserDao;
import com.framgia.users.model.Permissions;
import com.framgia.users.model.Users;
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
	public UserInfo updateUser(UserInfo user) throws ParseException {
		// TODO Auto-generated method stub
		UserInfo userReturn = new UserInfo();

		Users userModel = new Users();
		userModel = ConvertDataModelAndBean.converUserBeanToModel(user);

		Users userUpd = userDao.updateUser(userModel);

		if (null != userUpd && userUpd.getUserId() != null) {

			TransactionAspectSupport.currentTransactionStatus().isCompleted();

			userReturn = ConvertDataModelAndBean.converUserModelToBean(userUpd);

			userReturn.setDateUpdate(DateUtil.convertDateTimetoString(userUpd.getDateUpdate()));
		} else {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
		}

		return userReturn;
	}
}
