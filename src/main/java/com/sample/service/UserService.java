package com.sample.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.UserDao;
import com.sample.vo.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public User login(String id, String password) {
		// 회원정보
		User user = userDao.getUserById(id);
		if (user == null) {
			throw new RuntimeException("회원정보가 존재하지 않습니다.");
		}
		if ("Y".equals(user.getDisabled())) {
			throw new RuntimeException("탈퇴처리된 회원 아이디입니다.");
		}
		if (!password.equals(user.getPassword())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		// 인증된 사용자 정보를 반환
		return user;
	}
}
