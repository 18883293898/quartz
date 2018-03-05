package com.hiynn.project.model.cache;

import java.util.Date;
/**
 * <p>Title: CacheConfig </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月19日 下午6:01:16
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月19日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class CacheConfig implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long beginTime = new Date().getTime();// 缓存开始时间
	private boolean isForever = false;// 是否持久
	private Long durableTime;// 持续时间

	/**
	 * <p>Title: CacheConfModel </p>
	 * <p>Description: Constructor </p> 
	 * @param beginTime
	 * @param durableTime 分钟
	 * @param isForever
	 */
	public CacheConfig(int durableTime, boolean isForever) {
		this.isForever = isForever;
		this.durableTime = durableTime * 60 * 1000L;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public boolean isForever() {
		return isForever;
	}

	public Long getDurableTime() {
		return durableTime;
	}

}