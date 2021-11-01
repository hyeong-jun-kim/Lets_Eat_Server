package com.letseat.dto;

import com.letseat.model.order.OrderList;
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
public class OrderListDto {
	private User user;
	private Restaurant restaurant;
	private int tableNumber;
	private int sum;
	private String checkYN;
	private String orderYN;
	private String servingYN;
	private String reviewYN;
	private String request;
	private String orderTime;
	private String servingTime;
	public OrderList toEntity() {
		return OrderList.builder().user(user)
				.restaurant(restaurant).tableNumber(tableNumber)
				.request(request).orderTime(orderTime).servingTime(servingTime).sum(sum).
				checkYN(checkYN).orderYN(orderYN).servingYN(servingYN)
				.reviewYN(reviewYN).build();
	}
}
