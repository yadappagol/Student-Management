package com.task.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.student.dto.StudentDto;
import com.task.student.entity.Student;
import com.task.student.exception.StudentNotFoundException;
import com.task.student.response.ResponseMessage;
import com.task.student.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

	@Autowired
	private StudentService studentService;

	// http://localhost:8080/student/addStudent

	/**
	 * @param studentDto
	 * @return It will insert the the student details into database
	 */
	@PostMapping("/addStudent")
	public ResponseEntity<ResponseMessage> addStudent(@RequestBody StudentDto studentDto) {
		if (studentDto != null) {
			log.info(studentDto + "  Students Details Inserted Successfully..");
			studentService.addStudent(studentDto);
			return new ResponseEntity<>(new ResponseMessage(false, "Student Details Inserted Successfully", studentDto),
					HttpStatus.CREATED);
		} else {
			log.error("Students Details not inserted!!!");
			throw new StudentNotFoundException("Student Details not Inserted!!");
		}
	}

	// http://localhost:8080/student/getAllStudents

	/**
	 * @return It Will return All Student Details with his subjects information.
	 */
	@GetMapping("/getAllStudents")
	public ResponseEntity<ResponseMessage> getAllStudents() {
		List<Student> allStudents = studentService.getAllStudents();
		if (!allStudents.isEmpty()) {
			log.info("Students Details Fetched Successfully..");
			return new ResponseEntity<>(
					new ResponseMessage(false, "All Students details fetched successfully", allStudents),
					HttpStatus.OK);
		} else {
			log.error("Students Details not Found!!!");
			throw new StudentNotFoundException("Student Details not found!!");
		}
	}

}
