package com.letseat.model.order;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.letseat.model.qrcode.ResQRCode;
import com.letseat.model.qrcode.TableQRCode;
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
	
	@Column(nullable = false)
	private int amount;
	
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "resId")
	private Restaurant restaurant;
	
	@OneToOne
	@JoinColumn(name = "tableQRId")
	private TableQRCode tableQRCode;
	
	@OneToOne
	@JoinColumn(name = "tableNumber", referencedColumnName = "tableNumber")
	private TableQRCode tableNumber;
	
	@Column(length = 300)
	private String request;
	
	@Column(nullable = false, length = 30)
	private String orderTime;
	
	@Column(nullable = false, length = 30)
	private String servingTime;
	
	@CreationTimestamp
	private Timestamp createDate;
}
