package com.letseat.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letseat.dto.WaitingDto;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;
import com.letseat.model.waiting.Waiting;
import com.letseat.repository.RestaurantRepository;
import com.letseat.repository.UserRepository;
import com.letseat.repository.WaitingRepository;

@Service
@Transactional
public class WaitingService {
	@Autowired
	WaitingRepository waitingRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	UserRepository userRepository;
	// 웨이팅 등록하기
	public Waiting register(WaitingDto waitingDto) {
		Restaurant res = waitingDto.getRestaurant();
		int waitingNumber = getWaitingNumber(res);
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		String time = date.format(today);
		if (waitingDto.getPeopleNum() == 0)
			waitingDto.setPeopleNum(0);
		waitingDto.setFinish(0);
		waitingDto.setDate(time);
		waitingDto.setWaitingNumber(waitingNumber);
		Waiting waiting = waitingDto.toEntity();
		return waitingRepository.save(waiting);
	}
	// 레스토랑 현재 전체 대기자 가져오기
	public List<Waiting> getResWaiting(int resId) {
		Restaurant res = restaurantRepository.getById(resId);
		List<Waiting> resWaiting = waitingRepository.findAllByRestaurant(res);
		List<Waiting> resWaitingList = new ArrayList<Waiting>();
		for (int i = 0; i < resWaiting.size(); i++) {
			if ((resWaiting.get(i).getFinish() == 0)
					&& resWaiting.get(i).getWaitingNumber() > 0) {
				resWaitingList.add(resWaiting.get(i));
			}
		}
		return resWaitingList;
	}
	// 웨이팅 넘버 가져오기
	public int getWaitingNumber(Restaurant res) {
		List<Waiting> waitingList = waitingRepository.findAllByRestaurant(res);
		int max = 0;
		for (int i = 0; i < waitingList.size(); i++) {
			int num = waitingList.get(i).getWaitingNumber();
			if (max < num) {
				max = num;
			}
		}
		return max+1;
	}
	// 유저에서 웨이팅 순번 확인하기
	public Waiting checkUserWaiting(int userId) {
		User user = userRepository.getById(userId);
		List<Waiting> waitingList = waitingRepository.findAllByUser(user);
		Waiting waiting = new Waiting();
		for (int i = 0; i < waitingList.size(); i++) {
			if (waitingList.get(i).getFinish() != 1) {
				waiting = waitingList.get(i);
				break;
			}
		}
		return waiting;
	}
	// 웨이팅 순번 처리 함수
	public void processWaitingNumber(Restaurant res) {
		List<Waiting> waitingList = waitingRepository.findAllByRestaurant(res);
		for (int i = 0; i < waitingList.size(); i++) {
			Waiting waiting = waitingList.get(i);
			if (waiting.getFinish() == 0) {
				int count = waiting.getWaitingNumber();
				if (count > 0) {
					waiting.setWaitingNumber(count - 1);
					waitingRepository.save(waiting);
				}
			}
		}
	}

	// 레스토랑 웨이팅 순번 처리
	public String processWaiting(int resId) {
		Restaurant res = restaurantRepository.getById(resId);
		List<Waiting> waitingList = waitingRepository.findAllByRestaurant(res);
		Waiting waiting = new Waiting();
		int count = 0;
		if (waitingList.isEmpty())
			return null;
		for (int i = 0; i < waitingList.size(); i++) {
			count = waitingList.get(i).getWaitingNumber();
			if (count == 1) {
				waiting = waitingList.get(i);
				break;
			}
		}
		if(count != 1)
			return null;
		// 웨이팅 순번 처리
		processWaitingNumber(res);
		waiting.setWaitingNumber(0);
		waiting.setFinish(1);
		waitingRepository.save(waiting);
		return "OK";
	}
	// 레스토랑 마지막 대기자 번호 가져오기
	public String getLastResWaiting(int resId) {
		Restaurant res = restaurantRepository.getById(resId);
		List<Waiting> waitingList = waitingRepository.findAllByRestaurant(res);
		int max = 0;
		for(int i = 0; i < waitingList.size(); i++) {
			Waiting waiting = waitingList.get(i);
			if(waiting.getFinish() == 0) {
				if(waiting.getWaitingNumber() > max)
					max = waiting.getWaitingNumber();
			}
		}
		return String.valueOf(max);
	}
}
