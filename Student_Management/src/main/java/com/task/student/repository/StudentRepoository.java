package com.task.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.student.entity.Student;

public interface StudentRepoository extends JpaRepository<Student, Long> {

}
