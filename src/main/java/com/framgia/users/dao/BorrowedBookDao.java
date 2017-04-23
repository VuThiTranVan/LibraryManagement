package com.framgia.users.dao;

import java.util.List;

import com.framgia.users.model.Borroweds;
import com.framgia.util.ConditionSearchBorrowed;

public interface BorrowedBookDao {
	List<Borroweds> findByCondition(ConditionSearchBorrowed condition);
}
