package com.framgia.users.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framgia.users.bean.BookDetailInfo;
import com.framgia.users.bean.BookInfo;
import com.framgia.users.bean.CategoryInfo;
import com.framgia.users.bean.PublisherInfo;
import com.framgia.users.dao.BookDAO;
import com.framgia.users.dao.CategoryDAO;
import com.framgia.users.model.Author;
import com.framgia.users.model.Book;
import com.framgia.users.model.BookDetail;
import com.framgia.users.model.Categories;
import com.framgia.users.model.ConstantModel;
import com.framgia.users.model.Publishers;
import com.framgia.util.ConvertDataModelAndBean;
import com.framgia.util.DateUtil;
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

		List<BookInfo> bookInfoList = new ArrayList<BookInfo>();

		List<BookDetailInfo> bookDetailList = new ArrayList<BookDetailInfo>();

		List<Object[]> bookList = bookDao.findByConditon(book, categoryId);

		int idBook = 0;

		boolean flgInit = true;
		boolean flagOneBook = true;
		int count = 1;
		for (Object[] aRow : bookList) {
			BookInfo bookInfo = new BookInfo();

			bookInfo = ConvertDataModelAndBean.converBookModelToBean((Book) aRow[0]);

			if (flgInit) {
				idBook = bookInfo.getBookId();
				flgInit = false;

				// add book infor
				Categories category = new Categories();
				category = (Categories) aRow[3];
				bookInfo.setCategoriesId(category.getcategoriesId());
				bookInfo.setCategoriesName(category.getName());
				bookInfo.setCategoriesCode(category.getCategoriesCode());

				PublisherInfo publisherInfo = new PublisherInfo();
				publisherInfo = ConvertDataModelAndBean.converPublisherModelToBean((Publishers) aRow[4]);
				bookInfo.setPublishersId(publisherInfo.getPublishersId());
				bookInfo.setPublishersName(publisherInfo.getPublishersName());
				bookInfo.setPublishersPhone(publisherInfo.getPhone());
				bookInfo.setPublishersAddress(publisherInfo.getPhone());
				bookInfo.setPublishersEmail(publisherInfo.getEmail());
				bookInfoList.add(bookInfo);
			}

			if (bookInfo.getBookId() == idBook) {
				// detail
				Author author = new Author();
				author = (Author) aRow[2];
				BookDetail mBookDetail = new BookDetail();
				mBookDetail = (BookDetail) aRow[1];
				mBookDetail.setAuthor(author);

				BookDetailInfo bookDetail = new BookDetailInfo();
				bookDetail = ConvertDataModelAndBean.converBookDetailModelToBean(mBookDetail);

				bookDetailList.add(bookDetail);
			} else {
				flagOneBook = false;
				bookInfoList.get(bookInfoList.size() - 1).setBookDetail(bookDetailList);

				bookDetailList = new ArrayList<BookDetailInfo>();

				// detail
				Author author = new Author();
				author = (Author) aRow[2];
				BookDetail mBookDetail = new BookDetail();
				mBookDetail = (BookDetail) aRow[1];
				mBookDetail.setAuthor(author);

				BookDetailInfo bookDetail = new BookDetailInfo();
				bookDetail = ConvertDataModelAndBean.converBookDetailModelToBean(mBookDetail);

				bookDetailList.add(bookDetail);

				// Info
				// add book infor
				Categories category = new Categories();
				category = (Categories) aRow[3];
				bookInfo.setCategoriesId(category.getcategoriesId());
				bookInfo.setCategoriesName(category.getName());
				bookInfo.setCategoriesCode(category.getCategoriesCode());

				PublisherInfo publisherInfo = new PublisherInfo();
				publisherInfo = ConvertDataModelAndBean.converPublisherModelToBean((Publishers) aRow[4]);
				bookInfo.setPublishersId(publisherInfo.getPublishersId());
				bookInfo.setPublishersName(publisherInfo.getPublishersName());
				bookInfo.setPublishersPhone(publisherInfo.getPhone());
				bookInfo.setPublishersAddress(publisherInfo.getPhone());
				bookInfo.setPublishersEmail(publisherInfo.getEmail());

				bookInfo.setBookDetail(bookDetailList);

				bookInfoList.add(bookInfo);

				idBook = bookInfo.getBookId();
			}

			if (count == bookList.size() && flagOneBook) {
				bookInfoList.get(bookInfoList.size() - 1).setBookDetail(bookDetailList);
			}
			count++;

		}

		return bookInfoList;
	}

	@Transactional
	@Override
	public String deleteBook(int bookId, String userUpdate) {
		// TODO Auto-generated method stub
		Book updBook = new Book();

		updBook = bookDao.findBookIdForUpdate(bookId);

		if (updBook == null) {
			return "error";
		}
		try {

			updBook.setDeleteFlag(ConstantModel.DEL_FLG_DEL);
			updBook.setUserUpdate(userUpdate);
			updBook.setDateUpdate(DateUtil.getDateNow());

			bookDao.update(updBook);
			return "success";
		} catch (Exception e) {
			logger.error("Error delete logic: ", e);
		}
		return "error";
	}

	@Override
	public BookInfo findBookId(String bookId) {
		BookInfo bookInfo = new BookInfo();

		Book mBook = bookDao.findBookId(bookId);

		if (null != mBook) {
			bookInfo = ConvertDataModelAndBean.converBookModelToBean(mBook);
		}

		// TODO Auto-generated method stub
		return bookInfo;
	}

}
