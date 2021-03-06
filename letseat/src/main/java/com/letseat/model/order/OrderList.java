package com.letseat.model.order;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "resId")
	private Restaurant restaurant;
	
	@Column
	private int tableNumber;
	
	@Column
	private int sum;
	
	@Column
	private String checkYN;
	
	@Column
	private String servingYN;
	
	@Column
	private String orderYN;
	
	@Column
	private String reviewYN;
	
	@Column(length = 300)
	private String request;
	
	@Column(length = 30)
	private String orderTime; 
	
	@Column(length = 30)
	private String servingTime;
	
	@CreationTimestamp
	private Timestamp createDate;
}
