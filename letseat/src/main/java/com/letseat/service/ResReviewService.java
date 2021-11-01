package com.letseat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letseat.model.order.OrderList;
import com.letseat.model.restaurant.ResReview;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;
import com.letseat.repository.OrderListRepository;
import com.letseat.repository.ResReviewRepository;
import com.letseat.repository.RestaurantRepository;
import com.letseat.repository.UserRepository;

@Service
@Transactional
public class ResReviewService {
	@Autowired
	private OrderListRepository orderListRepository;
	@Autowired
	private ResReviewRepository resReviewRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private UserRepository userRepository;
	// 등록
	public ResReview register(ResReview resReview) {
		return resReviewRepository.save(resReview);
	}
	// 레스토랑으로 찾기
	public List<ResReview> loadByResId(int resId) {
		Optional<Restaurant> res_o = restaurantRepository.findById(resId);
		Restaurant res = res_o.get();
		List<ResReview> resReviews = resReviewRepository.findAllByRestaurant(res);
		return resReviews;
	}
	// 유저아이디로 찾기
	public List<ResReview> loadByUserId(int userId){
		Optional<User> user_o = userRepository.findById(userId);
		User user = user_o.get();
		List<ResReview> userReviews = resReviewRepository.findAllByUser(user);
		return userReviews;
	}
	/**
	 * 리뷰 검색
	 * 
	 */
	// 메뉴이름으로 찾기 
	public List<ResReview> loadByMenuName(String menuName){
		return resReviewRepository.findAllByMenuName(menuName);
	}
	// 등록된 리뷰 메뉴중 랜덤으로 5개 보여주기
	public List<String> getRandomReview(){
		ArrayList<String> menus = new ArrayList<>();
		ArrayList<String> random_menus = new ArrayList<>();
		List<ResReview> Reviews = resReviewRepository.findAll();
		for(int i = 0; i < Reviews.size(); i++) {
			String name = Reviews.get(i).getMenuName();
			if(!menus.contains(name)) {
				menus.add(name);
			}
		}
		if(menus.size() < 5)
			return null;
		Random random = new Random();
		while(random_menus.size() < 5) {
			random.setSeed(System.currentTimeMillis());
			int idx = random.nextInt(menus.size());
			if(!random_menus.contains(menus.get(idx))) {
				random_menus.add(menus.get(idx));
			}
		}
		return menus;
	}

}
