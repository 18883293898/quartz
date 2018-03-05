package com.hiynn.project.model.exception;


/**
 * 
 * <p>Title: CommonExceptionEnum </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月13日 下午4:32:31
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月13日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public enum CommonExceptionEnum implements IbaseException {
    //异常信息描述
    sys_err(1001, "系统错误"),
    param_err(1002,"参数错误"),
    sql_no_has(1003, "执行sql记录不存在"),
    method_params_value_empty(1004, "参数值不能为空"),
    data_no_exist(1005, "需要查询的数据不存在"),
	str_is_null(1006,"字符串为空"),
	str_exception(1007,"字符串异常"),
    login_err(1008,"登录失败"),
    user_no_exesit(1009,"用户未登录")
    ;
    private Integer code;
    private String message;

    CommonExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
