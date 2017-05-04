package com.framgia.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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

import com.framgia.users.bean.BorrowedInfo;
import com.framgia.users.service.ManagementBorrowedBookService;
import com.framgia.util.ConditionSearchBorrowed;
import com.framgia.util.Constant;
import com.framgia.util.CsvFileWriter;
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
	MessageSource messageSource;

	@Autowired
	ManagementBorrowedBookService managementBorrowedBookService;

	// File input stream
	private FileInputStream inputStream;

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
			@RequestParam(value = "txtDatePay") String txtDatePay, ModelMap model) {
		ConditionSearchBorrowed condition = new ConditionSearchBorrowed(txtBorrowedCode, txtStatus, txtIntenDateBor,
				txtIntenDatePay, txtDateBor, txtDatePay);

		// get value permission role for select box
		List<BorrowedInfo> borrowedInfo = managementBorrowedBookService.getBorrowedInfoByFindCondition(condition);

		return borrowedInfo;
	}


	@RequestMapping(value = "managementBorrowed/downloadCSV", method = RequestMethod.POST)
	public void downloadCondition(HttpServletResponse response,
			@RequestParam(value = "txtBorrowedCode") String txtBorrowedCode,
			@RequestParam(value = "txtStatus") String txtStatus,
			@RequestParam(value = "txtIntenDateBor") String txtIntenDateBor,
			@RequestParam(value = "txtIntenDatePay") String txtIntenDatePay,
			@RequestParam(value = "txtDateBor") String txtDateBor,
			@RequestParam(value = "txtDatePay") String txtDatePay, ModelMap model) throws IOException {

		ConditionSearchBorrowed condition = new ConditionSearchBorrowed(txtBorrowedCode, txtStatus, txtIntenDateBor,
				txtIntenDatePay, txtDateBor, txtDatePay);

		String FILE_PATH = System.getProperty("java.io.tmpdir");
		String curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		String filePath = FILE_PATH + "/" + Constant.NAME_REPORT_BORROWED + curTime + Constant.EXTERNAL;

		// get value
		List<BorrowedInfo> borrowedInfo = managementBorrowedBookService.getBorrowedInfoByFindCondition(condition);
		// Write CSV
		CsvFileWriter.writeBorrowedCsv(filePath, borrowedInfo);

		File downloadFile = new File(filePath);
		OutputStream outStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);

			response.setContentLength((int) downloadFile.length());

			// response header details
			String headerKey = "Content-Disposition";
			String fileName = URLEncoder.encode(downloadFile.getName(), "UTF-8");
			response.setHeader(headerKey, "attachment; filename*=UTF-8''" + fileName);

			// response
			outStream = response.getOutputStream();
			IOUtils.copy(inputStream, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream)
					inputStream.close();
				if (null != inputStream)
					outStream.close();

				// Delete file on server
				downloadFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}