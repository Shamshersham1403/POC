package com.poc.studentSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poc.studentSecurity.model.Student;
import com.poc.studentSecurity.repo.StudentRepo;

@Service(value = "userDetailsService")
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
