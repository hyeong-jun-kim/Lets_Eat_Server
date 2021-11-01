package com.letseat.model.waiting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Waiting {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int waitingId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "resId")
	private Restaurant restaurant;
	
	@Column
	private int waitingNumber;
	
	@Column
	private int peopleNum;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String date;
	
	@Column
	private int finish;
}
