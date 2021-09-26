package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.order.OrderList;

public interface OrderListRepository extends JpaRepository<OrderList, Integer>{
	
}
