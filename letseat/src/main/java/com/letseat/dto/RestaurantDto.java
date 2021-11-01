package com.letseat.dto;

import java.sql.Blob;

import com.letseat.model.restaurant.ResType;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RestaurantDto {
	private String resName;
	private String phoneNumber;
	private String openTime;
	private String resIntro;
	private String businessNumber;
	private String location;
	private int aloneAble;
	private ResType restype;
	private Owner owner;
	private String image;
	
	public Restaurant toEntity() {
		return Restaurant.builder().resName(resName)
				.phoneNumber(phoneNumber).openTime(openTime)
				.resIntro(resIntro).businessNumber(businessNumber)
				.location(location).aloneAble(aloneAble).restype(restype)
				.owner(owner).image(image).build();
	}
}
