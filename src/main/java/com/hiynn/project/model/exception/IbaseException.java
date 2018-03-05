package com.hiynn.project.model.exception;

import java.io.Serializable;

/**
 * 
 * <p>Title: IbaseException </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月13日 下午4:32:01
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月13日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public interface IbaseException extends Serializable {
    //获取异常码
    Integer getCode();

    //获取异常信息
    String getMessage();

    //获取类引用路径
    String getClassName();
}
