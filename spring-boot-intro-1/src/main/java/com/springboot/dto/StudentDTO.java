package com.springboot.dto;

import java.time.LocalDateTime;

import com.springboot.domain.Student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

	

	private Long id;
	
	@NotNull(message="First Name can not be null")
	@NotBlank(message="First Name can not be white space")
	@Size(min = 1 , max = 100 , message ="First Name '${validatedValue}' must be between1 and 100 chars log")
	private String firstName;
	
	@NotNull(message="Last Name can not be null")
	@NotBlank(message="Last Name can not be white space")
	@Size(min = 1 , max = 100 , message ="Last Name '${validatedValue}' must be between1 and 100 chars log")
	private String lastName;
	
	private Integer grade;
	
	
	private String phoneNumber;
	
	
	@Email(message = "provide valid mail")
	private String email;
	
	private LocalDateTime createdaDate=LocalDateTime.now();
	
	public StudentDTO(Student student) {
		
		this.id=student.getId();
		this.firstName=student.getFirstName();
		this.lastName=student.getLastName();
		this.grade=student.getGrade();
		this.email=student.getEmail();
		this.phoneNumber=student.getPhoneNumber();
		this.createdaDate=student.getCreatedaDate();
		
	}
	
	/*server.port=8081

server.error.include-message=always
server.error.include-binding-errors=always
server.error.include-stacktrace=never

spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.username=root
spring.datasource.password=mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
*/
}
