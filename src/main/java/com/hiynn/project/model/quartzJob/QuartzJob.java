package com.hiynn.project.model.quartzJob;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.hiynn.project.model.dao.UserMapper;
import com.hiynn.project.model.util.Constants;

/**
 * 任务执行类
 * <p>Title: QuartzJob </p>
 * <p>Description: TODO </p>
 * Date: 2017年8月28日 下午9:30:55
 * @author hydata@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年8月28日         hydata         创建文件,实现基本功能
 * 
 * ==========================================================
 */

public class QuartzJob implements Job {
	
	@Autowired
	private UserMapper userMapper;
	
public void execute(JobExecutionContext arg0) throws JobExecutionException {
	//这个很重要  没有这句话无法注入
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	
	
	List<Map<String,Object>> users = userMapper.getUsers();
	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"    "+ Constants.COMM_GSON.toJson(users)); 
	
}
}
