package com.letseat.model.restaurant;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ResMenu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resMenuId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int price;
	
	@Lob
	@Column(length = 10000)
	private String photo;
	
	@Column
	private String excription;
	
	@ManyToOne
	@JoinColumn(name = "resId", referencedColumnName = "resId")
	private Restaurant restaurant;
	
	@CreationTimestamp
	private Timestamp createDate;
}
