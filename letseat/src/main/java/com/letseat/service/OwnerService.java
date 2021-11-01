package com.letseat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letseat.model.user.Owner;
import com.letseat.repository.OwnerRepository;

@Service
@Transactional
public class OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;
	/**
	 * 회원 가입
	 */
	public boolean register(Owner owner) {
		boolean check = checkEmailDuplicate(owner.getEmail());
		if(!check) {
			ownerRepository.save(owner);
			return true;
		}
		return false;
	}
	// 전체 회원 조회
	public List<Owner> findUsers(){
		return ownerRepository.findAll();
	}
	// 아이디 중복 검사
	public boolean checkEmailDuplicate(String email) {
		return ownerRepository.existsByEmail(email);
	}
	// 특정 회원 검색
	public Owner findOwner(String email) {
		return ownerRepository.findByEmail(email);
	}

}
