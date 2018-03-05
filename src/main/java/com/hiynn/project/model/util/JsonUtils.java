package com.hiynn.project.model.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;



/**
 * Json工具类
 * ClassName: JsonUtils.java <br/>
 * Description: TODO <br/>
 * Date: 2015-3-30 <br/>
 * <br/>
 *
 * @author chenchunji
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 */
public class JsonUtils {

	protected static Gson gson = new Gson();

	/**
	 * 转换成String的List集合
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public static List<String> toJson(List list){
		if(list == null || list.size() == 0 ){
			throw new IllegalArgumentException();
		}
		List<String> listString = null;
		try{
			listString = new ArrayList<String>();
			for(Object o :list){
				listString.add(gson.toJson(o));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listString;
	}
    /**
     *
     * @param set
     * @return
     */
	@SuppressWarnings({ "rawtypes" })
	public static Set<String> toJson(Set set){
		if(set == null || set.size() == 0 ){
			throw new IllegalArgumentException();
		}
		Set<String> setString = null;
		try{
			setString = new HashSet<String>();
			for(Object o:set){
				if(o instanceof String) {
					setString.add((String)o);
				}else{
					setString.add(gson.toJson(o));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return setString;
	}
	/**
	 *
	 * @param o
	 * @return
	 */
	public static String toJson(Object o){
		if(o == null){
			throw new IllegalArgumentException();
		}
		//避免两个出现重复双引号
		if(o instanceof String) {
		    return (String)o;
		}
		String jsonString = null;
		try{
			jsonString =  gson.toJson(o);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonString;
	}

	/**
	 *
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
    public static Map<String,String> toJson(Map map){
			Map<String, String> map2 = new HashMap<String, String>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry entry = (Entry) iterator.next();
				String key = (String) entry.getKey();
				Object value = entry.getValue();
				map2.put(key, JsonUtils.toJson(value));
			}
			return map2;
	}

	/**
	 *
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object fromJson(String jsonString,Class clazz){
		if(jsonString == null){
			return null;
		}
		if(clazz.equals(String.class)) {
		    return jsonString;
		}
		Object o = null;
		try{
			o = gson.fromJson(jsonString, clazz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return o;
	}
    /**
     *
     * @param listString
     * @param clazz
     * @return
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List fromJson(List<String> listString,Class clazz){
		if(listString == null || listString.size() == 0){
			throw new IllegalArgumentException();
		}
		List list = new ArrayList();
		try{
			for(String jsonString :listString){
				list.add(gson.fromJson(jsonString, clazz));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
    /**
     *
     * @param setString
     * @param clazz
     * @return
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set fromJson(Set<String> setString,Class clazz){
		if(setString == null || setString.size() == 0){
			throw new IllegalArgumentException();
		}
		Set set = new HashSet();
		try{
			for(String jsonString :setString){
				set.add(gson.fromJson(jsonString, clazz));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return set;
	}
}
