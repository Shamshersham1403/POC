package com.poc.studentSecurity.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.studentSecurity.dto.ProjectResponseDto;
import com.poc.studentSecurity.model.Project;
import com.poc.studentSecurity.service.ProjectService;

@RestController
@RequestMapping("project")
public class ProjectController {

	@Autowired
	ProjectService projectService;


	@PostMapping("insert")
	public ResponseEntity<ProjectResponseDto> addProject(@RequestBody Project project) {
		System.out.println(project);
		ProjectResponseDto addProject = projectService.AddProject(project);
		return new ResponseEntity<ProjectResponseDto>(addProject, HttpStatus.OK);
	}
	@GetMapping("getAll")
	public ResponseEntity<List<ProjectResponseDto>> getAllProject(){
		return new ResponseEntity<List<ProjectResponseDto>>(projectService.getAllPOrjects(),HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getProjectById(@PathParam("projId") long projId){
		
		return new ResponseEntity<>(projectService.getById(projId),HttpStatus.OK);
	}

}
