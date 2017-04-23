package com.framgia.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.framgia.users.bean.AuthorInfo;
import com.framgia.users.bean.BookDetailInfo;
import com.framgia.users.bean.BookInfo;
import com.framgia.users.bean.BorrowedDetailInfo;
import com.framgia.users.bean.BorrowedInfo;
import com.framgia.users.bean.CategoryInfo;
import com.framgia.users.bean.PermissionInfo;
import com.framgia.users.bean.PublisherInfo;
import com.framgia.users.bean.UserInfo;
import com.framgia.users.model.Author;
import com.framgia.users.model.Book;
import com.framgia.users.model.BookDetail;
import com.framgia.users.model.BorrowedDetails;
import com.framgia.users.model.Borroweds;
import com.framgia.users.model.Categories;
import com.framgia.users.model.ConstantModel;
import com.framgia.users.model.Permissions;
import com.framgia.users.model.Publishers;
import com.framgia.users.model.Users;

public class ConvertDataModelAndBean {
	public static String gender_male = "Male";
	public static String gender_cd_fmale = "0";
	public static String gender_fmale = "Fmale";
	public static String book_status_good = "Good";
	public static String book_status_bad = "Bad";
	public static String borr_detail_status_wait = "Wait";
	public static String borr_detail_status_accept = "Accept";
	public static String borr_detail_status_reject = "Reject";

	public static String borr_status_request = "Request";
	public static String borr_status_approve = "Approve";
	public static String borr_status_cancel = "Cancel";
	public static String borr_status_borrowed = "Borrowed";
	public static String borr_status_finish = "Finish";

	// Table Users
	public static UserInfo converUserModelToBean(Users mUser) {

		UserInfo bUser = new UserInfo();

		if (null != mUser && mUser.getUserId() != null) {
			if (null != mUser.getUserId()) {
				bUser.setUserId(mUser.getUserId());
			}
			bUser.setUserName(mUser.getUserName());
			bUser.setPassWord(mUser.getPassWord());
			if (null != mUser.getBirthDate()) {
				bUser.setBirthDate(mUser.getBirthDate().toString());
			}
			bUser.setName(mUser.getName());
			bUser.setAddress(mUser.getAddress());
			bUser.setPhone(mUser.getPhone());
			bUser.setPassWord(mUser.getPassWord());

			bUser.setSex(gender_male);
			if (StringUtils.isNotBlank(mUser.getSex()) && mUser.getSex().equals(gender_cd_fmale)) {
				bUser.setSex(gender_fmale);
			}
			bUser.setEmail(mUser.getEmail());
			bUser.setDeleteFlag(mUser.getDeleteFlag());

			if (null != mUser.getDateCreate()) {
				bUser.setDateCreate(mUser.getDateCreate().toString().substring(0, 19));
			}
			bUser.setUserCreate(mUser.getUserCreate());
			if (null != mUser.getDateUpdate()) {
				bUser.setDateUpdate(mUser.getDateUpdate().toString().substring(0, 19));
			}
			bUser.setUserUpdate(mUser.getUserUpdate());
			PermissionInfo perInfo = new PermissionInfo();

			perInfo.setPermissionName(mUser.getPermissions().getPermissionName());
			bUser.setPermissions(perInfo);
		}
		return bUser;
	}

	public static Users converUserBeanToModel(UserInfo bUser) {
		Users userModel = new Users();

		Permissions per = new Permissions();
		per.setPermissionsId(bUser.getPermissions().getPermissionsId());

		userModel.setUserId(bUser.getUserId());
		userModel.setName(bUser.getName());
		userModel.setPermissions(per);
		userModel.setBirthDate(DateUtil.convertStringtoDate(bUser.getBirthDate()));
		userModel.setEmail(bUser.getEmail());
		userModel.setPhone(bUser.getPhone());
		userModel.setSex(bUser.getSex());
		userModel.setAddress(bUser.getAddress());
		userModel.setUserUpdate(bUser.getUserUpdate());
		userModel.setDateUpdate(DateUtil.convertStringtoDateTime(bUser.getDateUpdate()));
		userModel.setDateCreate(DateUtil.convertStringtoDateTime(bUser.getDateCreate()));
		userModel.setDeleteFlag(bUser.getDeleteFlag());
		userModel.setUserCreate(bUser.getUserCreate());
		userModel.setPassWord(bUser.getPassWord());
		userModel.setUserName(bUser.getUserName());

		return userModel;
	}

	// Table Category
	public static CategoryInfo converCategoryModelToBean(Categories mCategory) {

		CategoryInfo bCategory = new CategoryInfo();
		bCategory.setCatSubId(mCategory.getCatSubId());
		bCategory.setCategoriesCode(mCategory.getCategoriesCode());
		bCategory.setName(mCategory.getName());
		return bCategory;
	}

	public static Categories converCategoryBeanToModel(CategoryInfo bCategory) {

		Categories mCategory = new Categories();
		mCategory.setCatSubId(bCategory.getCatSubId());
		mCategory.setCategoriesCode(bCategory.getCategoriesCode());
		mCategory.setName(bCategory.getName());

		return mCategory;
	}

	// Table Publisher
	public static PublisherInfo converPublisherModelToBean(Publishers mPublishers) {

		PublisherInfo bPublisher = new PublisherInfo();
		bPublisher.setPublishersName(mPublishers.getPublishersName());
		bPublisher.setPhone(mPublishers.getPhone());
		bPublisher.setEmail(mPublishers.getEmail());
		bPublisher.setAddress(mPublishers.getAddress());

		return bPublisher;
	}

	public static Publishers converPublisherBeanToModel(PublisherInfo bPublisher) {

		Publishers mPublisher = new Publishers();
		mPublisher.setPublishersName(bPublisher.getPublishersName());
		mPublisher.setPhone(bPublisher.getPhone());
		mPublisher.setEmail(bPublisher.getEmail());
		mPublisher.setAddress(bPublisher.getAddress());

		return mPublisher;
	}

	// Table Author
	public static AuthorInfo converAuthorModelToBean(Author mAuthor) {

		AuthorInfo bAuthor = new AuthorInfo();
		bAuthor.setAuthorsName(mAuthor.getAuthorsName());
		bAuthor.setSex(gender_male);

		if (StringUtils.isNotBlank(mAuthor.getSex()) && mAuthor.getSex().equals(gender_cd_fmale)) {
			bAuthor.setSex(gender_fmale);
		}

		bAuthor.setEmail(mAuthor.getEmail());
		bAuthor.setDescription(mAuthor.getDescription());
		bAuthor.setPhone(mAuthor.getPhone());
		bAuthor.setBirthday(mAuthor.getBirthday().toString());
		bAuthor.setAddress(mAuthor.getAddress());

		return bAuthor;
	}

	public static Author converAuthorBeanToModel(AuthorInfo bAuthor) {

		Author mAuthor = new Author();

		mAuthor.setAuthorsName(bAuthor.getAuthorsName());
		mAuthor.setSex(bAuthor.getSex());

		mAuthor.setEmail(bAuthor.getEmail());
		mAuthor.setDescription(bAuthor.getDescription());
		mAuthor.setPhone(bAuthor.getPhone());
		mAuthor.setBirthday(DateUtil.convertStringtoDate(bAuthor.getBirthday()));
		mAuthor.setAddress(bAuthor.getAddress());

		return mAuthor;
	}

	// Table Book Detail
	public static BookDetailInfo converBookDetailModelToBean(BookDetail mBookDetail) {

		BookDetailInfo bBookDetail = new BookDetailInfo();
		bBookDetail.setBookDetailId(mBookDetail.getBookDetailId());
		bBookDetail.setAuthor(converAuthorModelToBean(mBookDetail.getAuthor()));

		return bBookDetail;
	}

	// Table Book
	public static BookInfo converBookModelToBean(Book mBook) {

		BookInfo bBook = new BookInfo();
		bBook.setBookId(mBook.getBookId());
		bBook.setCategoriesName(mBook.getCategories().getName());
		bBook.setPublishersName(mBook.getPublishers().getPublishersName());
		bBook.setCategoriesId(mBook.getCategories().getcategoriesId());

		bBook.setPublishersId(mBook.getPublishers().getPublishersId());
		bBook.setBookCode(mBook.getBookCode());
		bBook.setName(mBook.getName());
		bBook.setPrice(mBook.getPrice());
		bBook.setStatusBook(book_status_good);
		if (ConstantModel.BOO_STATUS_BAD.equals(mBook.getStatusBook())) {
			bBook.setStatusBook(book_status_bad);
		}
		bBook.setNumberBook(mBook.getNumberBook());
		bBook.setNumberBorrowed(mBook.getNumberBorrowed());
		bBook.setNumberRest(mBook.getNumberRest());
		bBook.setNumberPage(mBook.getNumberPage());
		bBook.setDeleteFlag(mBook.getDeleteFlag());
		if (null != mBook.getDateCreate()) {
			bBook.setDateCreate(mBook.getDateCreate().toString().substring(0, 19));
		}
		bBook.setUserCreate(mBook.getUserCreate());
		if (null != mBook.getDateUpdate()) {
			bBook.setDateUpdate(mBook.getDateUpdate().toString().substring(0, 19));
		}
		bBook.setUserUpdate(mBook.getUserUpdate());

		return bBook;
	}

	public static Book converBookBeanToModel(BookInfo bBook) {
		Book mBook = new Book();
		mBook.setBookId(bBook.getBookId());
		mBook.setBookCode(bBook.getBookCode());
		mBook.setName(bBook.getName());
		mBook.setPrice(bBook.getPrice());
		mBook.setStatusBook(bBook.getStatusBook());
		mBook.setNumberBook(bBook.getNumberBook());
		mBook.setNumberBorrowed(bBook.getNumberBorrowed());
		mBook.setNumberRest(bBook.getNumberRest());
		mBook.setNumberPage(bBook.getNumberPage());
		mBook.setDeleteFlag(bBook.getDeleteFlag());
		mBook.setUserUpdate(bBook.getUserUpdate());
		mBook.setDateUpdate(DateUtil.convertStringtoDateTime(bBook.getDateUpdate()));
		mBook.setDateCreate(DateUtil.convertStringtoDateTime(bBook.getDateCreate()));
		mBook.setDeleteFlag(bBook.getDeleteFlag());
		mBook.setUserCreate(bBook.getUserCreate());

		return mBook;
	}

	// Table Borrowed book detail
	public static BorrowedDetailInfo converBorrowedDetailModelToBean(BorrowedDetails mBorDet) {
		BorrowedDetailInfo bBorDet = new BorrowedDetailInfo();

		bBorDet.setBorrowedDetailId(mBorDet.getBorrowedDetailId());
		bBorDet.setBook(converBookModelToBean(mBorDet.getBook()));
		bBorDet.setStatus(borr_detail_status_wait);
		if (ConstantModel.BOR_DET_STATUS_ACCEPT.equals(mBorDet.getStatus())) {
			bBorDet.setStatus(borr_detail_status_accept);
		} else if (ConstantModel.BOR_DET_STATUS_REJECT.equals(mBorDet.getStatus())) {
			bBorDet.setStatus(borr_detail_status_accept);
		}
		bBorDet.setDeleteFlag(mBorDet.getDeleteFlag());

		if (null != mBorDet.getDateCreate()) {
			bBorDet.setDateCreate(mBorDet.getDateCreate().toString().substring(0, 19));
		}
		bBorDet.setUserCreate(mBorDet.getUserCreate());
		if (null != mBorDet.getDateUpdate()) {
			bBorDet.setDateUpdate(mBorDet.getDateUpdate().toString().substring(0, 19));
		}
		bBorDet.setUserUpdate(mBorDet.getUserUpdate());

		return bBorDet;
	}

	public static BorrowedDetails converBorrowedDetailBeanToModel(BorrowedDetailInfo bBorDet) {
		BorrowedDetails mBorDet = new BorrowedDetails();

		mBorDet.setBorrowedDetailId(bBorDet.getBorrowedDetailId());
		mBorDet.setStatus(bBorDet.getStatus());
		mBorDet.setBook(converBookBeanToModel(bBorDet.getBook()));
		mBorDet.setUserUpdate(bBorDet.getUserUpdate());
		mBorDet.setDateUpdate(DateUtil.convertStringtoDateTime(bBorDet.getDateUpdate()));
		mBorDet.setDateCreate(DateUtil.convertStringtoDateTime(bBorDet.getDateCreate()));
		mBorDet.setDeleteFlag(bBorDet.getDeleteFlag());
		mBorDet.setUserCreate(bBorDet.getUserCreate());

		return mBorDet;
	}

	// Table Borrowed book
	public static BorrowedInfo converBorrowedModelToBean(Borroweds mBor) {
		BorrowedInfo bBor = new BorrowedInfo();
		if (null != mBor.getBorrowedId()) {
			bBor.setBorrowedId(mBor.getBorrowedId());
		}
		bBor.setUserInfo(converUserModelToBean(mBor.getUser()));
		bBor.setBorrowedCode(mBor.getBorrowedCode());

		if (null != mBor.getDIntendBorrowed()) {
			bBor.setdIntendBorrowed(mBor.getDIntendBorrowed().toString());
		}

		if (null != mBor.getDIntendArrived()) {
			bBor.setdIntendArrived(mBor.getDIntendArrived().toString());
		}

		if (null != mBor.getDateBorrrowed()) {
			bBor.setDateBorrrowed(mBor.getDateBorrrowed().toString());
		}
		if (null != mBor.getDateArrived()) {
			bBor.setDateArrived(mBor.getDateArrived().toString());
		}

		switch (mBor.getStatus()) {
		case ConstantModel.BOR_STATUS_REQUEST:
			bBor.setStatus(borr_status_request);
			break;
		case ConstantModel.BOR_STATUS_APPROVE:
			bBor.setStatus(borr_status_approve);
			break;
		case ConstantModel.BOR_STATUS_CANCEL:
			bBor.setStatus(borr_status_cancel);
			break;
		case ConstantModel.BOR_STATUS_BORRWED:
			bBor.setStatus(borr_status_borrowed);
			break;
		default:
			bBor.setStatus(borr_status_finish);
			break;
		}
		bBor.setDeleteFlag(mBor.getDeleteFlag());

		if (null != mBor.getDateCreate()) {
			bBor.setDateCreate(mBor.getDateCreate().toString().substring(0, 19));
		}
		bBor.setUserCreate(mBor.getUserCreate());
		if (null != mBor.getDateUpdate()) {
			bBor.setDateUpdate(mBor.getDateUpdate().toString().substring(0, 19));
		}
		bBor.setUserUpdate(mBor.getUserUpdate());

		List<BorrowedDetailInfo> borrowedDetail = new ArrayList<BorrowedDetailInfo>();
		for (BorrowedDetails item : mBor.getBorrowedDetails()) {
			BorrowedDetailInfo bBorDet = new BorrowedDetailInfo();
			bBorDet = converBorrowedDetailModelToBean(item);
			borrowedDetail.add(bBorDet);
		}
		bBor.setBorrowedDetail(borrowedDetail);

		return bBor;
	}

	public static Borroweds converBorrowedModelToBean(BorrowedInfo bBor) {
		Borroweds mBor = new Borroweds();

		mBor.setBorrowedId(bBor.getBorrowedId());
		mBor.setUser(converUserBeanToModel(bBor.getUserInfo()));
		mBor.setBorrowedCode(bBor.getBorrowedCode());
		mBor.setDIntendBorrowed(DateUtil.convertStringtoDate(bBor.getdIntendBorrowed()));
		mBor.setDIntendArrived(DateUtil.convertStringtoDate(bBor.getdIntendArrived()));
		mBor.setDateBorrrowed(DateUtil.convertStringtoDate(bBor.getDateBorrrowed()));
		mBor.setDateArrived(DateUtil.convertStringtoDate(bBor.getDateArrived()));
		mBor.setStatus(bBor.getStatus());
		mBor.setDeleteFlag(mBor.getDeleteFlag());
		mBor.setUserCreate(bBor.getUserCreate());
		mBor.setUserUpdate(bBor.getUserUpdate());
		mBor.setDateUpdate(DateUtil.convertStringtoDateTime(bBor.getDateUpdate()));
		mBor.setDateCreate(DateUtil.convertStringtoDateTime(bBor.getDateCreate()));

		List<BorrowedDetails> borrowedDetail = new ArrayList<BorrowedDetails>();
		for (BorrowedDetailInfo itemInfo : bBor.getBorrowedDetail()) {
			BorrowedDetails mItem = new BorrowedDetails();
			mItem = converBorrowedDetailBeanToModel(itemInfo);
			borrowedDetail.add(mItem);
		}
		Set<BorrowedDetails> mListBorDet = new HashSet<BorrowedDetails>(borrowedDetail);

		mBor.setBorrowedDetails(mListBorDet);

		return mBor;
	}
}
