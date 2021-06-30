package com.poc.studentSecurity.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poc.studentSecurity.dto.StudentResponseDto;
import com.poc.studentSecurity.model.Student;
import com.poc.studentSecurity.repo.StudentRepo;
import com.poc.studentSecurity.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public StudentResponseDto addStudent(Student student) {
		// TODO Auto-generated method stub
		StudentResponseDto studentDto = new StudentResponseDto();
			
		if (student.getFirstName() != null && student.getLastName() != null && student.getMobNo() != null
				) {
			student.setPassword(passwordEncoder.encode(student.getPassword()));
			Student _student = studentRepo.save(student);
			studentDto.setId(_student.getId());
			studentDto.setFullName(_student.getFirstName()+ " "+_student.getLastName());
			studentDto.setEmail(_student.getEmail());
			studentDto.setMobNo(_student.getMobNo());
			studentDto.setPhoto(_student.getPhoto());
			studentDto.setMsg("Student Added Successfully");
			return studentDto;
			

		}
		studentDto.setMsg("Student Not Added Successfully");
		return studentDto;
	}
	

	@Override
	public List<StudentResponseDto> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = studentRepo.findAll();
		List<StudentResponseDto> studentList = students.stream().map(student->new StudentResponseDto(student)).collect(Collectors.toList());
		return studentList;
	}

}
