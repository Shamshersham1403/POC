package com.poc.studentSecurity.dto;

import com.poc.studentSecurity.model.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectResponseDto {
	private long projid;
	private String projname;
	private long duration;
	private String msg;
	
	public ProjectResponseDto(Project project){
		this.projid=project.getProjid();
		this.projname=project.getProjname();
		this.duration=project.getDuration();
		this.msg="Success";
	}
}
