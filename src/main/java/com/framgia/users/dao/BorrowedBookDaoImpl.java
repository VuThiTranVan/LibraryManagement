package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.Borroweds;
import com.framgia.users.model.ConstantModel;
import com.framgia.util.ConditionSearchBorrowed;

/**
 * ManagementUsersController.java
 * 
 * @version 03/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Repository("borrowedBookDao")
public class BorrowedBookDaoImpl extends AbstractDao<Integer, Borroweds> implements BorrowedBookDao {

	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public List<Borroweds> findByCondition(ConditionSearchBorrowed condition) {

		Session session = getOpenSession();
		List<Borroweds> queryList = new ArrayList<Borroweds>();
		try {

			String sql = "FROM Borroweds where deleteFlag = :deleteFlag";

			// add condition
			if (StringUtils.isNotEmpty(condition.getTxtBorrowedCode())) {
				sql = sql + " AND borrowedCode LIKE :borrowedCode";
			}

			if (StringUtils.isNotEmpty(condition.getTxtStatus())) {
				sql = sql + " AND status = :status";
			}

			if (StringUtils.isNotEmpty(condition.getTxtIntenDateBorrowed())) {
				sql = sql + " AND dIntendBorrowed = :dIntendBorrowed";
			}

			if (StringUtils.isNotEmpty(condition.getTxtIntenDatePayment())) {
				sql = sql + " AND dIntendArrived = :dIntendArrived";
			}

			if (StringUtils.isNotEmpty(condition.getTxtDateBorrowed())) {
				sql = sql + " AND dateBorrrowed = :dateBorrrowed";
			}

			if (StringUtils.isNotEmpty(condition.getTxtDatePayment())) {
				sql = sql + " AND dateArrived = :dateArrived";
			}

			Query query = session.createQuery(sql);

			// set param
			query.setParameter("deleteFlag", ConstantModel.DEL_FLG);

			if (StringUtils.isNotEmpty(condition.getTxtBorrowedCode())) {
				query.setParameter("borrowedCode", condition.getTxtBorrowedCode());
			}

			if (StringUtils.isNotEmpty(condition.getTxtStatus())) {
				query.setParameter("status", condition.getTxtStatus());
			}

			if (StringUtils.isNotEmpty(condition.getTxtIntenDateBorrowed())) {
				query.setParameter("dIntendBorrowed", condition.getTxtIntenDateBorrowed());
			}

			if (StringUtils.isNotEmpty(condition.getTxtIntenDatePayment())) {
				query.setParameter("dIntendArrived", condition.getTxtIntenDatePayment());
			}

			if (StringUtils.isNotEmpty(condition.getTxtDateBorrowed())) {
				query.setParameter("dateBorrrowed", condition.getTxtDateBorrowed());
			}

			if (StringUtils.isNotEmpty(condition.getTxtDatePayment())) {
				query.setParameter("dateArrived", condition.getTxtDatePayment());
			}

			queryList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			return queryList;
		}
	}
}
