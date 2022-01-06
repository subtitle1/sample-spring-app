package com.sample.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.ResponseDto;
import com.sample.service.CartItemService;
import com.sample.util.SessionUtils;
import com.sample.vo.User;

@RestController
@RequestMapping("/rest/cart")
public class CartRestController {
	
	@Autowired
	CartItemService cartItemService;
	
	@GetMapping("/counts.do")
	public ResponseDto<Integer> getCartItemCounts() {
		User user = (User)SessionUtils.getAttribute("LOGIN_USER");
		
		ResponseDto<Integer> response = new ResponseDto<>();
		response.setStatus("OK");
		response.setItems(List.of(0));
		
		if (user != null) {
			int cartItemsCount = cartItemService.getMyCartItems(user.getNo()).size();
			response.setItems(List.of(cartItemsCount));
		}
		return response;
	}
	
	@GetMapping("/add.do")
	public ResponseDto<?> add(int bookNo) {
		User user = (User)SessionUtils.getAttribute("LOGIN_USER");
		
		if (user == null) {
			ResponseDto<?> response = new ResponseDto<>();
			response.setStatus("FAIL");
			response.setError("로그인된 사용자가 아닙니다.");
			return response;
		}
		
		try {
			// 장바구니 정보를 추가시킨다
			cartItemService.addNewCartItem(user.getNo(), bookNo);
			int cartItemsCount = cartItemService.getMyCartItems(user.getNo()).size();
			
			ResponseDto<Integer> response = new ResponseDto<>();
			response.setStatus("OK");
			response.setItems(List.of(cartItemsCount));
			return response;
		} catch (RuntimeException e) {
			ResponseDto<?> response = new ResponseDto<>();
			response.setStatus("FAIL");
			response.setError(e.getMessage());
			return response;
		}
	}
	
	@GetMapping("/delete.do")
	public ResponseDto<?> delete(int no) {
		User user = (User)SessionUtils.getAttribute("LOGIN_USER");
		
		if (user == null) {
			ResponseDto<?> response = new ResponseDto<>();
			response.setStatus("FAIL");
			response.setError("로그인된 사용자가 아닙니다.");
			return response;
		}
		
		try {
			cartItemService.deleteCartItem(user.getNo(), no);
			int cartItemsCount = cartItemService.getMyCartItems(user.getNo()).size();
			
			ResponseDto<Integer> response = new ResponseDto<>();
			response.setStatus("OK");
			response.setItems(List.of(cartItemsCount));
			return response;
			
		} catch(RuntimeException e) {
			ResponseDto<?> response = new ResponseDto<>();
			response.setStatus("FAIL");
			response.setError(e.getMessage());
			return response;
		}
	}
}
