package com.letseat.model.restaurant;

import java.sql.Blob;

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
public class ResMenu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resMenuId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int price;
	
	@Column
	private Blob photo;
	
	@Column
	private String excription;
	
	@ManyToOne
	@JoinColumn(name = "resId", referencedColumnName = "resId")
	private Restaurant restaurant;
}
