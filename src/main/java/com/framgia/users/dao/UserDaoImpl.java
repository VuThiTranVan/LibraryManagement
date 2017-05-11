package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.ConstantModel;
import com.framgia.users.model.Permissions;
import com.framgia.users.model.Users;
import com.framgia.util.DateUtil;

/**
 * UserDaoImpl.java
 * 
 * @version 16/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, Users> implements ConstantModel, UserDao {

	// log
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Users findByUserName(String username) {

		logger.info("Search user by username.");
		List<Users> users = new ArrayList<Users>();

		users = getSession().createQuery("from Users where userName=:username and deleteFlag=:delFlg")
		        .setParameter("username", username).setParameter("delFlg", ConstantModel.DEL_FLG).list();

		if (users.size() > 0) {

			logger.info("End search user by username.");

			return users.get(0);
		} else {

			logger.error("Search user by username ERROR");

			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public List<Users> findByConditon(String txtName, int txtPermission) {

		logger.info("Search list user.");

		List<Users> queryList = new ArrayList<Users>();

		Session session = getOpenSession();

		try {
			String sql = "from Users where deleteFlag = :deleteFlag";
			if (null != txtName && StringUtils.isNotEmpty(txtName)) {
				sql = sql + " and userName like :userName";
			}

			if (txtPermission > 0) {
				sql = sql + " and permissionsId = :permissionsId";
			}
			Query query = session.createQuery(sql);

			query.setParameter("deleteFlag", ConstantModel.DEL_FLG);
			if (null != txtName && StringUtils.isNotEmpty(txtName)) {
				query.setParameter("userName", "%" + txtName + "%");
			}

			if (txtPermission > 0) {
				query.setParameter("permissionsId", txtPermission);
			}

			queryList = query.list();
			logger.info("Search list user end.");
		} catch (Exception e) {
			logger.error("Error search list user: " + e.getMessage());
		} finally {
			return queryList;
		}

	}

	@Override
	public boolean delLogicUser(int idUser, String userUpd, Date dateUpdate) {
		logger.info("Delete user.");

		try {

			Criteria crit = getSession().createCriteria(Users.class);

			crit.add(Restrictions.eq("userId", idUser));
			crit.add(Restrictions.eq("dateUpdate", dateUpdate));

			// Here is updated code
			ScrollableResults items = crit.scroll();

			while (items.next()) {
				Users user = (Users) items.get(0);

				user.setUserUpdate(userUpd);
				user.setDeleteFlag(ConstantModel.DEL_FLG_DEL);
				user.setDateUpdate(DateUtil.getDateNow());

				getSession().saveOrUpdate(user);

				logger.info("Delete user end.");

				return true;
			}
		} catch (Exception e) {
			logger.error("Error delete user: " + e.getMessage());

			return false;
		}

		return false;
	}

	@SuppressWarnings("finally")
	@Override
	public Users findById(int idUser) {
		logger.info("Search user by is.");

		Users user = new Users();

		Session session = getOpenSession();

		try {
			String sql = "from Users where deleteFlag = :deleteFlag AND userId = :idUser";

			Query query = session.createQuery(sql);

			query.setParameter("deleteFlag", ConstantModel.DEL_FLG);

			query.setParameter("idUser", idUser);

			if (query.list().size() > 0) {
				user = (Users) query.list().get(0);
			}
			logger.info("Search user by id end.");

		} catch (Exception e) {
			logger.error("Error search user by id: " + e.getMessage());
		} finally {
			return user;
		}

	}

	@Override
	public boolean updateUser(Users user) {
		logger.info("Update user.");
		try {
			Criteria crit = getSession().createCriteria(Users.class);

			crit.add(Restrictions.eq("userId", user.getUserId()));
			crit.add(Restrictions.eq("dateUpdate", user.getDateUpdate()));

			// Here is updated code
			ScrollableResults items = crit.scroll();

			while (items.next()) {
				Users userUpd = (Users) items.get(0);

				if (null != user.getName() && !user.getName().isEmpty()) {
					userUpd.setName(user.getName());
				}

				if (0 < user.getPermissions().getPermissionsId()) {
					Permissions per = new Permissions();
					per.setPermissionsId(user.getPermissions().getPermissionsId());
					userUpd.setPermissions(per);
				}

				if (null != user.getBirthDate() && !user.getBirthDate().toString().isEmpty()) {
					userUpd.setBirthDate(user.getBirthDate());
				}

				if (null != user.getAddress() && !user.getAddress().isEmpty()) {
					userUpd.setAddress(user.getAddress());
				}

				if (null != user.getPhone() && !user.getPhone().isEmpty()) {
					userUpd.setPhone(user.getPhone());
				}

				if (null != user.getSex() && !user.getSex().isEmpty()) {
					userUpd.setSex(user.getSex());
				}

				if (null != user.getEmail() && !user.getEmail().isEmpty()) {
					userUpd.setEmail(user.getEmail());
				}
				Date dateUpd = DateUtil.getDateNow();
				userUpd.setDateUpdate(dateUpd);
				userUpd.setUserUpdate(user.getUserUpdate());

				getSession().saveOrUpdate(userUpd);

				logger.info("Update user end.");

				return true;
			}
		} catch (Exception e) {
			logger.error("Error update Book: " + e.getMessage());

			return false;
		}

		return false;
	}

	@Override
	public Users updatePassword(Users user) {

		logger.info("Update password.");
		try {
			Criteria crit = getSession().createCriteria(Users.class);

			if (null != user.getEmail()) {
				crit.add(Restrictions.eq("email", user.getEmail()));
			}

			if (null != user.getName()) {
				crit.add(Restrictions.eq("userName", user.getUserName()));
			}

			// Here is updated code
			ScrollableResults items = crit.scroll();

			while (items.next()) {
				Users userUpd = (Users) items.get(0);

				userUpd.setPassWord(user.getPassWord());
				userUpd.setUserUpdate(user.getUserUpdate());
				userUpd.setDateUpdate(DateUtil.getDateNow());
				
				getSession().saveOrUpdate(userUpd);

				logger.info("Update password end.");
				return userUpd;
			}
		} catch (Exception e) {
			logger.error("Error update password: " + e.getMessage());
		}
		return null;
	}
}
