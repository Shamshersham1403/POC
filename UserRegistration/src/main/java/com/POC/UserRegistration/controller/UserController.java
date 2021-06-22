package com.POC.UserRegistration.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.POC.UserRegistration.models.UserInfo;
import com.POC.UserRegistration.service.UserInfoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	UserInfoService userInfoService;

	@GetMapping("/")
	public String Welcome() {
		return "";
	}

	@PostMapping("/user")
	public ResponseEntity<?> SaveUser(@RequestBody UserInfo userInfo) {
		System.out.println(userInfo);
		return new ResponseEntity<>(userInfoService.SaveUser(userInfo), HttpStatus.OK);

	}

	@GetMapping("/user")
	public ResponseEntity<?> GetAllUsers() {
		return new ResponseEntity<>(userInfoService.GetAllUSer(), HttpStatus.OK);
	}
	@GetMapping("/user/sort")
	public ResponseEntity<?> GetSortedUsers(@RequestParam("sortKey") String sortKey) {
		return new ResponseEntity<>(userInfoService.GetSortedUsers(sortKey), HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> DeleteUser(@PathVariable("id") long id, @PathParam("status") int status) {
		System.out.println("user id " + id + " Deleted" + status);

		return new ResponseEntity<>(userInfoService.DeleteUser(id, status), HttpStatus.OK);

	}
}
