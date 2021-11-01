package com.letseat.dto;

import java.util.List;

import com.letseat.model.order.OrderMenu;
import com.letseat.model.restaurant.ResMenu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OrderDto {
	// OrderListDto
	private int resId;
	private int orderId;
	private int tableNumber;
	private String request;
	private String orderTime;
	private String servingTime;
	private String resName;
	private String image;
	private String checkYN;
	private String servingYN;
	private String reviewYN;
	// OrderMenu
	private List<OrderMenuDto> orderMenus;
	// ResMenu
	private List<ResMenuDto> resMenus;
}
