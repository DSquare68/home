package com.dsquare.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.db.ExerciseNames;
import com.dsquare.model.Exercise;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.ExerciseRepository;
import com.vaadin.flow.router.Route;

@RestController
public class Import {

	@Autowired
	private ExerciseNamesRepository namesRepository;
	
	@PostMapping("/api/add/exercise_name")
	public ResponseEntity<ExerciseNames> addExercise(@RequestBody ExerciseNames e) {
		namesRepository.save(e);
		return ResponseEntity.status(200).build();
	}
	
	@GetMapping("/add/exercise_names")
	public String addExercises(@RequestBody List<ExerciseNames> es) {
		for(ExerciseNames e : es)
			namesRepository.save(e);
		return "import";
	}
}
