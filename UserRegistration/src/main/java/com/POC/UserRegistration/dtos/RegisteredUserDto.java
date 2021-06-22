package com.POC.UserRegistration.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisteredUserDto {
	long id;
	String name;
	String message;

}
