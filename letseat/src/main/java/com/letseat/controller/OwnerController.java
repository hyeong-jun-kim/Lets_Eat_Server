package com.letseat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.OwnerDto;
import com.letseat.model.user.Owner;
import com.letseat.repository.OwnerRepository;
import com.letseat.service.OwnerService;
@RestController
public class OwnerController {
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private OwnerService ownerService;
	// Onwer 회원가입
	@PostMapping("/register/owner/noraml")
	public Owner regsister(@RequestBody OwnerDto ownerDto) throws IllegalAccessException{
		Owner owner = ownerDto.toEntity();
		boolean check = ownerService.register(owner);
		if(!check)
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		return owner;
	}
	// Owner 이메일 중복 체크
	@GetMapping("/register/owner/email/check")
	public String emailDuplicateCheck(@RequestParam String email){
		boolean check = ownerService.checkEmailDuplicate(email);
		if(check) {
			return "emailCheckFail";
		}
		return "emailCheckOK";
	}
	// Owner 로그인
	@PostMapping("/login/owner/normal")
	public Owner login(@RequestBody OwnerDto ownerDto) {
		String email = ownerDto.getEmail();
		String password = ownerDto.getPassword();
		Owner owner = new Owner();
		owner = ownerDto.toEntity();
		owner = ownerRepository.findByEmail(email);
		if(owner == null) {
			throw new IllegalStateException("존재하지 않는 회원입니다.");
		}
		String user_pwd = owner.getPassword();
		if(!user_pwd.equals(password))
			throw new IllegalStateException("비밀번호를 확인해주세요.");
		return owner;
	}
	// 특정 회원 검색해서 OwerId 리턴
	@GetMapping("/register/owner/get/id")
	public int getOwnerId(@RequestParam String email){
		Owner owner = ownerService.findOwner(email);
		int id = owner.getOwnerId();
		return id;
	}
}
