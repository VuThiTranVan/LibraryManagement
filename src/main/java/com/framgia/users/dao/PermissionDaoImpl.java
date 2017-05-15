package com.framgia.users.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.ConstantModel;
import com.framgia.users.model.Permissions;

/**
 * ManagementUsersController.java
 * 
 * @version 19/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends AbstractDao<Integer, Permissions> implements ConstantModel, PermissionDao {

	// log
	private static final Logger logger = Logger.getLogger(PermissionDaoImpl.class);

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Permissions> findByDelFlg() {
		logger.info("Search list permission.");

		Session session = getOpenSession();
		Query query = session.createQuery("from Permissions where deleteFlag = :deleteFlag");
		query.setParameter("deleteFlag", ConstantModel.DEL_FLG);
		logger.info("Search list permission end.");

		return query.list();

	}

}
