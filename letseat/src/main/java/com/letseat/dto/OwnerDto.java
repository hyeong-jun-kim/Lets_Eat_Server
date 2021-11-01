package com.letseat.dto;

import java.util.ArrayList;
import java.util.List;

import com.letseat.model.restaurant.Restaurant;
import com.letseat.model.user.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OwnerDto {
	private String email;
	private String password;
	private String name;
	private String birthday;
	private String gender;
	public Owner toEntity() {
		return Owner.builder().email(email).password(password).name(name)
				.birthday(birthday).gender(gender)
				.build();
	}
}
