package com.hiynn.project.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiynn.project.model.dao.UserMapper;
import com.hiynn.project.model.quartzJob.CronDateUtils;
import com.hiynn.project.model.quartzJob.QuartzJob;
import com.hiynn.project.model.quartzJob.QuartzManager;
import com.hiynn.project.model.util.Constants;

/**
 * <p>Title: UserService </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月19日 下午2:13:01
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月19日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * <p>Title: getAllUsers </p>
	 * <p>Description: 获取所有用户 </p>
	 * @return
	 */
	public Map<String, Object> getAllUsers(){
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			
			List<Map<String,Object>> users = userMapper.getUsers();
			
			response.put("code", Constants.CODE_SUCCESS);
			response.put("msg", Constants.MSG_SUCCESS);
			response.put("result", users);
		} catch (Exception e) {
			LOGGER.error("获取所有用户 异常", e);
			response.put("code", Constants.CODE_FALSE);
			response.put("msg", Constants.MSG_FALSE);
		}
		LOGGER.debug("UserController.getAllUsers() response is  :  " + Constants.COMM_GSON.toJson(response));
		return response;
	}
	
	/**
	 * 
	 * <p>Title: quartz </p>
	 * <p>Description: 动态定时业务逻辑 </p>
	 * @param time
	 * @return
	 */
	public Map<String, Object> quartz(String time){
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			
			int int_time = Integer.valueOf(time);
			//计算时间戳
			long log_time = new Date().getTime();
			log_time = log_time+int_time*1000;
			//转换回date类型
			 Date date=new Date(log_time);
			 String job_name = "动态任务调度";
		      System.out.println("【系统启动】开始(每5秒输出一次)...");  
		      QuartzManager.addJob(job_name, QuartzJob.class,"*/3 * * * * ?");
		      //启动所有定时任务
		      QuartzManager.startJobs();
			response.put("code", Constants.CODE_SUCCESS);
			response.put("msg", Constants.MSG_SUCCESS);
			response.put("result", userMapper.getUsers());
		} catch (Exception e) {
			LOGGER.error("获取所有用户 异常", e);
			response.put("code", Constants.CODE_FALSE);
			response.put("msg", Constants.MSG_FALSE);
		}
		LOGGER.debug("UserController.quartz() response is  :  " + Constants.COMM_GSON.toJson(response));
		return response;
	}

	private SimpleDateFormat newSimpleDateFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
