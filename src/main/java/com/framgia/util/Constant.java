package com.framgia.util;

/**
 * Constant.java
 * 
 * @version 21/04/2017
 * @author phan.van.hieu@framgia.com
 */
public class Constant {

	// name file report screen management users
	public final static String NAME_REPORT_USER = "Download_Report_Users.csv";

	// type file report of screen management users
	public final static String TYPE_FILE_USER = "text/csv";

	// header of file report of screen management users
	public final static String HEADER_CSV_USER = "User id, Username, Permission, Full name, Email, Birthday, Address, Gender, Phone number, User create, Date create, User update, Date update";

	// Constant table Book
	public static final String TABLE_BOOK = "01";

	// Constant table BookDetails
	public static final String TABLE_BOOKDETAILS = "02";

	// Constant table Categories
	public static final String TABLE_CATEGORIES = "03";

	// Constant table Author
	public static final String TABLE_AUTHORS = "04";

	// Constant table Publishers
	public static final String TABLE_PUBLISHERS = "05";

	// Constant table Users
	public static final String TABLE_USERS = "06";

	// Constant table Permissions
	public static final String TABLE_PERMISSIONS = "07";

	// Constant error null
	public static final String ERROR_NULL = "cannot be NULL";

	// Column Book Code
	public static final String BOOK_CODE = "BookCode";

	// Column CategoriesID
	public static final String CATEGORIES_ID = "CategoriesID";

	// Column CategoriesSubID
	public static final String CATEGORIES_SUB_ID = "CategoriesSubID";

	// Column PublishersID
	public static final String PUBLISHERS_ID = "PublishersID";

	// Column Name
	public static final String NAME = "Name";

	// Column Price
	public static final String PRICE = "Price";

	// Column StatusBook
	public static final String STATUS_BOOK = "StatusBook";

	// Column NumberBorrowed
	public static final String NUMBER_BOOK = "NumberBook";

	// Column NumberPage
	public static final String NUMBER_PAGE = "NumberPage";

	// Name file insert table Books
	public static final String NAME_FILE_DATA_BOOK = "Books.csv";

	// Name file insert table BookDetail
	public static final String NAME_FILE_DATA_BOOK_DETAIL = "Bookdetails.csv";

	// Name file insert table Category
	public static final String NAME_FILE_DATA_CATEGORY = "Categories.csv";

	// Name file insert table Auhthor
	public static final String NAME_FILE_DATA_AUTHOR = "Authors.csv";

	// Name file insert table Publishers
	public static final String NAME_FILE_DATA_PUBLISHERS = "Publishers.csv";

	// Column error file
	public static final String ERROR_FILE = "The file is incorrect";

	// Column error file
	public static final String ERROR_FILE_FORMAT = "The file format is incorrect";

	// Column error file
	public static final String ERROR_FILE_EMPTY = "File % is data empty";

	// Column error file
	public static final String ERROR_LINE_FORMAT = "The line format is incorrect";

	// Error incorrect Integer
	public static final String ERROR_INTEGER = "Incorrect integer value";

	// Error incorrect Float
	public static final String ERROR_FLOAT = "Incorrect floar value";

	// Error incorrect Float
	public static final String ERROR_MAX_LENGTH = "Length must be less than % characters";

	// Error incorrect Float
	public static final String ERROR_DATE = "Incorrect date value";

	// Error incorrect Float
	public static final String ERROR_CHAR01 = "Values % different from 0 and 1";

	// DeleteFlag not delete
	public static final String NOT_DELETE = "0";

	// CategoryID not Exist in Table Categories
	public static final String ERROR_EXIST_KEY_CATEGORY_ID = "CategoryID not exist in table Categories";

	// BookID not Exist in Table BOOK
	public static final String ERROR_EXIST_KEY_BOOK_ID = "BookID not exist in table Books";

	// AuthorID not Exist in Table Author
	public static final String ERROR_EXIST_KEY_AUTHOR_ID = "AuthorID not exist in table Authors";

	// PublishersID not Exist in Table Publishers
	public static final String ERROR_EXIST_KEY_PUBLISHER_ID = "PublishersID not exist in table Publishers";

	// CatSubId not Exist in Table Categories
	public static final String ERROR_KEY_CAT_SUB_ID = "The CatSubId corresponding to the categoriesId in the Categories table was not found";

	// CatSubId not Exist in Table Categories
	public static final String ERROR_CATEGORY_CODE = "CategoriesCode exist in table Categories";

	// Format Date
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

	// Format Date
	public static final String CHARACTER = ",";

	// Column Book Code
	public static final String BOOK_ID = "BookID";

	// Column Book Code
	public static final String AUTHOR_ID = "AuthorsID";

	// Message import data success
	public static final String MSG_SUCCESS = "Import data into % success";

	// Message import data fail
	public static final String MSG_FAIL = "Import data into % fail";

	// Name table Books
	public static final String TB_BOOKS = "Books";

	// Name table BookDetails
	public static final String TB_BOOK_DETAILS = "BookDetails";

	// Name table Categories
	public static final String TB_BOOK_CATEGORY = "Categories";

	// Name table Authors
	public static final String TB_BOOK_AUHTHOR = "Authors";

	// Name table BookDetails
	public static final String TB_BOOK_PUBLISHERS = "Publishers";

	// Column CatSubID
	public static final String CAT_SUB_ID = "CatSubID";

	// Column CategoriesCode
	public static final String CATEGORIES_CODE = "CategoriesCode";

	// Column Name
	public static final String CATEGORY_NAME = "Name";

	// Column CatSubID
	public static final String AUTHORS_NAME = "AuthorsName";

	// Column CatSubID
	public static final String SEX = "Sex";

	// Column CatSubID
	public static final String EMAIL = "Email";

	// Column CatSubID
	public static final String DESCRIPTION = "Description";

	// Column CatSubID
	public static final String PHONE = "Phone";

	// Column CatSubID
	public static final String BIRTH_DAY = "Birthday";

	// Column CatSubID
	public static final String ADDRESS = "Address";

	// Constant deafault value 0
	public static final String DEFAULT_VALUE_0 = "0";

	// Constant deafault value 1
	public static final String DEFAULT_VALUE_1 = "1";

	// Column PublishersName
	public static final String PUBLISHERS_NAME = "PublishersName";

	// Constant replace msg
	public static final String DEFAULT_VALUE_MSG = "%";

	// Constant csv
	public static final String CONSTANT_CSV = "csv";
}