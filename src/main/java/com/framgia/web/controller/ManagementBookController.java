package com.framgia.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.users.bean.BookInfo;
import com.framgia.users.bean.CategoryInfo;
import com.framgia.users.service.ManagementBookService;
import com.framgia.util.DateUtil;

/**
 * ManagementBookController.java
 * 
 * @version 17/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Controller
public class ManagementBookController {

	// log
	private static final Logger logger = Logger.getLogger(ManagementBookController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	ManagementBookService managementBookService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = "/managementBook", method = RequestMethod.GET)
	public ModelAndView referencePage() {
		logger.info("call service: get list permission");

		// get value permission role for select box
		List<CategoryInfo> listCategory = managementBookService.findCategoryId();

		return new ModelAndView("managementBook", "listCategory", listCategory);
	}

	@RequestMapping(value = "/managementBook/search", method = RequestMethod.POST)
	public @ResponseBody List<BookInfo> findByCondition(
		@RequestParam(value = "name") String name,
		@RequestParam(value = "categoryId") String categoryId,
		ModelMap model) {
		logger.info("call service: get list book");

		// get value permission role for select box
		List<BookInfo> bookInfo = managementBookService.findByConditon(name, categoryId);

		return bookInfo;
	}

}
