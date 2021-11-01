package com.letseat.wrapper;

import java.util.List;

import com.letseat.dto.OrderMenuDto;

public class OrderMenuWrapper {
	private List<OrderMenuDto> orderMenus;
	
	public List<OrderMenuDto> getMenus(){
		return orderMenus;
	}
	
	public void setOrderMenus(List<OrderMenuDto> orderMenus) {
		this.orderMenus = orderMenus;
	}
}
