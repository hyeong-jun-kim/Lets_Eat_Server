package com.letseat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;
import com.letseat.model.waiting.Waiting;

@Repository
public interface WaitingRepository extends JpaRepository<Waiting, Integer>{
	List<Waiting> findAllByRestaurant(Restaurant restaurant);
	List<Waiting> findAllByUser(User user);
}
