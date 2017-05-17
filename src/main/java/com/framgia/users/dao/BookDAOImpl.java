package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.Book;
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
		List<Book> book = new ArrayList<Book>();

		book = getOpenSession().createQuery("from Book where bookId=:bookId and deleteFlag=:delFlg")
		        .setParameter("bookId", Integer.parseInt(bookId)).setParameter("delFlg", ConstantModel.DEL_FLG).list();

		if (book.size() > 0) {

			return book.get(0);
		} else {

			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findByConditon(String book, String category) {

		logger.info("Search list Book.");

		Session session = getOpenSession();

		try {
			String sql = "from Book where deleteFlag = :deleteFlag";

			if (StringUtils.isNotBlank(book)) {
				sql = sql + " and bookCode like :book and name like :book";
			}
			if (StringUtils.isNotBlank(category) && Integer.parseInt(category) > 0 ) {
				sql = sql + " and categoriesId = :categoriesId";
			}

			Query query = session.createQuery(sql);

			query.setParameter("deleteFlag", ConstantModel.DEL_FLG);
			if (StringUtils.isNotBlank(book)) {
				query.setParameter("userName", "%" + book + "%");
				query.setParameter("book", "%" + book + "%");
			}

			if (StringUtils.isNotBlank(category) && Integer.parseInt(category) > 0 ) {
				query.setParameter("categoriesId", category);
			}
			return query.list();
		} catch (Exception e) {
			logger.error("Error search list user: " + e.getMessage());
			return null;
		}
	}
}
