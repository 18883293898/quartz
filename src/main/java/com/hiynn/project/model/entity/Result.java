package com.hiynn.project.model.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.hiynn.project.model.exception.BaseExceptionEnum;
import com.hiynn.project.model.exception.IbaseException;
import com.hiynn.project.model.util.Constants;

/**
 * 
 * <p>Title: Result </p>
 * <p>Description: TODO </p>
 * Date: 2016年11月9日 下午4:33:05
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2016年11月9日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -602413775193033896L;
	private int code;
	private String message;
	private Object result;

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	private Result() {
	}

	public static Result success() {
		Result result = new Result();
		result.code = Constants.CODE_SUCCESS;
		result.message = "调用成功";
		return result;
	}

	public static Result fail() {
		Result result = new Result();
		result.code = Constants.CODE_FALSE;
		return result;
	}

	public static Result fail(String msg) {
		Result result = new Result();
		result.message = msg;
		result.code = Constants.CODE_FALSE;
		return result;
	}

	public static Result fail(IbaseException exceptionConstant) {
		final Result result = Result.fail();
		String pre = BaseExceptionEnum.getModuleContants(exceptionConstant.getClassName()).getCode().toString();
		if (StringUtils.isNotBlank(pre)) {
			result.code = Constants.CODE_FALSE;
			result.message = exceptionConstant.getMessage();
		} else {
			// 为模块错误描述未注册
			result.message = "该异常模块还未注册到主异常信息描述模块中";
		}
		return result;
	}

	public Result build(Object result) {
		if (null != result) {
			this.result = result;
		} else {
			this.result = null;
		}
		return this;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Result{");
		sb.append(", 'code':'").append(code).append('\'');
		sb.append(", 'message':'").append(message).append('\'');
		sb.append(", 'result':").append(result);
		sb.append('}');
		return sb.toString();
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getResult() {
		return result;
	}
}
