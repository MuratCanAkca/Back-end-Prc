package com.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.domain.Student;
import com.springboot.dto.StudentDTO;
import com.springboot.exception.ResourceNotFoundException;

public interface StudentService {

	List<Student> getAll();
	List<Student> findStudents(String lastName);
	Student findStudent(Long id) throws ResourceNotFoundException;
	void createStudent(Student student);
	void updateStudent(Long id,StudentDTO student);
	void deleteStudent(Long id);
	Page<Student> getAllWithPage(Pageable pageable);
	List<Student> findAllEqualsGrade(Integer grade);
	StudentDTO studentDTOById(Long id);

}
