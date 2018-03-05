package com.hiynn.project.model.cache;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/** 
 * <p>Title: MyCacheManager </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月19日 下午6:00:57
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月19日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class MyCacheManager {

	private static Map<String, Object> cacheMap = new ConcurrentHashMap<>();
	private static Map<String, Object> cacheConfMap = new ConcurrentHashMap<>();
	private static final MyCacheManager cm = new MyCacheManager();
	private static Thread t;
	
	// 私有化构造方法
	private MyCacheManager() {
	}

	public static MyCacheManager getInstance() {
		if (null == t) {
			t = new ClearCache();
			t.start();
		}
		return cm;
	}

	/** 
	 * <p>Title: addCache </p>
	 * <p>Description: 添加缓存 </p>
	 * @param key
	 * @param value
	 * @param minutes 生存时间(分钟),0表示永久
	 * @return
	 */
	public static boolean addCache(String key, Object value, int minutes) {
		CacheConfig ccm;
		boolean flag = false;
		if (0 == minutes) {
			ccm = new CacheConfig(0, true);
		} else {
			ccm = new CacheConfig(minutes, false);
		}
		try {
			cacheMap.put(key, value);
			cacheConfMap.put(key, ccm);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/** 
	 * <p>Title: getValue </p>
	 * <p>Description: 获取缓存 </p>
	 * @param key
	 * @return
	 */
	public static Object getValue(String key) {
		Object ob = cacheMap.get(key);
		if (ob != null) {
			return ob;
		} else {
			return null;
		}
	}

	/** 
	 * <p>Title: getSize </p>
	 * <p>Description: 已缓存的数量 </p>
	 * @return
	 */
	public static int getSize() {
		return cacheMap.size();
	}

	/** 
	 * <p>Title: removeCache </p>
	 * <p>Description: 删除缓存 </p>
	 * @param key
	 * @return
	 */
	public static boolean removeCache(Object key) {
		boolean flag = false;
		try {
			cacheMap.remove(key);
			cacheConfMap.remove(key);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/** 
	 * <p>Title: ClearCache </p>
	 * <p>Description: 清除缓存的线程 </p>
	 * Date: 2017年6月9日 下午3:23:18
	 * @author hy@hiynn.com
	 * @version 1.0 </p> 
	 * Significant Modify：
	 * Date               Author           Content
	 * ==========================================================
	 * 2017年6月9日    		     hy         创建文件,实现基本功能
	 * 
	 * ==========================================================
	 */
	private static class ClearCache extends Thread {
		public void run() {
			while (true) {
				Set<Object> tempSet = new HashSet<Object>();
				Set<String> set = cacheConfMap.keySet();
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					Object key = it.next();
					CacheConfig ccm = (CacheConfig) cacheConfMap.get(key);
					// 比较是否需要清除
					if (!ccm.isForever()) {
						if ((new Date().getTime() - ccm.getBeginTime()) >= ccm.getDurableTime() * 60 * 1000) {
							// 可以清除，先记录下来
							tempSet.add(key);
						}
					}
				}
				// 真正清除
				Iterator<Object> tempIt = tempSet.iterator();
				while (tempIt.hasNext()) {
					Object key = tempIt.next();
					cacheMap.remove(key);
					cacheConfMap.remove(key);

				}
				// 休息
				try {
					Thread.sleep(60 * 1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}