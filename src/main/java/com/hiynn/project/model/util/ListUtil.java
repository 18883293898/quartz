package com.hiynn.project.model.util;

import java.util.List;

public class ListUtil {
	/**
	 * 列表非null非空返回true，其他返回false
	 * 
	 * @param list
	 * @return
	 */
	public static <T> boolean isNotEmptyList(List<T> list) {
		return null != list && !list.isEmpty();
	}
	/**
	 * 列表null或者空返回true，其他返回false
	 * @param list
	 * @return
	 */
	public static <T> boolean isEmptyList(List<T> list) {
		return null == list || list.isEmpty();
	}
}
