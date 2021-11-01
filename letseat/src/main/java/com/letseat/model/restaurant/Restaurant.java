package com.letseat.model.restaurant;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.engine.jdbc.BlobImplementer;

import com.letseat.model.user.Owner;

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
	
	@Column(nullable = false, length = 20)
	private String phoneNumber;
	
	@Column(nullable = false, length = 40)
	private String openTime;
	
	@Column(nullable = false, length = 500)
	private String resIntro;
	
	@Column(nullable = false)
	private String businessNumber;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ResType restype;
	
	@Column(length=100, nullable = false)
	private String location;
	
	@Column(nullable = false)
	private int aloneAble;
	
	@Lob
	@Column(length = 10000)
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "onwerId")
	private Owner owner;
	
}
