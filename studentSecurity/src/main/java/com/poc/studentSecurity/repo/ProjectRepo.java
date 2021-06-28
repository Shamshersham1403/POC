package com.poc.studentSecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.studentSecurity.model.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>{

}
