package com.letseat.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.RestaurantDto;
import com.letseat.model.restaurant.ResType;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.repository.RestaurantRepository;
import com.letseat.service.RestaurantService;

@RestController
public class RestaurantController {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private RestaurantService restaurantService;
	// 레스토랑 등록
	@PostMapping("/store/register")
	public Restaurant register(@RequestBody RestaurantDto restaurantDto) {;
		Restaurant restaurant = new Restaurant();
		restaurant = restaurantDto.toEntity();
		restaurantService.register(restaurant);
		return restaurant;
	}
	// 아이디로 레스토랑 검색
	@GetMapping("/store/findRestaurantById")
	public Restaurant findRestaurantById(@RequestParam int resId) {
		Restaurant res = restaurantService.findRestaurantById(resId);
		return res;
	}
	// 모든 레스토랑 검색 
	@GetMapping("/store/findAll")
	public List<Restaurant> findAllRestaurant(){
		List<Restaurant> resList = restaurantService.findAllRestaurant();
		return resList;
	}
	// 특정 레스토랑 검색
	@GetMapping("/store/findOne")
	public Restaurant findOneRestaurant(@RequestParam int resId) {
		Restaurant res = restaurantService.findRestaurantById(resId);
		return res;
	}
	// OwnerId로 레스토랑 검색
	@GetMapping("/store/findOwner")
	public List<Restaurant> findAllRestaurantByOwnerId(@RequestParam int ownerId){
		return restaurantService.findRestaurantByOwnerId(ownerId);
	}
	
	// 레스토랑 카테고리 검색
	@GetMapping("/store/findRestaurant")
	public List<Restaurant> findOneRestaurant(@RequestParam ResType restype) {
		List<Restaurant> resArray = restaurantService.findRestaurantType(restype);
		return resArray;
		}
	// 식당 검색
	@GetMapping("/store/searchRestaurant")
	public List<Restaurant> searchRestaurants(@RequestParam String name){
		List<Restaurant> resList = restaurantService.searchRestaurant(name);
		return resList;
	}
	// 식당 정보수정
	@PutMapping("/store/update/{id}")
	public Restaurant update(@PathVariable int id, @RequestBody Restaurant resInfo){
		Restaurant restaurant = restaurantRepository.getById(id);
		restaurant = restaurantService.updateRestaurant(restaurant, resInfo);
		return restaurant;
	}
	// 혼밥집 찾기
	@GetMapping("/store/find/aloneAble")
	public List<Restaurant> findAloneAbleRestaurant(){
		return restaurantService.findAloneAbleRestaurant();
	}
}
