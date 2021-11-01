package com.letseat.controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.ResReviewDto;
import com.letseat.model.restaurant.ResReview;
import com.letseat.repository.ResReviewRepository;
import com.letseat.service.ResReviewService;

@RestController
public class ResReviewController {
	@Autowired
	ResReviewService resReviewService;
	@Autowired
	ResReviewRepository resReviewRepository;
	// 리뷰등록
	@PostMapping("/review/register")
	public ResReview registerReview(@RequestBody ResReviewDto resReviewDto) {
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		String time = date.format(today);
		resReviewDto.setDate(time);
		ResReview resReview = resReviewDto.toEntity();
		return resReviewService.register(resReview);
	}
	// 사용자에서 리뷰 가져오기
	@GetMapping("/review/load/user")
	public List<ResReview> loadReviewByUser(@RequestParam int userId) {
		return resReviewService.loadByUserId(userId);
	}
	// 레스토랑에서 리뷰 가져오기
	@GetMapping("/review/load/res")
	public List<ResReview> loadReviewByRes(@RequestParam int resId){
		return resReviewService.loadByResId(resId);
	}
	/**
	 * 검색
	 */
	// 메뉴이름으로 찾기
	@GetMapping("/review/load/menuName")
	public List<ResReview> loadReviewByMenuName(@RequestParam String menuName){
		return resReviewService.loadByMenuName(menuName);
	}
	// 랜덤 메뉴이름 5개 추천
	@GetMapping("review/load/randomMenu")
	public List<String> loadRandomMenu(){
		return resReviewService.getRandomReview();
	}
	// 모든 리뷰 가져오기
	@GetMapping("review/load/all")
	public List<ResReview> loadAllReview(){
		return resReviewRepository.findAll();
	}
}
