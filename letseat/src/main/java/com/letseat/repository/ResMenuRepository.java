package com.letseat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.restaurant.ResMenu;
import com.letseat.model.restaurant.Restaurant;

public interface ResMenuRepository extends JpaRepository<ResMenu, Integer>{
	List<ResMenu> findByrestaurant(Restaurant restaurant);
}
