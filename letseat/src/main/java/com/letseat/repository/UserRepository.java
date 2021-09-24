package com.letseat.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letseat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	// 이메일로 찾아서 유저 반환
	boolean existsByEmail(String email);
	User findByEmail(String email);
}
