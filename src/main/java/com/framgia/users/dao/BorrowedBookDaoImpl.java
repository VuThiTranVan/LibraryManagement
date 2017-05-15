package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.BorrowedDetails;
import com.framgia.users.model.Borroweds;
import com.framgia.users.model.ConstantModel;
import com.framgia.util.ConditionSearchBorrowed;
import com.framgia.util.DateUtil;

/**
 * ManagementUsersController.java
 * 
 * @version 03/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Repository("borrowedBookDao")
public class BorrowedBookDaoImpl extends AbstractDao<Integer, Borroweds> implements BorrowedBookDao {

	// log
	private static final Logger logger = Logger.getLogger(BorrowedBookDaoImpl.class);

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Borroweds> findByCondition(ConditionSearchBorrowed condition) {
		logger.info("Search list borroweds");

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
			logger.info("End search.");

			return queryList;

		} catch (Exception e) {
			logger.error("Search list borrowed error: " + e.getMessage());

			return null;
		}
	}

	@Override
	public Borroweds findByIdBorrowed(int idBorrowed) {
		logger.info("Search borrowed by Id");

		Borroweds borrowed = new Borroweds();
		try {
			Session session = getOpenSession();
			String sql = "FROM Borroweds where deleteFlag = :deleteFlag AND borrowedId = :borrowedId";

			Query query = session.createQuery(sql);

			query.setParameter("deleteFlag", ConstantModel.DEL_FLG);

			query.setParameter("borrowedId", idBorrowed);

			if (query.list().size() > 0) {
				borrowed = (Borroweds) query.list().get(0);
			}

			logger.info("Search end.");
			return borrowed;

		} catch (Exception e) {
			logger.error("Search find by id error: " + e.getMessage());

			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Borroweds update(Borroweds mBorrowed) {
		logger.info("Update borroweds");

		Borroweds mUpdBorrowed = new Borroweds();

		try {
			Criteria crit = getSession().createCriteria(Borroweds.class);

			crit.add(Restrictions.eq("borrowedId", mBorrowed.getBorrowedId()));
			crit.add(Restrictions.eq("dateUpdate", mBorrowed.getDateUpdate()));

			List<Borroweds> items = crit.list();

			if (items != null && items.size() > 0) {
				mUpdBorrowed = (Borroweds) items.get(0);

				switch (mBorrowed.getStatus()) {
				case ConstantModel.BOR_STATUS_APPROVE:
					mUpdBorrowed.setStatus(ConstantModel.BOR_STATUS_APPROVE);
					break;
				case ConstantModel.BOR_STATUS_CANCEL:
					mUpdBorrowed.setStatus(ConstantModel.BOR_STATUS_CANCEL);

					break;
				case ConstantModel.BOR_STATUS_BORRWED:
					mUpdBorrowed.setStatus(ConstantModel.BOR_STATUS_BORRWED);
					mUpdBorrowed.setDateBorrrowed(mBorrowed.getDateBorrrowed());
					break;
				case ConstantModel.BOR_STATUS_FINISH:
					mUpdBorrowed.setStatus(ConstantModel.BOR_STATUS_FINISH);

					if (StringUtils.isNotEmpty(mBorrowed.getDateBorrrowed().toString())) {
						mUpdBorrowed.setDateBorrrowed(mBorrowed.getDateBorrrowed());
					}

					mUpdBorrowed.setDateArrived(mBorrowed.getDateArrived());
					break;
				}

				mUpdBorrowed.setUserUpdate(mBorrowed.getUserUpdate());
				mUpdBorrowed.setDateUpdate(DateUtil.getDateNow());

				getSession().saveOrUpdate(mUpdBorrowed);
				logger.info("Update borrowed end.");

				return mUpdBorrowed;
			}
		} catch (Exception e) {
			logger.error("Update Borrowed error: " + e.getMessage());

			return null;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BorrowedDetails updateBorrowedDetails(BorrowedDetails mBorrowedDetails) {
		logger.info("Update borrowed detail.");

		try {

			Criteria crit = getSession().createCriteria(BorrowedDetails.class);

			crit.add(Restrictions.eq("borrowedDetailId", mBorrowedDetails.getBorrowedDetailId()));
			crit.add(Restrictions.eq("dateUpdate", mBorrowedDetails.getDateUpdate()));

			// Here is updated code
			List<BorrowedDetails> items = crit.list();

			if (items != null && items.size() > 0) {
				BorrowedDetails mUpdBorrowed = new BorrowedDetails();

				mUpdBorrowed = (BorrowedDetails) items.get(0);

				mUpdBorrowed.setUserUpdate(mBorrowedDetails.getUserUpdate());
				mUpdBorrowed.setDateUpdate(DateUtil.getDateNow());
				mUpdBorrowed.setStatus(mBorrowedDetails.getStatus());

				getSession().saveOrUpdate(mUpdBorrowed);

				logger.info("Update borrowed detail end.");

				return mUpdBorrowed;
			}
		} catch (Exception e) {
			logger.error("Update borrowed detail error: " + e.getMessage());

			return null;
		}
		return null;
	}
}
