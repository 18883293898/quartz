package com.hiynn.project.model.util;

import org.aeonbits.owner.ConfigCache;

/**
 * <p>Title: ServerConfigUtil </p>
 * <p>Description: 获取系统配置 </p>
 * Date: 2017年7月13日 上午10:02:53
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月13日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public final class ServerConfigUtil {
	/**
	 * <p>Title: ServerConfigUtil </p>
	 * <p>Description: Constructor </p>
	 */
	private ServerConfigUtil() {
	}
	/**
	 * 
	 * <p>Title: getServerConf </p>
	 * <p>Description: 获取系统配置单例 </p>
	 * @return
	 */
	public static ServerConfig getServerConf (){
		return ConfigCache.getOrCreate(ServerConfig.class);
	}
	
}
