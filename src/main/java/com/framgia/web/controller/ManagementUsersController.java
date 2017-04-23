package com.framgia.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.users.bean.PermissionInfo;
import com.framgia.users.bean.UserInfo;
import com.framgia.users.service.ManagementUsersService;
import com.framgia.util.Constant;
import com.framgia.util.DateUtil;

/**
 * ManagementUsersController.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Controller
public class ManagementUsersController {
	
    @Autowired
    MessageSource messageSource;
    
	@Autowired
	ManagementUsersService managementUsersService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = "/managementUsers", method = RequestMethod.GET)
	public ModelAndView referencePage() {

		// get value permission role for select box
		List<PermissionInfo> permissionInfo = managementUsersService.findByDelFlg();

		return new ModelAndView("managementUsers", "permissionInfo", permissionInfo);
	}

	@RequestMapping(value = "/managementUsers/search", method = RequestMethod.POST)
	public @ResponseBody List<UserInfo> findByCondition(@RequestParam(value = "txtName") String txtName,
			@RequestParam(value = "txtPermission") int txtPermission, ModelMap model) {

		List<UserInfo> userInfo = managementUsersService.findByUsersWithCondition(txtName, txtPermission);

		return userInfo;
	}

	@RequestMapping(value = "/managementUsers/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public int delLogicUser(@PathVariable("id") int idUser, @RequestParam(value = "dateUpd") String dateUpd) {

		return managementUsersService.delLogicUser(idUser, getUserName(), dateUpd);

	}

	@RequestMapping(value = "/managementUsers/detail/{id}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable("id") int idUser) {

		// get infor user
		UserInfo user = managementUsersService.findByIdUser(idUser);

		// get value permission role for select box
		List<PermissionInfo> permissionInfo = managementUsersService.findByDelFlg();

		// render page detail user
		ModelAndView mv = new ModelAndView("managementUserDetail", "user", user);

		mv.addObject("permissionInfo", permissionInfo);

		return mv;
	}

	@RequestMapping(value = "/managementUsers/update", method = RequestMethod.POST)
	public @ResponseBody UserInfo updateUsser(@ModelAttribute("user") UserInfo user, BindingResult result,
			ModelMap model) {

		user.setUserUpdate(getUserName());
		UserInfo resultUpd = null;
		try {
			resultUpd = managementUsersService.updateUser(user);

		} catch (Exception e) {
		}

		return resultUpd;
	}

	@RequestMapping(value = "/managementUsers/downloadCSV", method = RequestMethod.POST)
	public void downloadCSV(HttpServletResponse response, @RequestParam(value = "txtName") String txtName,
			@RequestParam(value = "txtPermission") int txtPermission, ModelMap model) throws IOException {
		response.setContentType(Constant.TYPE_FILE_USER);
		String reportName = Constant.NAME_REPORT_USER;
		
		response.setHeader("Content-disposition", "attachment;filename=" + reportName);

		ArrayList<String> rows = new ArrayList<String>();
		rows.add(Constant.HEADER_CSV_USER);
		rows.add("\n");
		
		List<UserInfo> userInfo = managementUsersService.findByUsersWithCondition(txtName, txtPermission);
		
		if (userInfo.size() == 0) {
			rows.add(messageSource.getMessage("download_no_result", null, Locale.getDefault()));
		}
		
		for (UserInfo item : userInfo) {
			String dataRow = item.getUserId() + "," +
							item.getUserName() + "," +
							item.getPermissions().getPermissionName() + "," +
							item.getName() + "," +
							item.getEmail() + "," +
							item.getBirthDate() + "," +
							item.getAddress() + "," +
							item.getSex() + "," +
							item.getPhone() + "," +
							item.getUserCreate() + "," +
							item.getDateCreate() + "," +
							item.getUserUpdate() + "," +
							item.getDateUpdate();
			
			rows.add(dataRow);
			rows.add("\n");

		}

		Iterator<String> iter = rows.iterator();
		while (iter.hasNext()) {
			String outputString = (String) iter.next();
			response.getOutputStream().print(outputString);
		}

		response.getOutputStream().flush();

	}

	public String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return auth.getName();
	}

}
