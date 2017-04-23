package com.framgia.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framgia.users.bean.BorrowedInfo;
import com.framgia.users.dao.BorrowedBookDao;
import com.framgia.users.model.Borroweds;
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

	@Autowired
	private BorrowedBookDao borrowedBookDao;

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

}
