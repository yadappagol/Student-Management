package com.task.student.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.student.dto.StudentDto;
import com.task.student.entity.Student;
import com.task.student.repository.StudentRepoository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepoository studentRepoository;

	@Override
	public Student addStudent(StudentDto studentDto) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		return studentRepoository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepoository.findAll();
	}

}
