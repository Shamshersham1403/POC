package com.poc.studentSecurity.service;

import java.util.List;

import com.poc.studentSecurity.dto.ProjectResponseDto;
import com.poc.studentSecurity.model.Project;

public interface ProjectService {

	ProjectResponseDto AddProject(Project project);

	List<ProjectResponseDto> getAllPOrjects();

	ProjectResponseDto getById(long id);

}
