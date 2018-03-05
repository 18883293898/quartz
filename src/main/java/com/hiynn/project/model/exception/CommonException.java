package com.hiynn.project.model.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p>Title: CommonException </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月13日 下午4:32:15
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月13日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class CommonException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2632701912014954670L;
	private IbaseException exceptionConstant;

    public CommonException(IbaseException exceptionContants) {
        super(exceptionContants.getMessage(), null);
        String pre = BaseExceptionEnum.getModuleContants(exceptionContants.getClassName()).getCode().toString();
        if (!StringUtils.isBlank(pre)) {
            this.exceptionConstant=exceptionContants;
        } else {
            throw new CommonException(CommonExceptionEnum.sys_err);
        }
    }

    public IbaseException getExceptionConstant() {
        return this.exceptionConstant;
    }

}
