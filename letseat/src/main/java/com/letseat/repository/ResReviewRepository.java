package com.letseat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.restaurant.ResReview;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;

public interface ResReviewRepository extends JpaRepository<ResReview, Integer>{
	List<ResReview> findAllByRestaurant(Restaurant restaurant);
	List<ResReview> findAllByUser(User user);
	List<ResReview> findAllByMenuName(String menuName);
}
