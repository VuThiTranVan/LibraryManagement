package com.framgia.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.framgia.users.model.Categories;
import com.framgia.users.model.ConstantModel;

/**
 * CategoryDAOImpl.java
 * 
 * @version 19/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Repository("categoryDAO")
public class CategoryDAOImpl extends AbstractDao<Integer, Categories> implements ConstantModel, CategoryDAO {

	// log
	private static final Logger logger = Logger.getLogger(CategoryDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Categories findCategoryId(String categoryId) {
		List<Categories> categories = new ArrayList<Categories>();

		categories = getOpenSession().createQuery("from Categories where categoriesId=:categoriesId and deleteFlag=:delFlg")
				.setParameter("categoriesId", Integer.parseInt(categoryId))
				.setParameter("delFlg", ConstantModel.DEL_FLG).list();

		if (categories.size() > 0) {

			return categories.get(0);
		} else {

			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Categories findCategoryCode(String categoryCode) {
		List<Categories> categories = new ArrayList<Categories>();

		categories = getOpenSession().createQuery("from Categories where categoriesCode=:categoriesCode and deleteFlag=:delFlg")
				.setParameter("categoriesCode", categoryCode)
				.setParameter("delFlg", ConstantModel.DEL_FLG).list();

		if (categories.size() > 0) {

			return categories.get(0);
		} else {

			return null;
		}
	}
	
	@Override
	public void insertCategory(Categories categories) {
		
		// Insert data into table Books
		getOpenSession().saveOrUpdate(categories);
		logger.info("Insert success.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categories> listCategory() {
		List<Categories> categoryList = getOpenSession().createQuery("from Categories where deleteFlag = :deleteFlag").
				setParameter("deleteFlag", ConstantModel.DEL_FLG)
				.list();
		return categoryList;
	}
}
