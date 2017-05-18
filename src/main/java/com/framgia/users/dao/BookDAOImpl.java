package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.Book;
import com.framgia.users.model.Borroweds;
import com.framgia.users.model.ConstantModel;

/**
 * BookDAOImpl.java
 * 
 * @version 24/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Repository("bookDAO")
public class BookDAOImpl extends AbstractDao<Integer, Book> implements ConstantModel, BookDAO {

	// log
	private static final Logger logger = Logger.getLogger(BookDAOImpl.class);

	@Override
	public void insertBook(Book book) {

		// Insert data into table Books
		getOpenSession().saveOrUpdate(book);
		logger.info("Insert success.");
	}

	@Override
	public void update(Book mBook) {
		getSession().saveOrUpdate(mBook);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Book findBookId(String bookId) {
		logger.info("Search book by id");
		Criteria crit = getOpenSession().createCriteria(Book.class);
		crit.createAlias("bookDetail", "detail")
		 .add(Restrictions.eq("detail.deleteFlag", ConstantModel.DEL_FLG));
		crit.add(Restrictions.eq("deleteFlag", ConstantModel.DEL_FLG));
		crit.add(Restrictions.eq("bookId", bookId));
		

		List<Book> items = crit.list();

		if (items != null && items.size() > 0) {
			return items.get(0);
		}
		return null;
//		book = getOpenSession().createQuery("from Book as book where bookId=:bookId and deleteFlag=:delFlg")
//				.setParameter("bookId", Integer.parseInt(bookId))
//				.setParameter("delFlg", ConstantModel.DEL_FLG)
//				.list();
		

//		if (book.size() > 0) {
//
//			return book.get(0);
//		} else {
//
//			return null;
//		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]>  findByConditon(String bookText, String category) {

		logger.info("Search list Book.");
		List<Object[]> listResult = null;
		Session session = getOpenSession();

		try {
			String sql = "from Book book "
					+ "inner join book.bookDetail bookDetail "
					+ "inner join book.categories categories "
					+ "inner join book.publishers publishers "
					+ "inner join bookDetail.author author "
					+ "where book.deleteFlag = :deleteFlag and bookDetail.deleteFlag = :deleteFlagDetail";
			
			if (StringUtils.isNotBlank(bookText)) {
				sql = sql + " and book.bookCode like :bookCode and book.name like :bookName";
			}
			if (StringUtils.isNotBlank(category) && Integer.parseInt(category) > 0) {
				sql = sql + " and categories.categoriesId = :categoriesId";
			}

			Query query = session.createQuery(sql);

			query.setParameter("deleteFlag", ConstantModel.DEL_FLG);
			query.setParameter("deleteFlagDetail", ConstantModel.DEL_FLG);
			if (StringUtils.isNotBlank(bookText)) {
				query.setParameter("bookCode", "%" + bookText + "%");
				query.setParameter("bookName", "%" + bookText + "%");
			}

			if (StringUtils.isNotBlank(category) && Integer.parseInt(category) > 0) {
				query.setParameter("categoriesId", Integer.parseInt(category));
			}
			listResult = query.list();
			return listResult;
		} catch (Exception e) {
			logger.error("Error search list user: " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Book findBookIdForUpdate(int bookId) {
		logger.info("Search book to update");
		Criteria crit = getSession().createCriteria(Book.class);
		crit.add(Restrictions.eq("deleteFlag", ConstantModel.DEL_FLG));
		crit.add(Restrictions.eq("bookId", bookId));
		crit.setLockMode(LockMode.UPGRADE);

		List<Book> items = crit.list();

		if (items != null && items.size() > 0) {
			return items.get(0);
		}
		return null;
	}
}
