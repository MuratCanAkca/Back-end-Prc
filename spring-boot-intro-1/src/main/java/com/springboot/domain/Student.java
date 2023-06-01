package com.springboot.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="First Name can not be null")
	@NotBlank(message="First Name can not be white space")
	@Column(length = 100 , nullable = false)
	@Size(min = 1 , max = 100 , message ="First Name '${validatedValue}' must be between1 and 100 chars log")
	private String firstName;
	
	@Column(length = 100 , nullable = false)
	private String lastName;
	
	private Integer grade;
	
	@Column(length = 14)
	private String phoneNumber;
	
	@Column(length = 100 , nullable = false , unique = true)
	@Email(message = "provide valid mail")
	private String email;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss",timezone = "Turkey")
	private LocalDateTime createdaDate=LocalDateTime.now();
	
	@OneToMany(mappedBy="student")
	private List<Book> books = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
