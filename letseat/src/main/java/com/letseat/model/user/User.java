package com.letseat.model.user;

import java.sql.Timestamp;
import java.time.LocalDate;

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
	private int userId;
	
	@Column(nullable = false, length =30)
	private String email;
	
	@Column(nullable = false,length=20)
	private String password;
	
	@Column(nullable = false,length=20)
	private String name;
	
	@Column(nullable = false,length=20)
	private String birthday;
	
	@Enumerated(EnumType.STRING)
	private ApiType api_token;
	
	@Column(length=10)
	private String gender;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
