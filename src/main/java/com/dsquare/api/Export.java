package com.dsquare.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.model.Exercise;
import com.dsquare.repository.ExerciseRepository;


@RestController
public class Export {
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@PostMapping("/api/get/exercises")
	public List<Exercise> getExercises() {
		System.out.println("asdf1");
		return exerciseRepository.findAll();
	}
}
