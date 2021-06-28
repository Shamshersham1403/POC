package com.poc.studentSecurity.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.studentSecurity.dto.ProjectResponseDto;
import com.poc.studentSecurity.model.Project;
import com.poc.studentSecurity.repo.ProjectRepo;
import com.poc.studentSecurity.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepo projectRepo;

	@Override
	public ProjectResponseDto AddProject(Project project) {
		ProjectResponseDto projectDto= new ProjectResponseDto();
		if(project.getProjname() != null && project.getDuration() != 0) {
			Project _project = projectRepo.save(project);
			projectDto.setDuration(_project.getDuration());
			projectDto.setProjid(_project.getProjid());
			projectDto.setProjname(_project.getProjname());
			projectDto.setMsg("Project Add Successfully");
			return projectDto;
		}
		projectDto.setMsg("Project Not Add Successfully");
		return projectDto;
		
	}

	@Override
	public List<ProjectResponseDto> getAllPOrjects() {
		// TODO Auto-generated method stub
		List<Project> projects = projectRepo.findAll();
		List<ProjectResponseDto> projectList = projects.stream().map(project->new ProjectResponseDto(project)).collect(Collectors.toList());
		return projectList;
	}

	@Override
	public ProjectResponseDto getById(long id) {
		Project project = projectRepo.getById(id);
		ProjectResponseDto projectResponseDto=new ProjectResponseDto(project);
		return projectResponseDto;
	}
	
	
}
