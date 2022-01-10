package com.sample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sample.annotation.LoginedUser;
import com.sample.util.SessionUtils;
import com.sample.vo.User;

public class RestLoginCheckInterceptor implements HandlerInterceptor {

	private static Logger logger = LogManager.getLogger(RestLoginCheckInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
		
		boolean hasLogineUser = false;
		for (MethodParameter methodParameter : methodParameters) {
			if (methodParameter.hasParameterAnnotation(LoginedUser.class)) {
				hasLogineUser = true;
				break;
			}
		}
		logger.info("로그인 체크 여부: " + hasLogineUser);
		
		if (!hasLogineUser) {
			return true;
		}
		
		User user = (User) SessionUtils.getAttribute("LOGIN_USER");
		if (user == null) {
			response.sendRedirect("/error/login/rest.do");
			return false;
		}
		
		return true;
		
	}
}
