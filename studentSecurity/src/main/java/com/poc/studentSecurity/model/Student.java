package com.poc.studentSecurity.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private  String mobNo;
	private String email;
	private String password;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Project> project=new ArrayList<Project>();
	@Lob
	private byte[] photo;
}
