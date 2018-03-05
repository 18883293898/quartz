package com.hiynn.project.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hiynn.project.model.entity.Result;
import com.hiynn.project.model.exception.CommonException;
import com.hiynn.project.model.exception.CommonExceptionEnum;

/**
 * 
 * <p>Title: GlobalExceptionHandler </p>
 * <p>Description: controller层统一异常处理 </p>
 * Date: 2017年7月13日 下午4:33:36
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月13日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
@ControllerAdvice(basePackages = "com.hiynn.shared.eden.controller")
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public @ResponseBody Result handleException(Exception ex) {
		LOGGER.error("异常发生", ex);
		return (ex instanceof CommonException) ? Result.fail(((CommonException) ex).getExceptionConstant())
				: Result.fail(CommonExceptionEnum.sys_err);
	}
}
