package com.letseat.dto;

import javax.persistence.Column;

import com.letseat.model.order.OrderList;
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
public class OrderMenuDto {
	private OrderList orderList;
	private ResMenu resMenu;
	private int amount;
	public OrderMenu toEntity() {
		return OrderMenu.builder().orderList(orderList).amount(amount).resMenu(resMenu).build();
	}
}
