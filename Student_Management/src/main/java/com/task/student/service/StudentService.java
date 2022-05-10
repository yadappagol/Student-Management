package com.task.student.service;

import java.util.List;

import com.task.student.dto.StudentDto;
import com.task.student.entity.Student;

public interface StudentService {

	Student addStudent(StudentDto studentDto);

	List<Student> getAllStudents();

}
