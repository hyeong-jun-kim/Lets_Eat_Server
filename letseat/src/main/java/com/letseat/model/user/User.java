package com.letseat.model.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(length=10)
	private String gender;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
