package com.task.student.dto;

import java.util.List;

import com.task.student.entity.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
	private String name;
	private int age;
	private String address;

	private List<Subject> subjects;
}
