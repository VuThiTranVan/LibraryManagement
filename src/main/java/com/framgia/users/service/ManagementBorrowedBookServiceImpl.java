package com.framgia.users.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framgia.users.bean.BorrowedInfo;
import com.framgia.users.dao.BookDao;
import com.framgia.users.dao.BorrowedBookDao;
import com.framgia.users.model.Book;
import com.framgia.users.model.BorrowedDetails;
import com.framgia.users.model.Borroweds;
import com.framgia.users.model.ConstantModel;
import com.framgia.util.ConditionSearchBorrowed;
import com.framgia.util.ConvertDataModelAndBean;

/**
 * ManagementBorrowedBookServiceImpl.java
 * 
 * @version 03/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@Service("managementBorrowedBookService")
public class ManagementBorrowedBookServiceImpl implements ManagementBorrowedBookService {

	// log
	private static final Logger logger = Logger.getLogger(ManagementBorrowedBookServiceImpl.class);

	@Autowired
	private BorrowedBookDao borrowedBookDao;

	@Autowired
	private BookDao bookDao;

	@Override
	public List<BorrowedInfo> getBorrowedInfoByFindCondition(ConditionSearchBorrowed condition) {

		List<Borroweds> mBorrowed = borrowedBookDao.findByCondition(condition);

		List<BorrowedInfo> bBorrowedInfo = new ArrayList<BorrowedInfo>();

		if (mBorrowed != null) {
			for (Borroweds item : mBorrowed) {

				BorrowedInfo borrowedInfo = new BorrowedInfo();
				borrowedInfo = ConvertDataModelAndBean.converBorrowedModelToBean(item);
				bBorrowedInfo.add(borrowedInfo);
			}
		}

		return bBorrowedInfo;
	}

	@Override
	public BorrowedInfo findByIdBorrowed(int idBorrowed) {

		Borroweds mBorrowed = borrowedBookDao.findByIdBorrowed(idBorrowed);
		BorrowedInfo bBorrowed = new BorrowedInfo();

		if (mBorrowed != null) {
			bBorrowed = ConvertDataModelAndBean.converBorrowedModelToBean(mBorrowed);
		}

		return bBorrowed;
	}

	@Transactional
	@Override
	public BorrowedInfo update(BorrowedInfo bBorrowed) {
		try {
			// Update Borrowed
			Borroweds mUpdBorrowed = new Borroweds();

			mUpdBorrowed = ConvertDataModelAndBean.converBorrowedBeanToModel(bBorrowed);

			Borroweds mBorroweds;

			mBorroweds = borrowedBookDao.update(mUpdBorrowed);

			if (mBorroweds != null) {
				BorrowedInfo updBorroweds = ConvertDataModelAndBean.converBorrowedModelToBean(mBorroweds);

				// Update Borrowed Detail
				boolean flgUpdBorrowedsDetail = true;
				boolean flgUpdBoook = true;

				BorrowedDetails updBookDetail = new BorrowedDetails();

				for (BorrowedDetails mUpdBorrowedDetails : mUpdBorrowed.getBorrowedDetails()) {
					mUpdBorrowedDetails.setUserUpdate(bBorrowed.getUserUpdate());
					updBookDetail = borrowedBookDao.updateBorrowedDetails(mUpdBorrowedDetails);

					if (null != updBookDetail && (ConstantModel.BOR_STATUS_CANCEL.equals(mUpdBorrowed.getStatus())
					        || ConstantModel.BOR_STATUS_FINISH.equals(mUpdBorrowed.getStatus())
					        || (ConstantModel.BOR_STATUS_BORRWED.equals(mUpdBorrowed.getStatus())
					                && ConstantModel.BOR_DET_STATUS_REJECT.equals(updBookDetail.getStatus())))) {

						flgUpdBoook = bookDao.update(updBookDetail.getBook());
						if (!flgUpdBoook) {
							break;
						}
					} else if (null == updBookDetail) {
						flgUpdBorrowedsDetail = false;
					}
				}

				if (ConstantModel.BOR_STATUS_FINISH.equals(mUpdBorrowed.getStatus())
				        && mUpdBorrowed.getBorrowedDetails().size() == 0) {

					for (BorrowedDetails mUpdBorrowedDetails : mBorroweds.getBorrowedDetails()) {
						if (ConstantModel.BOR_DET_STATUS_ACCEPT.equals(mUpdBorrowedDetails.getStatus())) {
							Book mUpdBook = new Book();
							mUpdBook.setUserUpdate(mUpdBorrowed.getUserUpdate());
							mUpdBook.setBookId(mUpdBorrowedDetails.getBook().getBookId());

							flgUpdBoook = bookDao.update(mUpdBook);

							if (!flgUpdBoook) {
								break;
							}
						}
					}
				}

				if (null != updBorroweds && flgUpdBorrowedsDetail && flgUpdBoook) {
					return updBorroweds;
				}
			}
		} catch (ParseException e) {
			logger.error("Error update borrowed: ", e);
			return null;
		}
		return null;
	}
}
