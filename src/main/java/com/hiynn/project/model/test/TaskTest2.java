package com.hiynn.project.model.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>Title: TaskTest2 </p>
 * <p>Description: 注解式定时器 </p>
 * Date: 2018年1月17日 下午5:03:18
 * @author jzx@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2018年1月17日         jzx         创建文件,实现基本功能
 * 
 * ==========================================================
 */
@Component
public class TaskTest2 {
	@Scheduled(cron="*/5 * * * * ?")
	public void task2(){
		System.err.println("每隔5秒小熊好帅！！！");
	}
}
