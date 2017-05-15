package com.framgia.users.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.Book;
import com.framgia.users.model.Borroweds;
import com.framgia.util.DateUtil;

/**
 * ManagementUsersController.java
 * 
 * @version 08/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Repository("bookDao")
public class BookDaoImpl extends AbstractDao<Integer, Borroweds> implements BookDao {

	// log
	private static final Logger logger = Logger.getLogger(BookDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean update(Book mBook) {
		try {

			logger.info("Update book.");

			Criteria crit = getSession().createCriteria(Book.class);

			crit.add(Restrictions.eq("bookId", mBook.getBookId()));

			// Here is updated code
			List<Book> items = crit.list();

			if (items != null && items.size() > 0) {
				Book mUpdBook = (Book) items.get(0);

				mUpdBook.setUserUpdate(mBook.getUserUpdate());
				mUpdBook.setDateUpdate(DateUtil.getDateNow());
				mUpdBook.setNumberRest(mUpdBook.getNumberRest() + 1);
				mUpdBook.setNumberBorrowed(mUpdBook.getNumberBorrowed() - 1);

				getSession().saveOrUpdate(mUpdBook);
				logger.info("Update end.");
				return true;
			}

		} catch (Exception e) {
			logger.error("Error update Book: " + e.getMessage());

			return false;
		}

		return false;
	}

}
