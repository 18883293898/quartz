package com.hiynn.project.model.util;

import com.google.gson.Gson;

/**
 * <p>Title: RestURIConstants </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月19日 下午2:28:43
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月19日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public final class RestURIConstants {
	
	public static final Gson YUNWEI_GSON = new Gson();
	/**
	 * <p>Title: LangChaoRestURIConstants </p>
	 * <p>Description: Constructor </p>  
	 */
	private RestURIConstants() {
	}
	
	
	/**
	 * 获取所有用户
	 */
	public static final String ALL_USERS = "users/all";
	
	/**
	 * 动态定时器首页
	 */
	public static final String QTZ_INDEX = "users/qtzindex";
	
	
	/**
	 * 动态定时器demo
	 */
	public static final String QUARTZ = "users/quartz/{time}";
	 
	
	
	
}
