package com.letseat.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letseat.dto.OrderDto;
import com.letseat.dto.OrderMenuDto;
import com.letseat.dto.ResMenuDto;
import com.letseat.model.order.OrderList;
import com.letseat.model.order.OrderMenu;
import com.letseat.model.restaurant.ResMenu;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;
import com.letseat.repository.OrderListRepository;
import com.letseat.repository.OrderMenuRepository;
import com.letseat.repository.ResMenuRepository;
import com.letseat.repository.RestaurantRepository;
import com.letseat.repository.UserRepository;

@Service
@Transactional
public class OrderListService {
	@Autowired
	OrderListRepository orderListRepository;
	@Autowired
	OrderMenuRepository orderMenuRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ResMenuRepository resMenuRepository;
	@Autowired
	OrderMenuService orderMenuService;

	public OrderList registerOrderList(OrderList orderList) {
		return orderListRepository.save(orderList);
	}

	public List<OrderList> loadOrderList(int resId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(resId);
		Restaurant res = restaurant.get();
		List<OrderList> orders = orderListRepository.findAllByRestaurant(res);
		return orders;
	}

	/*
	 * public List<OrderList> loadWaitingOrderList(int userId){ Optional<User>
	 * user_optional = userRepository.findById(userId); User user =
	 * user_optional.get(); List<OrderList> orders =
	 * orderListRepository.findAllByUser(user); List<OrderList> userOrders = new
	 * ArrayList<OrderList>(); for(OrderList order : orders) {
	 * if(order.getOrderYN().equals("Y")) { userOrders.add(order); } } return
	 * userOrders; }
	 */
	// 유저가 주문 대기중인 리스트 가져오기
	public List<OrderDto> loadUserWaitingOrderList(int userId) {
		Optional<User> user_optional = userRepository.findById(userId);
		User user = user_optional.get();
		List<OrderList> orderLists = orderListRepository.findAllByUser(user);
		List<OrderList> userOrders = new ArrayList<OrderList>();
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (OrderList order : orderLists) {
			if (order.getServingYN() != null) {
				if (order.getServingYN().equals("N"))
					userOrders.add(order);
			}
		}
		for (int i = 0; i < userOrders.size(); i++) {
			OrderDto order = new OrderDto();
			OrderList orderList = userOrders.get(i);
			int orderId = orderList.getOrderId();
			int resId = orderList.getRestaurant().getResId();
			Optional<Restaurant> res_ = restaurantRepository.findById(resId);
			Restaurant res = res_.get();
			// orderMenu 가져오기
			List<OrderMenu> menuList = orderMenuService.loadOrderMenu(orderId);
			List<OrderMenuDto> menuDtoList = new ArrayList<OrderMenuDto>();
			List<ResMenuDto> resMenuList = new ArrayList<ResMenuDto>();
			// resMenu 가져오기
			for (int j = 0; j < menuList.size(); j++) {
				ResMenuDto resMenuDto = new ResMenuDto();
				OrderMenuDto orderMenuDto = new OrderMenuDto();
				int resMenuId = menuList.get(j).getResMenu().getResMenuId();
				Optional<ResMenu> resMenu_ = resMenuRepository.findById(resMenuId);
				ResMenu resMenu = resMenu_.get();
				resMenuDto.setName(resMenu.getName());
				resMenuDto.setPrice(resMenu.getPrice());
				resMenuList.add(resMenuDto);
				// orderMenuDto
				orderMenuDto.setAmount(menuList.get(j).getAmount());
				menuDtoList.add(orderMenuDto);
			}
			// orderMenu -> orderMenuDto
			// order에 데이터 넣어줌
			order.setResId(resId);
			order.setResName(res.getResName());
			order.setImage(res.getImage());
			order.setRequest(orderList.getRequest());
			order.setOrderTime(orderList.getOrderTime());
			order.setServingTime(orderList.getServingTime());
			order.setOrderMenus(menuDtoList);
			order.setResMenus(resMenuList);
			order.setTableNumber(orderList.getTableNumber());
			orders.add(order);
		}
		return orders;
	}

	// 레스토랑이 주문 대기중인 리스트 가져오기
	public List<OrderDto> loadResWaitingOrderList(int resId) {
		Optional<Restaurant> res_optional = restaurantRepository.findById(resId);
		Restaurant restaurant = res_optional.get();
		List<OrderList> orderLists = orderListRepository.findAllByRestaurant(restaurant);
		List<OrderList> userOrders = new ArrayList<OrderList>();
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (OrderList order : orderLists) {
			if (order.getOrderYN().equals("Y")) {
				userOrders.add(order);
			}
		}
		for (int i = 0; i < orderLists.size(); i++) {
			OrderDto order = new OrderDto();
			OrderList orderList = orderLists.get(i);
			int orderId = orderList.getOrderId();
			// orderMenu 가져오기
			List<OrderMenu> menuList = orderMenuService.loadOrderMenu(orderId);
			List<OrderMenuDto> menuDtoList = new ArrayList<OrderMenuDto>();
			List<ResMenuDto> resMenuList = new ArrayList<ResMenuDto>();
			// resMenu 가져오기
			for (int j = 0; j < menuList.size(); j++) {
				ResMenuDto resMenuDto = new ResMenuDto();
				OrderMenuDto orderMenuDto = new OrderMenuDto();
				int resMenuId = menuList.get(j).getResMenu().getResMenuId();
				Optional<ResMenu> resMenu_ = resMenuRepository.findById(resMenuId);
				ResMenu resMenu = resMenu_.get();
				resMenuDto.setName(resMenu.getName());
				resMenuDto.setPrice(resMenu.getPrice());
				resMenuList.add(resMenuDto);
				// orderMenuDto
				orderMenuDto.setAmount(menuList.get(j).getAmount());
				menuDtoList.add(orderMenuDto);
			}
			// orderMenu -> orderMenuDto
			// order에 데이터 넣어줌
			order.setOrderId(orderList.getOrderId());
			order.setRequest(orderList.getRequest());
			order.setOrderTime(orderList.getOrderTime());
			order.setServingTime(orderList.getServingTime());
			order.setOrderMenus(menuDtoList);
			order.setResMenus(resMenuList);
			order.setTableNumber(orderList.getTableNumber());
			order.setCheckYN(orderList.getCheckYN());
			order.setServingYN(orderList.getServingYN());
			orders.add(order);
		}
		return orders;
	}

	// 점주가 주문확인 버튼 눌렀을때 checkYN -> Y
	public OrderList setCheckOrder(OrderList orderList) {
		orderList.setCheckYN("Y");
		return orderListRepository.save(orderList);
	}

	// 점주가 서빙완료 버튼 눌렀을 때 servingYN -> Y / servingTime 최신화
	public OrderList setServingOrder(OrderList orderList) {
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		String time = date.format(today);
		orderList.setServingTime(time);
		orderList.setServingYN("Y");
		return orderListRepository.save(orderList);
	}

	// 서빙 완료된 orderDto 반환하기
	public List<OrderDto> loadCompleteOrder(int userId) {
		Optional<User> user_optional = userRepository.findById(userId);
		User user = user_optional.get();
		List<OrderList> orderLists = orderListRepository.findAllByUser(user);
		List<OrderList> userOrders = new ArrayList<OrderList>();
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (OrderList order : orderLists) {
			if ((order.getCheckYN().equals("Y"))) {
				if (order.getServingYN().equals("Y"))
					userOrders.add(order);
			}
		}
		for (int i = 0; i < userOrders.size(); i++) {
			OrderDto order = new OrderDto();
			OrderList orderList = userOrders.get(i);
			int orderId = orderList.getOrderId();
			int resId = orderList.getRestaurant().getResId();
			Optional<Restaurant> res_ = restaurantRepository.findById(resId);
			Restaurant res = res_.get();
			// orderMenu 가져오기
			List<OrderMenu> menuList = orderMenuService.loadOrderMenu(orderId);
			List<OrderMenuDto> menuDtoList = new ArrayList<OrderMenuDto>();
			List<ResMenuDto> resMenuList = new ArrayList<ResMenuDto>();
			// resMenu 가져오기
			for (int j = 0; j < menuList.size(); j++) {
				ResMenuDto resMenuDto = new ResMenuDto();
				OrderMenuDto orderMenuDto = new OrderMenuDto();
				int resMenuId = menuList.get(j).getResMenu().getResMenuId();
				Optional<ResMenu> resMenu_ = resMenuRepository.findById(resMenuId);
				ResMenu resMenu = resMenu_.get();
				resMenuDto.setName(resMenu.getName());
				resMenuDto.setPrice(resMenu.getPrice());
				resMenuList.add(resMenuDto);
				// orderMenuDto
				orderMenuDto.setAmount(menuList.get(j).getAmount());
				menuDtoList.add(orderMenuDto);
			}
			// orderMenu -> orderMenuDto
			// order에 데이터 넣어줌
			order.setOrderId(orderId);
			order.setResId(resId);
			order.setResName(res.getResName());
			order.setImage(res.getImage());
			order.setRequest(orderList.getRequest());
			order.setOrderTime(orderList.getOrderTime());
			order.setServingTime(orderList.getServingTime());
			order.setReviewYN(orderList.getReviewYN());
			order.setOrderMenus(menuDtoList);
			order.setResMenus(resMenuList);
			order.setTableNumber(orderList.getTableNumber());
			orders.add(order);
		}
		return orders;
	}
	// resId와 tableNum으로 orderId 찾기
	public OrderList findOrderList(int resId, int tableNum) {
		Optional<Restaurant> res_o = restaurantRepository.findById(resId);
		Restaurant res = res_o.get();
		List<OrderList> orderListArray = orderListRepository.findAllByRestaurant(res);
		OrderList orderList = new OrderList();
		for (OrderList order : orderListArray) {
			if (order.getTableNumber() == tableNum) {
				orderList = order;
				break;
			}
		}
		return orderList;
	}
	public String checkReviewYN(int orderId) {
		OrderList order = orderListRepository.getById(orderId);
		if(order.getReviewYN().equals("Y")) {
			return "Y";
		}else {
			return "N";
		}
	}
	
	public String setReviewYN(int orderId) {
		OrderList order = orderListRepository.getById(orderId);
		order.setReviewYN("Y");
		orderListRepository.save(order);
		return "OK";
	}
}
