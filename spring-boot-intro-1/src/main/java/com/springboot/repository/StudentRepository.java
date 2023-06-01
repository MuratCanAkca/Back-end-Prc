package com.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Student;
import com.springboot.dto.StudentDTO;
import com.springboot.exception.ConflictException;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
	
	@Query("select s from Student s where s.grade=:pGrade")
	List<Student> findAllEqualsGrade(@Param("pGrade") Integer pGrade);
	
	@Query(value="select * from Student s where s.grade=:pGrade" ,nativeQuery = true)
	List<Student> findAllEqualsGradeWihtSQL(@Param("pGrade") Integer pGrade);
	
	@Query("select new com.springboot.dto.StudentDTO(s) from Student s where s.id=:id")
	Optional<StudentDTO> studentDTOById(@Param("id") Long id);
	
	List<Student> findByLastName(String lastName);
	
	Boolean existsByEmail(String email) throws ConflictException; 

}
