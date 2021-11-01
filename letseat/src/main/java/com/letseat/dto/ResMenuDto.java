package com.letseat.dto;

import java.sql.Blob;

import com.letseat.model.restaurant.ResMenu;
import com.letseat.model.restaurant.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ResMenuDto {
	private String name;
	private int price;
	private String photo;
	private String excription;
	private Restaurant restaurant;
	
	public ResMenu toEntity() {
		return ResMenu.builder().name(name).price(price)
				.photo(photo).excription(excription)
				.restaurant(restaurant).build();
	}
}
