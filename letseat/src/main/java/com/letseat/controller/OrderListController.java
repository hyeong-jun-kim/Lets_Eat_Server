package com.letseat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.OrderDto;
import com.letseat.dto.OrderListDto;
import com.letseat.model.order.OrderList;
import com.letseat.repository.OrderListRepository;
import com.letseat.service.OrderListService;

@RestController
public class OrderListController {
	@Autowired
	OrderListRepository orderListRepository;
	
	@Autowired
	OrderListService orderListService;
	// 주문내역 등록
	@PostMapping("/order/list/register")
	public OrderList registerOrder(@RequestBody OrderListDto orderListDto) {
		OrderList orderList = orderListDto.toEntity();
		orderList = orderListService.registerOrderList(orderList);
		return orderList;
	}
	// 주문내역 불러오기
	@GetMapping("/order/list/load")
	public List<OrderList> loadOrder(@RequestParam int resId) {
		List<OrderList> orderList = orderListService.loadOrderList(resId);
		return orderList;
	}
	// 유저가 현재 주문한 내역 불러오기
	@GetMapping("/order/list/user")
	public List<OrderDto> loadWaitingUserOrder(@RequestParam int userId){
		List<OrderDto> orderList = orderListService.loadUserWaitingOrderList(userId);
		return orderList;
	}
	// 레스토랑이 주문 대기중인 내역 불러오기
	@GetMapping("/order/list/restaurant")
	public List<OrderDto> loadWatingResOrder(@RequestParam int resId){
		List<OrderDto> orderList = orderListService.loadResWaitingOrderList(resId);
		return orderList;
	}
	// 주문 확인 (checkYN N -> Y)
	@PostMapping("/order/check")
	public String setCheckOrder(@RequestParam int orderId) {
		Optional<OrderList> orderList_o = orderListRepository.findById(orderId);
		OrderList orderList = orderList_o.get();
		orderListService.setCheckOrder(orderList);
		return "OK";
	}
	// 서빙 확인
	@PostMapping("/order/serving")
	public String setServingOrder(@RequestParam int orderId) {
		Optional<OrderList> orderList_o = orderListRepository.findById(orderId);
		OrderList orderList = orderList_o.get();
		orderListService.setServingOrder(orderList);
		return "OK";
	}
	// 서빙 완료된 orderList 반환하기
	@GetMapping("/order/list/complete/load")
	public List<OrderDto> loadCompleteOrder(@RequestParam int userId) {
		List<OrderDto> orderList = orderListService.loadCompleteOrder(userId);
		return orderList;
	}
	// resId와 tableNumber로 orderList찾기
	@GetMapping("/order/list/find/orderList")
	public int findOrderList(@RequestParam int resId, @RequestParam int tableNum) {
		return orderListService.findOrderList(resId, tableNum).getOrderId();
	}
	// Review "Y" 인지 확인하기
	@GetMapping("/order/list/get/reviewYN")
	public String getReviewYN(@RequestParam int orderId) {
		return orderListService.checkReviewYN(orderId);
	}
	// 리뷰 작성 시 reviewYN -> Y로 변경
	@GetMapping("/order/list/set/reviewYN")
	public String setReviewYN(@RequestParam int orderId) {
		return orderListService.setReviewYN(orderId);
	}
}
