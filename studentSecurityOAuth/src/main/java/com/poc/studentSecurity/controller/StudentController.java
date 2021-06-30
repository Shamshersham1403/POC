package com.poc.studentSecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.studentSecurity.dto.ProjectResponseDto;
import com.poc.studentSecurity.dto.StudentResponseDto;
import com.poc.studentSecurity.model.Student;
import com.poc.studentSecurity.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("insert")
	public ResponseEntity<StudentResponseDto> addStudent(@RequestBody Student student) {
		System.out.println(student);
		
		return new ResponseEntity<StudentResponseDto>(studentService.addStudent(student),HttpStatus.OK);
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<StudentResponseDto>> getAllStudent(){
		return new ResponseEntity<List<StudentResponseDto>>(studentService.getAllStudents(),HttpStatus.OK);
	}
	
}
