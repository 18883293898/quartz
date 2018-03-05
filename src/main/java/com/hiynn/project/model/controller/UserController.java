package com.hiynn.project.model.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hiynn.project.model.service.UserService;
import com.hiynn.project.model.util.RestURIConstants;

/**
 * 
 * <p>Title: UserController </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月19日 下午2:20:31
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月19日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * <p>Title: getAllUsers </p>
	 * <p>Description: 获取所有用户 </p>
	 * @param session
	 * @return
	 */
	@RequestMapping(value = RestURIConstants.ALL_USERS, method = RequestMethod.GET)
	public Map<String, Object> getAllUsers() {
		return userService.getAllUsers();
	}
	
	/**
	 * 
	 * <p>Title: quartz </p>
	 * <p>Description: 动态定时器demo </p>
	 * @return
	 */
	@RequestMapping(value = RestURIConstants.QUARTZ, method = RequestMethod.GET)
	public ModelAndView quartz(@PathVariable("time")String time) {
		ModelAndView modelAndView = new ModelAndView("quartz");
		userService.quartz(time);
		return modelAndView;
	}
	
	/**
	 * 
	 * <p>Title: qtzindex </p>
	 * <p>Description: 动态定时器首页 </p>
	 * @return
	 */
	@RequestMapping(value = RestURIConstants.QTZ_INDEX, method = RequestMethod.GET)
	public ModelAndView qtzindex() {
		ModelAndView modelAndView = new ModelAndView("quartz");
		return modelAndView;
	}

}
