package com.letseat.model.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.letseat.model.restaurant.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
public class Owner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ownerId;
	
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
