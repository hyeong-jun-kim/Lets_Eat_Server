package com.letseat.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=20)
	private String userid;
	
	@Column(nullable=false, length=20)
	private String name;
	
	@Column(nullable=false, length=20)
	private String password;
	
	@Column(nullable=false)
	private int phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private GenderType gender;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
