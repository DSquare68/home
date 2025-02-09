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
import com.vaadin.flow.router.Route;

@RestController
public class Import {

	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@PostMapping("/api/add/exercise")
	public ResponseEntity<Exercise> addExercise(@RequestBody Exercise e) {
		exerciseRepository.save(e);
		return ResponseEntity.status(200).build();
	}
	
	@GetMapping("/add/exercises")
	public String addExercises(@RequestBody List<Exercise> es) {
		for(Exercise e : es)
			exerciseRepository.save(e);
		return "import";
	}
}
