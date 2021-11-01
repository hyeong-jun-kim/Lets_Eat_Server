package com.letseat.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;
import com.letseat.model.waiting.Waiting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WaitingDto {
	private User user;
	private Restaurant restaurant;
	private int waitingNumber;
	private String date;
	private String phoneNumber;
	private int finish;
	private int peopleNum;

	public Waiting toEntity() {
		return Waiting.builder().user(user).restaurant(restaurant).waitingNumber(waitingNumber).date(date)
				.finish(finish).peopleNum(peopleNum).phoneNumber(phoneNumber).build();
	}
}
