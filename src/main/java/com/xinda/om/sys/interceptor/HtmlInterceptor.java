package com.xinda.om.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * html文件映射拦截器.
 * 
 * @author coudy
 *
 *         2017年2月25日
 */
public class HtmlInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);

	private static final String DEFAULT_VIEW = "/login.html";

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 在业务处理器处理请求之前被调用.
	 * 
	 * 如果返回false从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true,执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller ,然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		logger.info("Request Uri : [{}]", request.getRequestURI());
		HttpSession session = request.getSession(false);
		if (session == null) {
			String contextPath = request.getContextPath();
			String path = request.getRequestURI().substring(contextPath.length());
			if ("".equals(path) || "/".equals(path)) {
				request.getRequestDispatcher("/login").forward(request, response);
			} else {
				response.sendRedirect(contextPath + DEFAULT_VIEW);
			}
			return false;
		}
		return true;
	}

}
