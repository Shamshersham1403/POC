package com.POC.UserRegistration.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.POC.UserRegistration.dtos.RegisteredUserDto;
import com.POC.UserRegistration.models.UserInfo;
import com.POC.UserRegistration.repository.UserInfoRepo;
import com.POC.UserRegistration.service.UserInfoService;
import com.POC.UserRegistration.util.Messages;
import com.POC.UserRegistration.util.Status;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	UserInfoRepo userInfoRepo;

	@Override
	public RegisteredUserDto SaveUser(UserInfo userInfo) {
		RegisteredUserDto newUser = new RegisteredUserDto();
		String Valid = this.checkValidation(userInfo);
		if (!Valid.equals(Messages.SUCCESS)) {
			newUser.setMessage(Valid);
			return newUser;
		}
		UserInfo user = this.userInfoRepo.save(userInfo);

		newUser.setId(user.getId());
		newUser.setName(user.getFirstName() + " " + user.getLastName());
		newUser.setMessage(Messages.SUCCESS);
		return newUser;

	}

	@Override
	public List<UserInfo> GetAllUSer() {
		// TODO Auto-generated method stub

		return this.userInfoRepo.findByStatus(Status.ACTIVE);
	}

	@Override
	public String DeleteUser(long id, int status) {
		// TODO Auto-generated method stub

		Optional<UserInfo> userInfoOp = userInfoRepo.findById(id);
		if (userInfoOp.isPresent()) {
			UserInfo deletedUser = userInfoOp.get();

			if (status == Status.SOFTDELETE) {
				deletedUser.setStatus(status);
				userInfoRepo.save(deletedUser);
				return "User Deleted Successfully";
			} else {
				userInfoRepo.delete(deletedUser);
				return "User Deleted Permanently";
			}

		} else
			return "User Not Found";
	}

	private String checkValidation(UserInfo userInfo) {
		// First Name validation
		String firstName = userInfo.getFirstName();

		if (firstName == "" || firstName == null) {
			return Messages.EMPTY_FIRST_NAME;
		}
		if (!firstName.matches("^[a-zA-Z]*$")) {
			return Messages.SPECIAL_CHAR_NOT_ALLOWED;
		}
		// Last Name validation
		String lastName = userInfo.getLastName();

		if (lastName == "" || lastName == null) {
			return Messages.EMPTY_LAST_NAME;
		}
		if (!lastName.matches("^[a-zA-Z]*$")) {
			return Messages.SPECIAL_CHAR_NOT_ALLOWED;
		}

		if (userInfo.getDob().after(userInfo.getDoj())) {
			return "DOB Should be Before DOJ";
		}

		return Messages.SUCCESS;

	}

	@Override
	public List<UserInfo> GetSortedUsers(String sortKey) {
		// TODO Auto-generated method stub
		if(sortKey=="" || sortKey==null) {
			System.out.println("SortKey is empty");
			return new ArrayList<>();
		}
		String[] split = sortKey.split("-");
		System.out.println(split[0]+" "+split[1]);
		if(split[0].equals("DOB")) {
			if(split[1].equals("A")) {
			
				List<UserInfo> userList = userInfoRepo.findAllByStatusOrderByDobAsc(Status.ACTIVE);
				userList.stream().forEach(a->System.out.println(a));
				return userList;
			}else {
				List<UserInfo> userList = userInfoRepo.findAllByStatusOrderByDobDesc(Status.ACTIVE);
				return userList;
			}
		}else {
			if(split[1].equals("A")) {
				
				List<UserInfo> userList = userInfoRepo.findAllByStatusOrderByDojAsc(Status.ACTIVE);
				userList.stream().forEach(a->System.out.println(a));
				return userList;
			}else {
				List<UserInfo> userList = userInfoRepo.findAllByStatusOrderByDojDesc(Status.ACTIVE);
				return userList;
			}
		}
		

	}
	
	
}
