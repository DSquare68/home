package com.dsquare.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.db.ExerciseNames;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.ExerciseRepository;


@RestController
public class Export {
	@Autowired
	private ExerciseNamesRepository exerciseNamesRepository;
	
	@GetMapping("/api/get/exercise_name/{id}")
	public ExerciseNames getExerciseName(@PathVariable("id") int id) {
		return exerciseNamesRepository.findById(id).orElse(null);
	}
	@GetMapping("/api/get/exercises")
	public List<ExerciseNames> getExercises() {
		return exerciseNamesRepository.findAll();
	}
	/*@GetMapping("api/get/exerciseIDByName/{name}")
	public Long getExerciseIDByName(@Param("name") String name) {
		Long l = exerciseNamesRepository.findIDByName(name);
		return l;
	}*/
	
}
