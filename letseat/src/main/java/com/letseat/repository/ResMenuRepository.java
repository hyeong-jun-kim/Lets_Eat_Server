package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.restaurant.ResMenu;

public interface ResMenuRepository extends JpaRepository<ResMenu, Integer>{

}
