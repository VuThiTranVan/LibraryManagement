package com.framgia.users.service;

import java.util.List;

import com.framgia.users.bean.BorrowedInfo;
import com.framgia.util.ConditionSearchBorrowed;

/**
 * ManagementUsersController.java
 * 
 * @version 03/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
public interface ManagementBorrowedBookService {
	
	// Search infomation borowed book with init screen management borrowed books
	List<BorrowedInfo> getBorrowedInfoByFindCondition(ConditionSearchBorrowed condition);
}
