package com.task.student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.student.dto.StudentDto;
import com.task.student.entity.Student;
import com.task.student.entity.Subject;
import com.task.student.repository.StudentRepoository;

@SpringBootTest
class TestService {

	@Mock
	private StudentRepoository studentRepository;

	@InjectMocks
	private StudentServiceImpl service;

	@Test
	void testAddStudent() {
		List<Subject> subjectsList = new ArrayList<>();
		Subject subject = new Subject(100l, "Hindi", 80);
		Subject subjectTwo = new Subject(101l, "English", 90);
		subjectsList.add(subject);
		subjectsList.add(subjectTwo);
		StudentDto studentDto = new StudentDto("Ruturaj", 27, "Mumbai", subjectsList);
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		Mockito.when(studentRepository.save(Mockito.any())).thenReturn(student);
		assertEquals(studentDto.getName(), service.addStudent(studentDto).getName());
	}

	@Test
	void testFetchAllStudents() {
		List<Subject> subjectsList = new ArrayList<>();
		Subject subject = new Subject(100l, "Hindi", 80);
		Subject subjectTwo = new Subject(101l, "English", 90);
		subjectsList.add(subject);
		subjectsList.add(subjectTwo);
		Student student = new Student(100, "Virat", 32, "Delhi", subjectsList);
		Student studentTwo = new Student(101, "Rahul", 34, "Mumbai", subjectsList);
		List<Student> studentsList = new ArrayList<>();
		studentsList.add(student);
		studentsList.add(studentTwo);
		Mockito.when(studentRepository.findAll()).thenReturn(studentsList);
		assertEquals(2, service.getAllStudents().size());
	}

}
