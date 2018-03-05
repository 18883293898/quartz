package com.hiynn.project.model.service;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hiynn.project.model.util.Constants;

/**
 * <p>Title: LdrkZhuYeSubMapperTest </p>
 * <p>Description: TODO </p>
 * Date: 2016年8月15日 上午11:18:42
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2016年8月15日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml"})
public class UserServiceTest2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest2.class);

	
	
	@Test
	public void test() throws InterruptedException{

		Thread.sleep(10000);
	}
	
	

}
