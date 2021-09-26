package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.restaurant.ResType;

public interface ResTypeRepository extends JpaRepository<ResType, Integer>{
	
}
