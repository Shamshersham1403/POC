package com.poc.studentSecurity.service;

import java.util.List;

import com.poc.studentSecurity.dto.StudentResponseDto;
import com.poc.studentSecurity.model.Student;

public interface StudentService {

	StudentResponseDto addStudent(Student student);

	List<StudentResponseDto> getAllStudents();



}
