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

/*
 * HandlerInterceptor
 * 		+ 컨트롤러의 요청핸들러 메소드 실행 전처리/후처리를 구현하는 객체다.
 * 		+ 주요 API
 * 			boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
 * 				- 컨트롤러의 요청핸들러 메소드 실행 전 실행되는 메소드다.
 * 				- 이 메소드가 true를 반환하면 요청핸들러 메소드를 실행하고, false를 반환하면 요청핸들러 메소드를 실행하지 않는다.
 * 
 * 			void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
 * 				- 컨트롤러의 요청핸들러 메소드 실행 후 실행되는 메소드다.
 * 
 * 			void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
 * 				- 클라이언트의 요청을 처리하고, DispatcherServlet에서 응답을  보낸 후 실행되는 메소드다.
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

	private static Logger logger = LogManager.getLogger(LoginCheckInterceptor.class);
	
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
			response.sendRedirect("/error/login/form.do");
			return false;
		}
		
		return true;
	}
}
