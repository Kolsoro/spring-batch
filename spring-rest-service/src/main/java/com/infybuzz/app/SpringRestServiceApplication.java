package com.infybuzz.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class SpringRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestServiceApplication.class, args);
	}
	
	@GetMapping("/students")
	public List<StudentResponse> students(){
		return Arrays.asList(
//				new StudentResponse(1L,"John","Smith","john@gmail.com"),
//				new StudentResponse(2L,"Aman","Kumar","aman@gmail.com"),
//				new StudentResponse(3L,"joi","Dev","joi@gmail.com"),
//				new StudentResponse(4L,"kamal","singh","kamal@gmail.com")
				);
	} 
//	this post api will create the student 
	@PostMapping("/createStudent")
	public StudentResponse createStudent(@RequestBody StudentRequest studentRequest) {
		System.out.println("student created "+studentRequest);
		return new StudentResponse(studentRequest.getId(), 
				studentRequest.getFirstName(),
				studentRequest.getLastname(),
				studentRequest.getEmail());
	}
	

}
