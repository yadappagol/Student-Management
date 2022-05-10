package com.task.student.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Student_Details")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentId;
	@Column(name = "student_name")
	private String name;
	@Column(name = "student_age")
	private int age;
	@Column(name = "student_address")
	private String address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "studentId")
	private List<Subject> subjects;

}
