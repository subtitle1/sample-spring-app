package com.sample.argumentResolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.sample.annotation.LoginedUser;
import com.sample.util.SessionUtils;

/*
 * HandlerMethodArgumentResolver
 * 		- 요청핸들러 메소드의 매개변수를 분석해서 해당 매개변수에 적절한 값을 전달하는 객체다.
 * 		- 주요 API
 * 			- boolean supportsParameter(MethodParameter parameter)
 * 				- 이 메소드가 true를 반환하면 resolveArgument() 메소드가 실행된다.
 * 				- 사용자가 지정한 매개변수에 대해서 true를 반환하도록 수행문을 작성해야 한다. 
 * 				- 사용자정의 HandlerMethodArgumentResolver의 적용대상 매개변수인지 여부를 true/false 값으로 변환한다.
 * 
 * 			- Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
				- supportsParameter() 메소드가 true를 반환할 때만 실행된다.
				- 이 메소드가 반환하는 값이 요청핸들러 메소드의 매개변수에 전달된다.
				- 요청핸들러 메소드의 요청파라미터에 적절한 값을 반환한다. 
 */

public class LoginedUserArgumentResolver implements HandlerMethodArgumentResolver {

	static final Logger logger = LogManager.getLogger(LoginedUserArgumentResolver.class);
	
	// MethodParameter는 매개변수에 대한 다양한 정보를 제공해 준다
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		logger.info("@LoginedUser 어노테이션을 가지고 있는가? : " + parameter.hasParameterAnnotation(LoginedUser.class));
		logger.info("매개변수의 클래스이름: " + parameter.getParameterType().getClass().getName());
		logger.info("매개변수의 이름: " + parameter.getParameterName());
		
		// LoginedUser 어노테이션을 갖고 있는 매개변수인지 true or false 반환
		return parameter.hasParameterAnnotation(LoginedUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		logger.info("@LoginedUser이 부착된 매개변수다. HttpSession객체에 저장된 인증된 사용자정보를 매개변수로 전달한다.");
		// HttpSession 객체에 저장된 속성명으로 사용정보를 반환한다.
		return SessionUtils.getAttribute("LOGIN_USER");
	}

}
