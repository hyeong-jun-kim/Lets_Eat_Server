package com.letseat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.order.OrderList;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;

public interface OrderListRepository extends JpaRepository<OrderList, Integer>{
	OrderList findByuser(User user);
	List<OrderList> findAllByRestaurant(Restaurant restaurant);
	List<OrderList> findAllByUser(User user);
}
