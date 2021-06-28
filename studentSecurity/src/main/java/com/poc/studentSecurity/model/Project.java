package com.poc.studentSecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projid;
	private String projname;
	private long duration;
	@ManyToOne
	private Student student;
}
