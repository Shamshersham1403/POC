package com.poc.studentSecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poc.studentSecurity.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{

	Student getByEmail(String username);


}
