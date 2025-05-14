package com.dsquare.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.db.ExerciseNames;
import com.dsquare.model.Training;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.TrainingRepository;


@RestController
public class Export {
	@Autowired
	private ExerciseNamesRepository exerciseRepository;
	
	@GetMapping("/api/get/exercises")
	public List<ExerciseNames> getExercises() {
		System.out.println("asdf1");
		return exerciseRepository.findAll();
	}
}
