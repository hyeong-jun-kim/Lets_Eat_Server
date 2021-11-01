package com.letseat.dto;

import com.letseat.model.restaurant.ResReview;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ResReviewDto {
	private String menuName;
	private String content;
	private int rate;
	private String image;
	private String date;
	private Restaurant restaurant;
	private User user;
	
	public ResReview toEntity() {
		return ResReview.builder().menuName(menuName).content(content).
				rate(rate).image(image).restaurant(restaurant).user(user).date(date).
				build();
	}
}
