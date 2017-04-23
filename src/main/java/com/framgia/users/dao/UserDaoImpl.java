package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

	@SuppressWarnings("unchecked")
	@Override
	public Users findByUserName(String username) {
		List<Users> users = new ArrayList<Users>();

		users = getSession().createQuery("from Users where userName=:username and deleteFlag=:delFlg")
				.setParameter("username", username).setParameter("delFlg", ConstantModel.DEL_FLG).list();

		if (users.size() > 0) {

			return users.get(0);
		} else {

			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public List<Users> findByConditon(String txtName, int txtPermission) {

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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return queryList;
		}

	}

	@Override
	public boolean delLogicUser(int idUser, String userUpd, Date dateUpdate) {

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

				return true;
			}
		} catch (Exception e) {

			return false;
		}

		return false;
	}

	@SuppressWarnings("finally")
	@Override
	public Users findById(int idUser) {

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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return user;
		}

	}

	@Override
	public Users updateUser(Users user) {

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

				getSession().saveOrUpdate(userUpd);

				return userUpd;
			}
		} catch (Exception e) {

			return null;
		}

		return null;
	}
}
