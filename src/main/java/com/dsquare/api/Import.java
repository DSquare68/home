package com.dsquare.api;

import java.util.ArrayList;
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
import com.dsquare.service.TrainingService;
import com.vaadin.flow.router.Route;

@RestController
public class Import {

	@Autowired
	private ExerciseNamesRepository namesRepository;
	@Autowired
	private TrainingService trainingService;
	
	@PostMapping("/api/add/training")
	public ResponseEntity<Training> addTraining(@RequestBody List<Training> training) {
	    try {
	        trainingService.addTraining((ArrayList<Training>)removeNulls(training));
	        return ResponseEntity.status(201).build();
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        throw e;
	    }
	}
	
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
	private ArrayList<?> removeNulls(List<?> list) {
		return new ArrayList<>(list.stream().filter(e->e!=null).toList());
	}
}
