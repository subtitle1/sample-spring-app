package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.dto.CartItemDto;
import com.sample.service.CartItemService;
import com.sample.util.SessionUtils;
import com.sample.vo.User;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		
		// 세션에 저장된 사용자 정보를 조회한다.
		User user = (User) SessionUtils.getAttribute("LOGIN_USER");
		if (user == null) {
			return "redirect:/login.do?error=deny";
		}
		
		// 사용자번호로 장바구니 아이템 정보를 조회한다.
		List<CartItemDto> cartItems = cartItemService.getMyCartItems(user.getNo());
		
		// 조회된 장바구니 아이템정보를 뷰페이지로 전달하기 위해 model에 저장한다.
		model.addAttribute("cartItems", cartItems);
		
		return "cart/list.jsp";
	}
	
	@GetMapping("/insert.do")
	public String insert() {
		
		return "redirect:cart/list.do";
	}
	
	@GetMapping("/delete.do")
	public String delete() {
		
		return "";
	}
}
