package com.sample.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.dto.ResponseDto;
import com.sample.exception.LoginErrorException;
import com.sample.exception.RestLoginErrorException;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping("/login/form.do")
	public String loginform() {
		throw new LoginErrorException("로그인 후 사용가능한 서비스 입니다.");
		// 아래의 예외처리 핸들러 메소드가 실행되도록 예외를 강제로 발생시켰다.
	}
	/*
	 * 
	@ExceptionHandler(LoginErrorException.class) 
	public String handleLoginErrorException(LoginErrorException e, Model model){
		
		model.addAttribute("error", e.getMessage());
		return "loginform.jsp";
	}
	 */
	
	
	@RequestMapping("/login/rest.do")
	public ResponseDto<?> rest() {
		throw new RestLoginErrorException("로그인 후 사용가능한 서비스입니다.");
		// 아래의 예외처리 핸들러 메소드가 실행된다.
	}
	
	/*
	@ExceptionHandler(RestLoginErrorException.class)
	public @ResponseBody ResponseDto<?> handleCartErrorException(RestLoginErrorException e) {
		
		ResponseDto<?> response = new ResponseDto<>();
		response.setStatus("FAIL");
		response.setError(e.getMessage());
		
		return response;
	}
	*/
}
