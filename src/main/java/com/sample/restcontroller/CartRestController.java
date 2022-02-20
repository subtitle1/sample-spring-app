package com.sample.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.annotation.LoginedUser;
import com.sample.dto.ResponseDto;
import com.sample.exception.CartErrorException;
import com.sample.service.CartItemService;
import com.sample.util.SessionUtils;
import com.sample.vo.User;

@RestController
@RequestMapping("/rest/cart")
public class CartRestController {

	static final Logger logger = LogManager.getLogger(CartRestController.class);
	
	@Autowired
	CartItemService cartItemService;
	
	@GetMapping("/counts.do")
	public ResponseDto<Integer> getCartItemCounts() {
		User user = (User) SessionUtils.getAttribute("LOGIN_USER");
		
		ResponseDto<Integer> response = new ResponseDto<Integer>();
		response.setStatus("OK");
		response.setItems(List.of(0));
		
		if (user != null) {
			int cartItemsCount = cartItemService.getMyCartItems(user.getNo()).size();
			response.setItems(List.of(cartItemsCount));
		} 
		return response;
	} 
	
	@GetMapping("/add.do")
	public ResponseDto<?> add(@LoginedUser User user, int bookNo) {
		logger.info("로그인된 사용자정보 : " + user);
		//User user = (User) SessionUtils.getAttribute("LOGIN_USER");
		if (user == null) {
			throw new CartErrorException("장바구니 아이템 삭제는 로그인 후 사용가능합니다.");
		}
		
		// 장바구니에 정보를 추가시킨다
		cartItemService.addNewCartItem(user.getNo(), bookNo);
		int cartItemsCount = cartItemService.getMyCartItems(user.getNo()).size();
		
		ResponseDto<Integer> response = new ResponseDto<>();
		response.setStatus("OK");
		response.setItems(List.of(cartItemsCount));
		return response;
	}

	@GetMapping("/delete.do")
	public ResponseDto<?> delete(@LoginedUser User user, int no) {
		logger.info("로그인된 사용자정보 : " + user);
		// SessionUtils를 사용해서 세션객체에 저장된 인증된 사용자 정보를 조회한다.
		//User user = (User) SessionUtils.getAttribute("LOGIN_USER");
		if (user == null) {
			throw new CartErrorException("장바구니 아이템 삭제는 로그인 후 사용가능합니다.");
		}
		
		cartItemService.deleteCartItem(user.getNo(), no);
		int cartItemsCount = cartItemService.getMyCartItems(user.getNo()).size();
		
		ResponseDto<Integer> response = new ResponseDto<>();
		response.setStatus("OK");
		response.setItems(List.of(cartItemsCount));
		return response;
	}
}
