package com.letseat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letseat.model.User;
import com.letseat.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	/**
	 * 회원 가입
	 */
	public boolean register(User user) {
		boolean check = checkEmailDuplicate(user.getEmail());
		if(!check) {
			userRepository.save(user);
			return true;
		}
		return false;
	}
	// 전체 회원 조회
	public List<User> findUsers(){
		return userRepository.findAll();
	}
	// 아이디 중복 검사
	public boolean checkEmailDuplicate(String email) {
		return userRepository.existsByEmail(email);
	}
}
