package com.framgia.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.users.bean.BorrowedInfo;
import com.framgia.users.bean.UserInfo;
import com.framgia.users.service.ManagementBorrowedBookService;
import com.framgia.util.ConditionSearchBorrowed;
import com.framgia.util.DateUtil;

/**
 * ManagementUsersController.java
 * 
 * @version 03/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Controller
public class ManagementBorowedBookController {

	@Autowired
	ManagementBorrowedBookService managementBorrowedBookService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = "/managementBorrowed", method = RequestMethod.GET)
	public ModelAndView referencePage() {

		// return new ModelAndView("managementBorrowed", "borrowedInfoList",
		// borrowedInfoList);
		return new ModelAndView("managementBorrowed");
	}

	@RequestMapping(value = "/managementBorrowed/search", method = RequestMethod.POST)
	public @ResponseBody List<BorrowedInfo> findByCondition(
			@RequestParam(value = "txtBorrowedCode") String txtBorrowedCode,
			@RequestParam(value = "txtStatus") String txtStatus, 
			@RequestParam(value = "txtIntenDateBor") String txtIntenDateBor,
			@RequestParam(value = "txtIntenDatePay") String txtIntenDatePay, 
			@RequestParam(value = "txtDateBor") String txtDateBor,
			@RequestParam(value = "txtDatePay") String txtDatePay,
			ModelMap model) {
		ConditionSearchBorrowed condition = new ConditionSearchBorrowed(txtBorrowedCode,
				txtStatus, txtIntenDateBor, txtIntenDatePay, txtDateBor, txtDatePay);
		// get value permission role for select box
		List<BorrowedInfo> borrowedInfo = managementBorrowedBookService.getBorrowedInfoByFindCondition(condition);

		return borrowedInfo;
	}

}
