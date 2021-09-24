package com.letseat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resId;
	
	@Column(nullable = false,length = 50)
	private String resName;
	
	@ManyToOne
	@JoinColumn(name="resUserId")
	private resUser resUser;
	
	@Column
	private int phoneNumber;
	
	@Column(length = 100)
	private String Location;
	
	@Column
	private int menuCode;
	
	@Column
	private int tableNumber;
	
	@Column 
	private int businessNumber;
	
	@Column(length = 500)
	private String resInfo; 
	
	
	@Column(length = 500)
	private String openInfo;
	
}
