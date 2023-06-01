package com.springboot.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springboot.domain.Student;
import com.springboot.domain.User;
import com.springboot.dto.StudentDTO;
import com.springboot.exception.BadRequestException;
import com.springboot.exception.ConflictException;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.repository.StudentRepository;
import com.springboot.repository.UserRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private UserRepository userRepository;
	
	@Autowired//Sadece bir tane constructor varsa autowıred yazmaya gerek yok
	public StudentServiceImpl(StudentRepository studentRepository , UserRepository userRepository) {
		this.studentRepository=studentRepository;
		this.userRepository=userRepository;
	}
	
	@Override
	public List<Student> getAll() {
		
		return studentRepository.findAll();
	
	}

		

	@Override
	public List<Student> findStudents(String lastName) {
		
		return studentRepository.findByLastName(lastName);
		
	}

	@Override
	public Student findStudent(Long id) throws ResourceNotFoundException {
		
		return studentRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Student not found with id"+id));
		
	}

	@Override
	public void createStudent(Student student) {
		
		if(studentRepository.existsByEmail(student.getEmail())) {
			throw new ConflictException("Email already exist!");
		}
		
		studentRepository.save(student);
	}

	@Override
	public void updateStudent(Long id,StudentDTO studentDTO) {
		
		
		Student foundStudent = studentRepository.findById(id).
		orElseThrow(()-> new ResourceNotFoundException("Student not found With id :"+id));
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user = userRepository.findByUserName(userDetails.getUsername());
		
		if(foundStudent.getUser()==null) {
			throw new BadRequestException("User of the Student not found");
			
		}
		
		if(!user.get().getStudent().getId().equals(id)) {
			
			//throw new AccessDeniedException("This user doesnt have enought oermıssıon tu update id :"+id);
			throw new ResourceNotFoundException("This user doesnt have enought oermıssıon tu update id :"+id);
			
		}
		
		
		
		
		//*************************************************************************************************************************
		Boolean emailExist =studentRepository.existsByEmail(studentDTO.getEmail());
		Student student =findStudent(id);
	
		if(emailExist&&studentDTO.getEmail().equals(student.getEmail())) {
			
			throw new ConflictException("Email already used");
			
		}
		//Student updateStudent = new Student();

		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setGrade(studentDTO.getGrade());
		student.setEmail(studentDTO.getEmail());
		student.setPhoneNumber(studentDTO.getPhoneNumber());
		studentRepository.save(student);
		
		
	}

	@Override
	public void deleteStudent(Long id) {
		Student student = findStudent(id);
		studentRepository.delete(student);
	}

	@Override
	public Page<Student> getAllWithPage(Pageable pageable) {
		
		return studentRepository.findAll(pageable);
	}

	@Override
	public List<Student> findAllEqualsGrade(Integer grade) {
		
		//return studentRepository.findAllEqualsGrade(grade);
		return studentRepository.findAllEqualsGradeWihtSQL(grade);
	}

	@Override
	public StudentDTO studentDTOById(Long id) {
		
			return studentRepository.studentDTOById(id)
					.orElseThrow(()->new 
							ResourceNotFoundException("Student not found"+id));
		
		
	}

	

}
