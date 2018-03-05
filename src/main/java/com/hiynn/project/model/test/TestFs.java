package com.hiynn.project.model.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * <p>Title: TestFs </p>
 * <p>Description: 反射 </p>
 * Date: 2018年1月22日 下午2:32:37
 * @author jzx@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2018年1月22日         jzx         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class TestFs {

	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("test.Demo");
			Method[] methods = clazz.getDeclaredMethods();//获取所有方法的集合
			for (int i = 0; i < methods.length; i++) {
				if ("dList".equals(methods[i].getName())) {
					Object o = methods[i].invoke(clazz.newInstance());//获取具体的方法
					
					System.err.println(o);
				}
				System.err.println("----------------------------------");
				if ("ccc".equals(methods[i].getName())) {
					Object o = methods[i].invoke(clazz.newInstance(),"qqq",999,true);
					System.err.println(o);
				}
			}
			
			Method method = clazz.getMethod("aaa");
			Object o = method.invoke(clazz.newInstance());
			System.err.println(o);
			
			Method method1 = clazz.getMethod("bbb",String.class);
			Object o1 = method1.invoke(clazz.newInstance(),"qweqwe");
			System.err.println(o1);
			
		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
