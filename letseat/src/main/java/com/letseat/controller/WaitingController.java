package com.letseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letseat.dto.WaitingDto;
import com.letseat.model.waiting.Waiting;
import com.letseat.service.WaitingService;

@RestController
public class WaitingController {
	@Autowired
	WaitingService waitingService;
	// 웨이팅 등록
	@PostMapping("/waiting/register")
	public Waiting registerWaiting(@RequestBody WaitingDto waitingDto) {
		return waitingService.register(waitingDto);
	}
	// 레스토랑 현재 대기자 가져오기
	@GetMapping("/waiting/res/load")
	public List<Waiting> getResWaiting(@RequestParam int resId){
		return waitingService.getResWaiting(resId);
	}
	// 유저에서 웨이팅 순번 확인하기
	@GetMapping("/waiting/user/load")
	public Waiting getUserWaiting(@RequestParam int userId) {
		return waitingService.checkUserWaiting(userId);
	}
	// 레스토랑에서 웨이팅 순번 처리
	@GetMapping("/waiting/res/process")
	public String resProcessWaiting(@RequestParam int resId) {
		return waitingService.processWaiting(resId);
	}
	// 레스토랑에서 마지막 대기자 번호 가져오기
	@GetMapping("/waiting/res/get/lastWaiting")
	public String getLastResWaiting(@RequestParam int resId) {
		return waitingService.getLastResWaiting(resId);
	}
}
