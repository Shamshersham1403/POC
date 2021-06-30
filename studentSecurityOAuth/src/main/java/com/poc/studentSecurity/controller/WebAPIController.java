package com.poc.studentSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAPIController {


	@GetMapping("/")
	public String Welcome() {
		return "Welcome to the POC task2";
	}
}
