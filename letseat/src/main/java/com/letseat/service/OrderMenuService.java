package com.letseat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letseat.model.order.OrderList;
import com.letseat.model.order.OrderMenu;
import com.letseat.repository.OrderListRepository;
import com.letseat.repository.OrderMenuRepository;

@Service
@Transactional
public class OrderMenuService {
	@Autowired
	OrderMenuRepository orderMenuRepository;
	@Autowired
	OrderListRepository orderListRepository;
	public OrderMenu registerOrderMenu(OrderMenu orderMenu) {
		orderMenu = orderMenuRepository.save(orderMenu);
		return orderMenu;
	}
	public List<OrderMenu> loadOrderMenu(int orderId) {
		Optional<OrderList> orderList_Optional = orderListRepository.findById(orderId);
		OrderList orderList = orderList_Optional.get();
		List<OrderMenu> menus = orderMenuRepository.findByOrderList(orderList);
		return menus;
	}
}
