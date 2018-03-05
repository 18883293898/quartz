package com.hiynn.project.model.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisAdvisor implements MethodInterceptor {
	
	private static final Logger CAT = LoggerFactory
			.getLogger(RedisAdvisor.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String flag = getMethodString(invocation);
		long start = System.currentTimeMillis();
		try{
			Object result = invocation.proceed();
			long cost =  System.currentTimeMillis() - start;
			StringBuilder sb = new StringBuilder();
            sb.append("Executed " + flag + " [ timeCost -> " + cost + " ms , result(size) -> ");
            if (result instanceof List) {
                sb.append(((List) result).size());
            } else if (result instanceof Map) {
            	sb.append(((Map) result).size());
            } else {
                sb.append(result);
            }
            sb.append(" ]");
            CAT.info(sb.toString());
			return result;
	    }catch(Exception e){
	    	StringBuilder sb = new StringBuilder();
            sb.append("Executed " + flag + " [ timeCost -> " + (System.currentTimeMillis() - start) + " ms ] ");
            sb.append(e.getClass().getName() + ",");
            sb.append(e.toString());
            CAT.error(sb.toString());
	    	throw e;
	    }
		
	}
	
	/**
     * GetMethodString: 获取MethodInvocation的输出文本
     *
     * @param invocation
     * @return 输出文本
     */
    private static String getMethodString(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        StringBuffer sb = new StringBuffer();
        sb.append(method.getDeclaringClass().getSimpleName());
        sb.append("@[");
        sb.append(method.getReturnType().getSimpleName()).append(" ");
        sb.append(method.getName());
        sb.append("(");
        @SuppressWarnings("rawtypes")
        Class[] params = method.getParameterTypes();
        for (int j = 0; j < params.length; j++) {
            // TODO 参数是泛型是，无法区分两个方法，后续补充
            sb.append(params[j].getSimpleName());
            if (j < (params.length - 1)) {
                sb.append(",");
            }
        }
        sb.append(")]");
        return sb.toString();
    }

}
