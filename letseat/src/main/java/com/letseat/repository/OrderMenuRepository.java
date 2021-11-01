package com.letseat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.order.OrderList;
import com.letseat.model.order.OrderMenu;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer>{
	List<OrderMenu> findByOrderList(OrderList orderList);
}
