/**
 * 
 */
package com.hiynn.project.model.schedule;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiynn.project.model.cache.MyCacheManager;
import com.hiynn.project.model.service.UserService;
import com.hiynn.project.model.util.Constants;

/**
 * <p>Title: ScheduleTask </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月19日 下午6:18:26
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月19日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class ScheduleTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("static-access")
	public void syncAllUsers(){
		//3分钟同步一次
		Map<String, Object> result;
		int code;  
		
		result = userService.getAllUsers();
		code = (int) result.get("code");
		
		if (Constants.CODE_FALSE == code) {
			LOGGER.error("用户查询发生异常，不会添加到缓存");
			result = null;
		}else if (Constants.CODE_SUCCESS == code) {
			MyCacheManager.getInstance().addCache("allUsers", result, 0);
			LOGGER.info("ScheduleTask>>syncAllUsers  添加缓存成功");
		}
	}

}
