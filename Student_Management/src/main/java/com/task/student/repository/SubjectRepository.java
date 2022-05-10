package com.task.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.student.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
