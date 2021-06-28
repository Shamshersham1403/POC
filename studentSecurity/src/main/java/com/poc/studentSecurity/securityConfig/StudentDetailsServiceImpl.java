package com.poc.studentSecurity.securityConfig;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.poc.studentSecurity.model.Student;
import com.poc.studentSecurity.repo.StudentRepo;

public class StudentDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private StudentRepo studentRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = studentRepo.getByEmail(username);
		if (student == null) {
			throw new UsernameNotFoundException(username);
		}

		StudentDetails studentDetails = new StudentDetails(student);
		return studentDetails;
	}

}
