package com.task.student.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.student.dto.StudentDto;
import com.task.student.entity.Student;
import com.task.student.entity.Subject;
import com.task.student.response.ResponseMessage;
import com.task.student.service.StudentService;

@SpringBootTest
class TestController {

	@Mock
	private StudentService service;

	@InjectMocks
	private StudentController controller;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mapper = new ObjectMapper();
	}

	@Test
	void testAddStudent() throws UnsupportedEncodingException, Exception {
		List<Subject> subjectsList = new ArrayList<>();
		Subject subject = new Subject(100l, "Hindi", 80);
		Subject subjectTwo = new Subject(101l, "English", 90);
		subjectsList.add(subject);
		subjectsList.add(subjectTwo);
		StudentDto studentDto = new StudentDto("Ruturaj", 27, "Mumbai", subjectsList);
		Student student = new Student();
		student.setStudentId(100);
		BeanUtils.copyProperties(studentDto, student);
		Mockito.when(service.addStudent(Mockito.any())).thenReturn(student);
		String jsonContent = mapper.writeValueAsString(student);
		String result = mockMvc
				.perform(post("/student/addStudent").content(jsonContent).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		ResponseMessage message = mapper.readValue(result, ResponseMessage.class);
		assertEquals(Boolean.FALSE, message.isError());
	}

	@Test
	void testFetchAllStudents() throws UnsupportedEncodingException, Exception {
		List<Subject> subjectsList = new ArrayList<>();
		Subject subject = new Subject(100l, "Hindi", 80);
		Subject subjectTwo = new Subject(101l, "English", 90);
		subjectsList.add(subject);
		subjectsList.add(subjectTwo);
		Student student = new Student(100, "Virat", 32, "Delhi", subjectsList);
		Student studentTwo = new Student(101, "Rahul", 34, "Mumbai", subjectsList);
		List<Student> studentList = new ArrayList<>();
		studentList.add(student);
		studentList.add(studentTwo);
		Mockito.when(service.getAllStudents()).thenReturn(studentList);
		String jsonContent = mapper.writeValueAsString(studentList);
		String result = mockMvc
				.perform(get("/student/getAllStudents").content(jsonContent)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseMessage message = mapper.readValue(result, ResponseMessage.class);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> data = (List<Map<String, String>>) message.getData();
		for (Map.Entry<String, String> mapData : data.get(0).entrySet()) {
			assertEquals(student.getStudentId(), mapData.getValue());
			break;
		}
	}

}
