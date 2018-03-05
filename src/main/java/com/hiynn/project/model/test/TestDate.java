package com.hiynn.project.model.test;


import com.hiynn.util.time.Time;

/**
 * 
 * <p>Title: TestDate </p>
 * <p>Description: 使用公司自带计算时间的包 </p>
 * Date: 2018年1月22日 下午2:32:11
 * @author jzx@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2018年1月22日         jzx         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class TestDate {

	public static void main(String[] args) {
		String time = Time.getCurrentDayEndTime("yyyy-MM-dd HH:mm:ss");
		System.out.println(time);
	}

}
