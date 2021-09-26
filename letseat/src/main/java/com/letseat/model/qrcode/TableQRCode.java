package com.letseat.model.qrcode;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.letseat.model.restaurant.ResType;
import com.letseat.model.restaurant.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TableQRCode {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tableQRid;
	
	@Column(nullable = false)
	private int tableNumber;
	
	@ManyToOne
	@JoinColumn(name="resId")
	private Restaurant restaurant;
	
	@CreationTimestamp
	private Timestamp createDate;
}