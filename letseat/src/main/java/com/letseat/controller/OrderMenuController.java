package com.letseat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.OrderMenuDto;
import com.letseat.model.order.OrderList;
import com.letseat.model.order.OrderMenu;
import com.letseat.repository.OrderMenuRepository;
import com.letseat.service.OrderMenuService;
import com.letseat.wrapper.OrderMenuWrapper;

@RestController
public class OrderMenuController {
	@Autowired
	OrderMenuRepository orderMenuRepository;
	
	@Autowired
	OrderMenuService orderMenuService;
	
	// 메뉴 저장
	@PostMapping("/order/menu/register")
	public OrderMenuDto registerOrderMenu(@RequestBody OrderMenuWrapper wrapper) {
		OrderMenuDto orderMenuDto = new OrderMenuDto();
		for(OrderMenuDto menuDto : wrapper.getMenus()) {
			OrderMenu orderMenu = menuDto.toEntity();
			orderMenu = orderMenuService.registerOrderMenu(orderMenu);
		}
		return orderMenuDto;
	}
	// 메뉴 불러오기
	@GetMapping("/order/menu/load")
	public List<OrderMenuDto> loadOrderMenu(@RequestParam int orderId) {
		List<OrderMenu> menuList = orderMenuService.loadOrderMenu(orderId);
		List<OrderMenuDto> menus = new ArrayList<>();
		for(int i = 0; i < menuList.size(); i++) {
			OrderMenuDto menuDto = new OrderMenuDto();
			OrderMenu menuEntity = menuList.get(i); 
			menuDto.setAmount(menuEntity.getAmount());
			menuDto.setResMenu(menuEntity.getResMenu());
			menus.add(menuDto);
		}
		return menus;
	}
}
