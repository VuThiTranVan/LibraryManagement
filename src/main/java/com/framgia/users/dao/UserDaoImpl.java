package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.framgia.users.model.Users;

/**
 * UserDaoImpl.java
 * 
 * @version 16/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	public static String DEL_FLG = "0";
	public static String DEL_FLG_DEL = "1";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Search user with function login
	 * 
	 * @param name,
	 *            permission
	 * @return List<Users>
	 */
	@SuppressWarnings("unchecked")
	public Users findByUserName(String username) {

		List<Users> users = new ArrayList<Users>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from Users where " + "userName=:username and deleteFlag=:delFlg")
				.setParameter("username", username).setParameter("delFlg", DEL_FLG).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Get list users for search by condition
	 * 
	 * @param name,
	 *            permission
	 * @return List<Users>
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByUsersWithCondition(String txtName, String txtPermission) {

		List<Users> result = new ArrayList<>();

		Session session = sessionFactory.openSession();

		try {

			String sql = "SELECT u.userId as userId, " + "p.permissionsId as permissionsId, "
					+ "u.userName as userName, " + "u.passWord as passWord, " + "u.birthDate as birthDate, "
					+ "u.name as name, " + "u.address as address, " + "u.phone as phone, " + "u.sex as sex, "
					+ "u.email as email, " + "u.deleteFlag as deleteFlag, " + "u.dateCreate as dateCreate, "
					+ "u.userCreate as userCreate, " + "u.dateUpdate as dateUpdate, " + "u.userUpdate as userUpdate "
					+ "FROM Users u " + "INNER JOIN Permissions p " + "ON u.permissionsId = p.permissionsId "
					+ "where u.deleteFlag=0 and p.deleteFlag = 0";

			if (StringUtils.isNotEmpty(txtName) || StringUtils.isNotBlank(txtName)) {
				sql += " AND u.name = :namePar";
			}

			if (0 < Integer.parseInt(txtPermission)) {
				sql += " AND u.permissionsId = :permissionPar";
			}

			// Creat Native sql
			SQLQuery selectQuery = session.createSQLQuery(sql);

			// Add parameter
			if (!StringUtils.isEmpty(txtName) && !StringUtils.isBlank(txtName)) {
				selectQuery.setParameter("namePar", txtName);
			}

			if (0 < Integer.parseInt(txtPermission)) {
				selectQuery.setParameter("permissionPar", txtPermission);
			}
			result = selectQuery.addEntity(Users.class).list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		// return result
		return result;
	}

	/**
	 * Search by all user
	 * 
	 * @param name,
	 *            permission
	 * @return List<Users>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByAllUser() {

		List<Users> users = new ArrayList<Users>();

		users = sessionFactory.getCurrentSession().createQuery("from Users where deleteFlag=:delFlg")
				.setParameter("delFlg", DEL_FLG).list();

		return users;
	}

	/**
	 * Search user with function login
	 * 
	 * @param idUser
	 * @return Users
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Users findByIdUser(String idUser) {
		List<Users> users = new ArrayList<Users>();

		Session session = sessionFactory.openSession();
		try {
			String sql = "SELECT * FROM Users u WHERE u.userId = :idUser AND deleteFlag=:delFlg";

			SQLQuery selectQuery = session.createSQLQuery(sql);

			selectQuery.setParameter("idUser", idUser);
			selectQuery.setParameter("delFlg", DEL_FLG);

			users = selectQuery.addEntity(Users.class).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int delLogicUser(String idUser, String userUpd) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int result = 0;

		try {
			tx = session.beginTransaction();
			// update table slip_product
			String sql = "UPDATE Users SET dateUpdate = NOW()," + " userUpdate = :userUpd," + " deleteFlag=:delFlg"
					+ " where userId = :idUser";
			SQLQuery selectQuery = session.createSQLQuery(sql);

			// Set parameter
			selectQuery.setParameter("userUpd", userUpd);
			selectQuery.setParameter("idUser", idUser);
			selectQuery.setParameter("delFlg", DEL_FLG_DEL);

			result = selectQuery.executeUpdate();

			if (result == 0) {
				if (tx != null) {
					tx.rollback();
					return 0;
				}
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
				return 0;
			}
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int updateUser(Users uses) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int result = 0;

		try {
			tx = session.beginTransaction();
			// update table slip_product
			String sql = "UPDATE Users SET" +
					" birthDate = :birthDate," +
					" name = :name," +
					" address = :address," +
					" phone = :phone," +
					" sex = :sex," +
					" email = :email," +
					" userUpdate = :userUpdate," +
					" dateUpdate = NOW()"
					+ " where userId = :idUser";

			SQLQuery selectQuery = session.createSQLQuery(sql);

			// Set parameter
			selectQuery.setParameter("birthDate", uses.getBirthDate());
			selectQuery.setParameter("name", uses.getName());
			selectQuery.setParameter("address", uses.getAddress());
			selectQuery.setParameter("phone", uses.getPhone());
			selectQuery.setParameter("sex", uses.getSex());
			selectQuery.setParameter("email", uses.getEmail());
			selectQuery.setParameter("userUpdate", uses.getUserUpdate());
			selectQuery.setParameter("idUser", uses.getUserId());


			result = selectQuery.executeUpdate();

			if (result == 0) {
				if (tx != null) {
					tx.rollback();
					return 0;
				}
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
				return 0;
			}
		} finally {
			session.close();
		}
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
