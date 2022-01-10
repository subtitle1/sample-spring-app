package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.annotation.LoginedUser;
import com.sample.dto.CartItemDto;
import com.sample.service.CartItemService;
import com.sample.vo.User;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/list.do")
	public String list(@LoginedUser User user, Model model) {
		// 세션에 저장된 사용자 정보를 조회한다
		//User user = (User) SessionUtils.getAttribute("LOGIN_USER");
		//if (user == null) {
		//	throw new LoginErrorException("로그인 후 사용가능한 서비스 입니다.");
		//}
		
		// 사용자번호로 장바구니 아이템정보를 조회한다.
		List<CartItemDto> dtos = cartItemService.getMyCartItems(user.getNo());
		// 조회된 장바구니 아이템보를 뷰페이지로 전달하기 위해서 model에 저장한다.
		model.addAttribute("cartItems", dtos);
		
		return "cart/list.jsp";
	}
	
	
}
