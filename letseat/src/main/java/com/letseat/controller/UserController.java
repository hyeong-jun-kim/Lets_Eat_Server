package com.letseat.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.UserDto;
import com.letseat.model.user.ApiType;
import com.letseat.model.user.User;
import com.letseat.repository.UserRepository;
import com.letseat.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@PostMapping("/register/normal")
	public User regsister(@RequestBody UserDto userDto) throws IllegalAccessException{
		ModelMapper mm = new ModelMapper();
		User user = new User();
		mm.map(userDto, user);
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
	public User login(@RequestBody UserDto userDto) {
		String email = userDto.getEmail();
		String password = userDto.getPassword();
		ModelMapper mm = new ModelMapper();
		User user = new User();
		mm.map(userDto, user);
		user = userRepository.findByEmail(email);
		if(user == null) {
			throw new IllegalStateException("존재하지 않는 회원입니다.");
		}
		String user_pwd = user.getPassword();
		if(!user_pwd.equals(password))
			throw new IllegalStateException("비밀번호를 확인해주세요.");
		return user;
	}
}
