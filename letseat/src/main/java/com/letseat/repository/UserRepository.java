package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
