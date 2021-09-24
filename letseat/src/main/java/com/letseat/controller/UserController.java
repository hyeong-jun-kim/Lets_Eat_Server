package com.letseat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.model.ApiType;
import com.letseat.model.User;
import com.letseat.repository.UserRepository;
import com.letseat.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@PostMapping("/register/normal")
	public User regsister(@RequestBody User userInfo) throws IllegalAccessException{
		User user = new User();
		user.setEmail(userInfo.getEmail());
		user.setPassword(userInfo.getPassword());
		user.setName(userInfo.getName());
		user.setGender(userInfo.getGender());
		user.setBirthday(userInfo.getBirthday());
		user.setApi_token(ApiType.normal);
		boolean check = userService.register(user);
		if(!check)
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		return user;
	}
	@GetMapping("/register/email/check")
	public String emailDuplicateCheck(@RequestParam String email){
		boolean check = userService.checkEmailDuplicate(email);
		if(check) {
			return "emailCheckFail";
		}
		return "emailCheckOK";
	}
	@PostMapping("/login/normal")
	public User login(@RequestBody User userInfo) {
		String email = userInfo.getEmail();
		String password = userInfo.getPassword();
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new IllegalStateException("존재하지 않는 회원입니다.");
		}
		String user_pwd = user.getPassword();
		if(!user_pwd.equals(password))
			throw new IllegalStateException("비밀번호를 확인해주세요.");
		return user;
	}
}
