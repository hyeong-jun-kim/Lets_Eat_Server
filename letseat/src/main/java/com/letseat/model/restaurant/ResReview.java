package com.letseat.model.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.letseat.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ResReview {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reviewId;
	
	@Column
	private String menuName;
	
	@Column
	private String content;
	
	@Column
	private String date;
	
	@Column
	private float rate;
	
	@Lob
	@Column(length = 10000)
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "resId")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
}
