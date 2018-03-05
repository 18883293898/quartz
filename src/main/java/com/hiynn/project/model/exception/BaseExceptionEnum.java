package com.hiynn.project.model.exception;


import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>Title: BaseExceptionEnum </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月13日 下午4:32:23
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月13日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public enum BaseExceptionEnum implements IbaseException {

    module_name(CommonExceptionEnum.class.getCanonicalName(), 1000);

    BaseExceptionEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    private String message;

    private Integer code;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getClassName() {
        return this.getClass().getCanonicalName();
    }

    private static Map<String, BaseExceptionEnum> map = new HashMap<String, BaseExceptionEnum>();

    static {
        for (BaseExceptionEnum c : BaseExceptionEnum.values()) {
            map.put(c.getMessage(), c);
        }
    }

    public static IbaseException getModuleContants(String clazz) {
        return map.get(clazz);
    }

}
