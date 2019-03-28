package com.tstore.freemarker;

import javax.servlet.http.HttpServletRequest;

/***
 * 
 * 
 * RequestUtil<br/>
 * 创建人:xuchengfeifei<br/>
 * 时间：2019年3月11日-上午11:51:41 <br/>
 * @version 1.0.0<br/>
 *
 */
public class RequestUtil {

	public static String getRequestIP(HttpServletRequest request) {
		String clientIp = request.getHeader("x-forwarded-for");
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}
		return clientIp;
	}

	public static boolean isResponseBody(HttpServletRequest request) {
		if (request.getRequestURI().indexOf("ajax") > -1) {
			return true;
		}
		return false;
	}

	/**
	 * 得到请求的根目录
	 *
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath  = null;
		if(request.getServerPort()==80) {
			basePath = request.getScheme() + "://" + request.getServerName() + path;
		}else {
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		}
		return basePath;
	}

	/**
	 * 得到结构目录
	 *
	 * @param request
	 * @return
	 */
	public static String getContextPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return path;
	}

}
