package com.dsquare.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.db.ExerciseNames;
import com.dsquare.db.TrainingRecord;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.ExerciseRepository;
import com.dsquare.repository.TrainingRepository;
import com.vaadin.flow.router.Route;

@RestController
public class Import {

	@Autowired
	private ExerciseNamesRepository namesRepository;
	@Autowired
	private TrainingRepository trainingRepository;
	
	@PostMapping("/api/add/exercise_name")
	public ResponseEntity<ExerciseNames> addExercise(@RequestBody ExerciseNames e) {
		namesRepository.save(e);
		return ResponseEntity.status(200).build();
	}
	
	@PostMapping("/api/add/exercise_names")
	public String addExercises(@RequestBody List<ExerciseNames> es) {
		for(ExerciseNames e : es)
			namesRepository.save(e);
		return "import";
	}
	@PostMapping("/api/add/training_record")
	public String addTrainingRecord(@RequestBody TrainingRecord t) {
		trainingRepository.save(t);
		return "import";
	}
	@PostMapping("/api/add/training")
	public String addTraining(@RequestBody List<TrainingRecord> ts) {
		for(TrainingRecord e : ts)
			trainingRepository.save(e);
		return "import";
	}
}
