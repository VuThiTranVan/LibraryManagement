package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.Permissions;

/**
 * ManagementUsersController.java
 * 
 * @version 19/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Repository("permissionDao")
public class PermissionDaoImpl implements PermissionDao {
	
	public static String DEL_FLG = "0";
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Permissions> findByAllPermissions() {
		
		List<Permissions> permission = new ArrayList<Permissions>();
		permission = sessionFactory.getCurrentSession().createQuery("from Permissions where deleteFlag=:delFlg")
				.setParameter("delFlg", DEL_FLG)
				.list();

		return permission;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
