package com.hiynn.project.model.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 
 * <p>Title: SimpleCORSFilter </p>
 * <p>Description: 解决web跨域问题  </p>
 * Date: 2017年7月13日 下午4:36:34
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月13日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class SimpleCORSFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp =(HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept");
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
