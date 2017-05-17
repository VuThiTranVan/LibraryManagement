package com.framgia.users.service;

import java.util.List;

import com.framgia.users.bean.BookInfo;
import com.framgia.users.bean.CategoryInfo;

/**
 * ManagementBookService.java
 * 
 * @version 17/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
public interface ManagementBookService {

	// Search Permission with no condition
	List<CategoryInfo> findCategoryId();

	// find by conditon form of admin
	List<BookInfo> findByConditon(String book, String categoryId);
}
