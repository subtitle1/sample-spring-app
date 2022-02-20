package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sample.exception.LoginErrorException;
import com.sample.service.UserService;
import com.sample.util.SessionUtils;
import com.sample.vo.User;

/*			
 * @Controller
 * 		- 클라이언트의 HTTP 요청을 처리하는 요청핸들러 메소드를 포함하고 있는 컨트롤러 클래스임을 나타낸다.
 * 		- 스프링은 컨트롤러에서 정의된 @RequestMapping, @GetMapping, @PostMapping 어노테이션 정보를 분석해서
 * 		요청URL과 요청핸들러 메소드를 매핑시킨다.
 * 		- 클라이언트의 요청이 접수되면 요청URL을 분석해서 매핑된 요청핸들러 메소드를 실행한다.
 * 
 * @SessionAttributes
 * 		- 지정된 이름으로 Model에 객체가 저장되면, 해당 객체를 HttpSession에 저장시킨다.
 * 		사용예)
 * 			public String login(String id, String password) {
				// 사용자 인증서비스 호출
				User user = userService.login(id, password);
				// 인증된 사용자정보를 Model에 저장
				// @SessionAttributes(names = {("LOGIN_USER")} 이 설정 때문에
				// user 객체는 HttpSession 객체에 저장된다.
				model.addAttribute("LOGIN_USER", user);
				return "redirect:home.do";
			}
 * 
 * SessionStatus
 * 		- @SessionAttributes로 HttpSession 객체에 저장된 객체를 삭제한다.
 * 		- 동일한 컨트롤러 안에서 @SessionAttributes로 저장한 것만 삭제된다.
 * 		- SessionStatus의 setComplete() 메소드는 동일한 컨트롤러 안에서 @SessionAttributes 어노테이션 설정으로
 * 	  	  HttpSession에 저장된 속성을 삭제한다.
 */
@Controller
/* 인증된 사용자 정보를 세션에 담는다. 
 * @SessionAttributes(names = {"LOGIN_USER"}) 
 */

public class HomeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/home.do")
	public String home() {
		return "home.jsp";
	}
	
	@GetMapping("/login.do")
	public String loginform() {
		
		return "loginform.jsp";
	}
	
	@PostMapping("/login.do")
	public String login(String id, String password) {
		// 아이디와 비밀번호가 비어있거나 공백만 있으면 loginform.jsp로 내부이동
		if (!StringUtils.hasText(id) || !StringUtils.hasText(password)) {
			throw new LoginErrorException("아이디와 비밀번호는 필수 입력값입니다.");
		}
		
		User user = userService.login(id, password);
		SessionUtils.addAttribute("LOGIN_USER", user);
		return "redirect:home.do";

	}
	
	/*
	 * @GetMapping("logout.do") public String logout(SessionStatus sessionStatus) {
	 * sessionStatus.setComplete();
	 * 
	 * return "redirect:home.do"; }
	 */
	
	@GetMapping("/logout.do")
	public String logout() {
		SessionUtils.removeAttrubute("LOGIN_USER");
		return "redirect:home.do";
	}
	
	@GetMapping("/movie/list.do")
	public String movieList() {
		return "/movie/list.jsp";
	}
	
	@GetMapping("/movie/detail.do")
	public String movieDetail(int id) {
		return "/movie/detail.jsp";
	}
}
