package com.poc.studentSecurity.dto;

import com.poc.studentSecurity.model.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentResponseDto {
	
	private long id;
	private String fullName;
	private  String mobNo;
	private String email;
	private byte[] photo;
	private String msg;
	
	public StudentResponseDto(Student student) {
		this.id=student.getId();
		this.fullName=student.getFirstName()+ " "+ student.getLastName();
		this.mobNo=student.getMobNo();
		this.email=student.getEmail();
//		this.photo=student.getPhoto();
		this.msg="success";
	}
}
