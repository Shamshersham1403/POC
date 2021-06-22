package com.POC.UserRegistration.service;

import java.util.List;

import com.POC.UserRegistration.dtos.RegisteredUserDto;
import com.POC.UserRegistration.models.UserInfo;

public interface UserInfoService {

	RegisteredUserDto SaveUser(UserInfo userInfo);

	List<UserInfo> GetAllUSer();

	String DeleteUser(long id, int status);
	
	List<UserInfo> GetSortedUsers(String sortKey);

}
