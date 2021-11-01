package com.letseat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.restaurant.ResType;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.Owner;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	boolean existsByResName(String resName);
	List<Restaurant> findByRestype(ResType resType);
	List<Restaurant> findByAloneAble(int aloneAble);
	List<Restaurant> findByOwner(Owner owner);
}
