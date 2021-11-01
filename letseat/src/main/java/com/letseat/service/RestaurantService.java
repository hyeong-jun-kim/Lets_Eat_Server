package com.letseat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letseat.dto.RestaurantDto;
import com.letseat.model.restaurant.ResType;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.Owner;
import com.letseat.repository.OwnerRepository;
import com.letseat.repository.RestaurantRepository;

@Service
@Transactional
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	/**
	 * 레스토랑 등록
	 */
	
	public Restaurant register(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}
	// 레스토랑 저장
	public Restaurant save(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}
	// 모든 레스토랑 검색
	public List<Restaurant> findAllRestaurant() {
		return restaurantRepository.findAll();
	}
	// 특정 레스토랑 검색
	public Restaurant findRestaurantById(int resId) {
		return restaurantRepository.getById(resId);
	}

	// 레스토랑 타입 검색
	public List<Restaurant> findRestaurantType(ResType resType) {
		return restaurantRepository.findByRestype(resType);
	}
	// OwnerId로 레스토랑 찾기
	public List<Restaurant> findRestaurantByOwnerId(int ownerId){
		Optional<Owner> owner_optional = ownerRepository.findById(ownerId);
		Owner owner = owner_optional.get();
		return restaurantRepository.findByOwner(owner);
	}
	// 레스토랑 검색 알고리즘
	public List<Restaurant> searchRestaurant(String name) {
		List<Restaurant> resList = new ArrayList<>();
		List<Restaurant> restaurants = restaurantRepository.findAll();
		for (int j = 0; j < restaurants.size(); j++) {
			String resName = restaurants.get(j).getResName();
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < resName.length(); k++) {
				sb.append(resName.charAt(k));
				if (name.equals(sb.toString())) {
					resList.add(restaurants.get(j));
					break;
				}
			}
		}
		return resList;
	}
	// 식당 정보수정
	public Restaurant updateRestaurant(Restaurant updateRes, Restaurant resInfo) {
		updateRes.setAloneAble(resInfo.getAloneAble());
		updateRes.setBusinessNumber(resInfo.getBusinessNumber());
		updateRes.setImage(resInfo.getImage());
		updateRes.setLocation(resInfo.getLocation());
		updateRes.setOpenTime(resInfo.getOpenTime());
		updateRes.setOwner(resInfo.getOwner());
		updateRes.setPhoneNumber(resInfo.getPhoneNumber());
		updateRes.setResIntro(resInfo.getResIntro());
		updateRes.setResName(resInfo.getResName());
		updateRes.setRestype(resInfo.getRestype());
		return restaurantRepository.save(updateRes);
	}
	// 혼밥집인 식당 찾기
	public List<Restaurant> findAloneAbleRestaurant(){
		return restaurantRepository.findByAloneAble(1);
	}
}
