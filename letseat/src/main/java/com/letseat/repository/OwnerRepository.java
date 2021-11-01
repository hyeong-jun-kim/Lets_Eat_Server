package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.user.Owner;
import com.letseat.model.user.User;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	// 이메일로 찾아서 유저 반환
	boolean existsByEmail(String email);

	Owner findByEmail(String email);
}
