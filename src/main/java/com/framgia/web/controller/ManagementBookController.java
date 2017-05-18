package com.framgia.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.users.bean.BookInfo;
import com.framgia.users.bean.CategoryInfo;
import com.framgia.users.service.ManagementBookService;
import com.framgia.util.Constant;
import com.framgia.util.CsvFileWriter;
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
	public @ResponseBody List<BookInfo> findByCondition(@RequestParam(value = "name") String name,
			@RequestParam(value = "categoryId") String categoryId, ModelMap model) {
		logger.info("call service: get list book");

		// get value permission role for select box
		List<BookInfo> bookInfo = managementBookService.findByConditon(name, categoryId);

		return bookInfo;
	}

	@RequestMapping(value = "/managementBook/downloadCSV", method = RequestMethod.POST)
	public void downloadCSV(HttpServletResponse response, @RequestParam(value = "name") String name,
			@RequestParam(value = "categoryId") String categoryId, ModelMap model) throws IOException {
		logger.info("call service: download csv with condition search");

		// get value
		List<BookInfo> bookInfo = managementBookService.findByConditon(name, categoryId);

		if (null != bookInfo) {
		String FILE_PATH = System.getProperty("java.io.tmpdir");
		String curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		String filePath = FILE_PATH + "/" + Constant.NAME_REPORT_BORROWED + curTime + Constant.EXTERNAL;

		// Write CSV
		try {
			CsvFileWriter.writeBookCsv(filePath, bookInfo);

		} catch (FileNotFoundException e) {
			logger.error("Error write csv: ", e);
		}

		File downloadFile = new File(filePath);

		response.setContentLength((int) downloadFile.length());

		// response header details
		String headerKey = "Content-Disposition";
		String fileName = URLEncoder.encode(downloadFile.getName(), "UTF-8");
		response.setHeader(headerKey, "attachment; filename*=UTF-8''" + fileName);

		// response
		OutputStream outStream = response.getOutputStream();

		CsvFileWriter.deleteFileInServer(downloadFile, outStream);
		}
	}

	@RequestMapping(value = "/managementBook/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String delLogicBook(@PathVariable("id") int bookId) {
		logger.info("call service: delete Book");

		return managementBookService.deleteBook(bookId, getUserName());
	}

	@RequestMapping(value = "/managementBook/detail/{id}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable("id") String idBook) {
		logger.info("call service: get user and get list permission");

		// get infor book
		BookInfo bookInfo = managementBookService.findBookId(idBook);

		// get value category for select box
		List<CategoryInfo> categoryInfo = managementBookService.findCategoryId();

		// render page detail category
		ModelAndView mv = new ModelAndView("managementBookDetail", "bookInfo", bookInfo);

		mv.addObject("categoryInfo", categoryInfo);

		return mv;
	}

	public String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			logger.info("username: " + userDetail.getUsername());

			return userDetail.getUsername();
		} else {
			return null;
		}
	}
}
