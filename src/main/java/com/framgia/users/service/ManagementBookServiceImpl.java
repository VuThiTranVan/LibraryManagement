package com.framgia.users.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framgia.users.bean.BookInfo;
import com.framgia.users.bean.CategoryInfo;
import com.framgia.users.dao.BookDAO;
import com.framgia.users.dao.CategoryDAO;
import com.framgia.users.model.Book;
import com.framgia.users.model.Categories;
import com.framgia.util.ConvertDataModelAndBean;
import com.framgia.util.Helpers;

/**
 * ManagementBookServiceImpl.java
 * 
 * @version 17/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Service("managementBookService")
public class ManagementBookServiceImpl implements ManagementBookService {

	// log
	private static final Logger logger = Logger.getLogger(ManagementUsersServiceImpl.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private BookDAO bookDao;

	@Override
	public List<CategoryInfo> findCategoryId() {
		logger.info("Get pull category");

		// get value category for select box
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();

		List<Categories> categoryList = categoryDAO.listCategory();

		if (!Helpers.isEmpty(categoryList)) {
			for (Categories item : categoryList) {

				CategoryInfo categoryInfo = new CategoryInfo();
				categoryInfo = ConvertDataModelAndBean.converCategoryModelToBean(item);

				categoryInfoList.add(categoryInfo);
			}
		}

		return categoryInfoList;
	}

	@Override
	public List<BookInfo> findByConditon(String book, String categoryId) {
		logger.info("Get book by finbyCondition");

		List<Book> bookList = bookDao.findByConditon(book, categoryId);

		if (!Helpers.isEmpty(bookList)) {
			List<BookInfo> bookInfoList = new ArrayList<BookInfo>();

			for (Book item : bookList) {

				BookInfo bookInfo = new BookInfo();

				bookInfo = ConvertDataModelAndBean.converBookModelToBean(item);

				bookInfoList.add(bookInfo);

			}
			return bookInfoList;
		}
		
		logger.info("finbyCondition: No result");
		return null;
	}

}
