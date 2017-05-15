package com.framgia.users.dao;

import java.text.ParseException;
import java.util.List;

import com.framgia.users.model.BorrowedDetails;
import com.framgia.users.model.Borroweds;
import com.framgia.util.ConditionSearchBorrowed;

public interface BorrowedBookDao {
	List<Borroweds> findByCondition(ConditionSearchBorrowed condition);

	Borroweds findByIdBorrowed(int idBorrowed);

	Borroweds update(Borroweds mBorrowed) throws ParseException;
	
	BorrowedDetails updateBorrowedDetails(BorrowedDetails mBorrowedDetails) throws ParseException;
}
