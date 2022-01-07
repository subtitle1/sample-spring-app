package com.sample.exceptionhandler;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.dto.ResponseDto;
import com.sample.exception.CartErrorException;
import com.sample.exception.CustomException;
import com.sample.exception.LoginErrorException;

/*
 * @ControllerAdvice와 @RestControllerAdvice
 * 		- @ControllerAdvice는 @Controller 클래스와 @RestController 관련된 예외처리가 구현된 클래스를 나타내는 어노테이션이다.
 * 		- @RestControllerAdvice @RestController 클래스 관련된 예외처리가 구현된 클래스를 나타내는 어노테이션이다.
 * 
 * 		- @ControllerAdvie 클래스의 예외처리 메소드는 예외가 발생하면 오류페이지 혹은 JSON 응답을 제공할 수 있다.
 * 		- @RestControllerAdvice는 예외가 발생하면 JSON 응답만 제공할 수 있다.
 * 
 * 		- 웹 애플리케이션이 jsp와 같은 웹페이지를 응답으로 제공하는 애플리케이션인 경우, @ControllerAdvice만 사용해서 오류페이지를 응답으로 제공하면 된다.
 * 		- 웹 애플리케이션이 웹페이지와 Rest 응답을 제공하는 애플리케이션인 경우, @ControllerAdvice만 사용하거나 @RestControllerAdvice를 같이 사용할 수 있다.
 * 		- 웹 애플리케이션이 Rest 응답을 제공하는 애플리케이션인 경우, @RestControllerAdvice만 사용해서 응답을 제공할 수 있다.
 * 
 * 		- 예외가 발생하면 여러 예외처리 메소드 중에서 발생한 예외와 가장 일치하는 예외가 정의된 메소드가 실행된다.
 * 		- 일치하는 예외가 없으면 부모타입의 예외가 정의된 예외처리 메소드가 실행된다.
 * 
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(CartErrorException.class)
	public @ResponseBody ResponseDto<?> handleCartErrorException(CartErrorException e) {
		ResponseDto<?> response = new ResponseDto<>();
		response.setStatus("FAIL");
		response.setError(e.getMessage());
		
		return response;
	}
	
	@ExceptionHandler
	public String handleLoginErrorException(LoginErrorException e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "loginform.jsp";
	}
	
	@ExceptionHandler(CustomException.class)
	public String handleCustomException(CustomException e) {
		return "error/customError.jsp";
	}
	
	@ExceptionHandler(DataAccessException.class) 
	public String handleDataAccessException(DataAccessException e) {
		return "error/databaseError.jsp";
	}
	
	
	// exception이 발생하면 serverError.jsp로 보낸다
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		return "error/serverError.jsp";
	}
}
