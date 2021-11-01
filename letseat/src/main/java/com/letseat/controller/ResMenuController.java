package com.letseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.ResMenuDto;
import com.letseat.dto.RestaurantDto;
import com.letseat.model.restaurant.ResMenu;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.repository.ResMenuRepository;
import com.letseat.repository.RestaurantRepository;
import com.letseat.service.ResMenuService;
import com.letseat.service.RestaurantService;

@RestController
public class ResMenuController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ResMenuRepository resMenuRepository;
	@Autowired
	private ResMenuService resMenuService;
	
	// 메뉴 등록
	@PostMapping("/store/menu/register")
	public ResMenu register(@RequestBody ResMenuDto resMenuDto) {
		ResMenu resMenu = resMenuDto.toEntity();
		resMenuService.register(resMenu);
		return resMenu;
	}
	
	// 메뉴 가져오기
	@GetMapping("/store/menu/load")
	public List<ResMenu> register(@RequestParam int resId) {
		Restaurant res = restaurantService.findRestaurantById(resId);
		List<ResMenu> menus = resMenuService.searchResMenu(res);
		return menus;
	}
}
