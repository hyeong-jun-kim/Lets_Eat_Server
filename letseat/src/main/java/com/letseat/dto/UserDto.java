package com.letseat.dto;

import com.letseat.model.user.ApiType;
import com.letseat.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserDto {
	private String email;
	private String password;
	private String name;
	private String birthday;
	private ApiType api_token;
	private String gender;
	public User toEntity() {
		return User.builder().email(email).password(password).name(name)
				.birthday(birthday).gender(gender)
				.build();
	}
}
