package com.hiynn.project.model.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * <p>Title: Demo </p>
 * <p>Description: 用来做反射的demo类 </p>
 * Date: 2018年1月22日 下午2:32:48
 * @author jzx@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2018年1月22日         jzx         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class Demo {
	
	public List<Map<String,Object>> dList(){
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map1 = new HashMap<>();
		Map<String,Object> map2 = new HashMap<>();
		map1.put("aaa", "map1");
		map2.put("bbb", "map2");
		list.add(map1);
		list.add(map2);
		return list;
	}

	public String aaa(){
		return "aaaaaaaa";
	}
	
	public String bbb(String s){
		return "bbbbb======"+s;
	}
	
	public String ccc(String s,int i,boolean b){
		return "cccc     sssss="+s+"         iiiiii="+"      bbbbb="+b;
	}
}
