package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

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

	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public List<Permissions> findByDelFlg() {
		Session session = getOpenSession();
		List<Permissions> queryList = new ArrayList<Permissions>();
		try {
			Query query = session.createQuery("from Permissions where deleteFlag = :deleteFlag");
			query.setParameter("deleteFlag", ConstantModel.DEL_FLG);
			queryList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			return queryList;
		}
	}

}
