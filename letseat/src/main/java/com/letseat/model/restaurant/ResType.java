package com.letseat.model.restaurant;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ResType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resTypeId;

	@CreationTimestamp
	private Timestamp createDate;
	
	@Column(length = 10)
	private String foodStyle;
}
