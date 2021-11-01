package com.letseat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letseat.model.restaurant.ResMenu;
import com.letseat.model.restaurant.Restaurant;
import com.letseat.repository.OwnerRepository;
import com.letseat.repository.ResMenuRepository;

@Service
@Transactional
public class ResMenuService {
	@Autowired
	private ResMenuRepository resMenuRepository;
	/**
	 * 메뉴 등록
	 */
	public boolean register(ResMenu resMenu) {
		resMenuRepository.save(resMenu);
		return true;
	}
	public List<ResMenu> searchResMenu(Restaurant res){
		List<ResMenu> menus = resMenuRepository.findByrestaurant(res);
		return menus;
	}
}
