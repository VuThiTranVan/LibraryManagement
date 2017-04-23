package com.framgia.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.framgia.users.bean.Permissions;
import com.framgia.users.bean.Users;
import com.framgia.users.service.ManagementUsersService;

/**
 * ManagementUsersController.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Controller
public class ManagementUsersController {

	@Autowired
	ManagementUsersService managementUsersService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/managementUsers", method = RequestMethod.GET)
	public ModelAndView referencePage() {

		// get value permission role for select box
		List<com.framgia.users.model.Permissions> permissionList = managementUsersService.findByAllPermissions();

		List<Permissions> permissionInfo = new ArrayList<Permissions>();

		for (com.framgia.users.model.Permissions item : permissionList) {

			Permissions per = new Permissions();

			per.setPermissionsId(item.getPermissionsId());
			per.setPermissionName(item.getPermissionName());

			permissionInfo.add(per);
		}

		return new ModelAndView("managementUsers", "permissionInfo", permissionInfo);
	}

	@RequestMapping(value = "/managementUsers/search", method = RequestMethod.POST)
	public @ResponseBody List<Users> findByCondition(@RequestParam(value = "txtName") String txtName,
			@RequestParam(value = "txtPermission") String txtPermission, ModelMap model) {
		try {
			List<com.framgia.users.model.Users> userList = managementUsersService.findByUsersWithCondition(txtName,
					txtPermission);

			List<Users> userInfo = new ArrayList<Users>();

			for (com.framgia.users.model.Users item : userList) {

				Users user = new Users();

				user.setUserId(item.getUserId());
				user.setUserName(item.getUserName());
				user.setPassWord(item.getPassWord());
				user.setBirthDate(item.getBirthDate());
				user.setName(item.getName());
				user.setAddress(item.getAddress());

				user.setPhone(item.getPhone());
				user.setPassWord(item.getPassWord());

				user.setSex("Male");
				if (StringUtils.isNotBlank(item.getSex()) && item.getSex().equals("0")) {
					user.setSex("Fmale");
				}
				user.setEmail(item.getEmail());
				user.setDeleteFlag(item.getDeleteFlag());
				user.setDateCreate(item.getDateCreate());
				user.setUserCreate(item.getUserCreate());
				user.setDateUpdate(item.getDateUpdate());
				user.setUserUpdate(item.getUserUpdate());
				user.setPermissionsName(item.getPermissions().getPermissionName());
				userInfo.add(user);

			}
			return userInfo;
		} catch (Exception ce) {
			return null;
		}
	}

	@RequestMapping(value = "/managementUsers/detail/{id}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable("id") String idUser) {

		Users user = new Users();
		List<Permissions> permissionInfo = new ArrayList<Permissions>();
		try {
			com.framgia.users.model.Users userModel = managementUsersService.findByIdUser(idUser);

			if (null != userModel) {
				user.setUserId(userModel.getUserId());
				user.setUserName(userModel.getUserName());
				user.setPassWord(userModel.getPassWord());
				user.setBirthDate(userModel.getBirthDate());
				user.setName(userModel.getName());
				user.setAddress(userModel.getAddress());
				user.setPhone(userModel.getPhone());
				user.setPassWord(userModel.getPassWord());

				user.setSex("Male");
				if (StringUtils.isNotBlank(userModel.getSex()) && userModel.getSex().equals("0")) {
					user.setSex("Fmale");
				}
				user.setEmail(userModel.getEmail());
				user.setDeleteFlag(userModel.getDeleteFlag());
				user.setDateCreate(userModel.getDateCreate());
				user.setUserCreate(userModel.getUserCreate());
				user.setDateUpdate(userModel.getDateUpdate());
				user.setUserUpdate(userModel.getUserUpdate());
				user.setPermissionsName(userModel.getPermissions().getPermissionName());
			}

			// get value permission role for select box
			List<com.framgia.users.model.Permissions> permissionList = managementUsersService.findByAllPermissions();

			for (com.framgia.users.model.Permissions item : permissionList) {

				Permissions per = new Permissions();

				per.setPermissionsId(item.getPermissionsId());
				per.setPermissionName(item.getPermissionName());

				permissionInfo.add(per);
			}

		} catch (Exception ce) {
		}

		// render page detail user
		ModelAndView mv = new ModelAndView("managementUserDetail", "user", user);

		mv.addObject("permissionInfo", permissionInfo);

		return mv;
	}

}
