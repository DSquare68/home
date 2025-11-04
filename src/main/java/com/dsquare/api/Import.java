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
import com.dsquare.db.MatchRecord;
import com.dsquare.db.TrainingRecord;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.ExerciseRepository;
import com.dsquare.repository.MatchRespository;
import com.dsquare.repository.TrainingRepository;
import com.vaadin.flow.router.Route;

@RestController
public class Import {

	@Autowired
	private ExerciseNamesRepository namesRepository;
	@Autowired
	private TrainingRepository trainingRepository;
	@Autowired
	private MatchRespository matchRepository;

	@PostMapping("/api/add/exercise_name")
	public ResponseEntity<ExerciseNames> addExercise(@RequestBody ExerciseNames e) {
		namesRepository.save(e);
		return ResponseEntity.status(200).build();
	}

	@PostMapping("/api/add/exercise_names")
	public ResponseEntity<ExerciseNames> addExercises(@RequestBody List<ExerciseNames> es) {
		es = es.stream().filter(e -> e != null || e.getName() != null).collect(java.util.stream.Collectors.toList());
		for (ExerciseNames e : es)
			namesRepository.save(e);
		return ResponseEntity.status(200).build();
	}

	@PostMapping("/api/add/training_record") // ?type=class
	public ResponseEntity<TrainingRecord> addTrainingRecord(@RequestBody TrainingRecord t) {
		trainingRepository.save(t);
		return ResponseEntity.status(200).build();
	}

	/*
	 * @PostMapping("/api/add/training_record")//type=json public
	 * ResponseEntity<TrainingRecord> addTrainingRecord(@RequestBody String t) { int
	 * a=0; return ResponseEntity.status(200).build(); }
	 */
	@PostMapping("/api/add/training")
	public ResponseEntity<TrainingRecord> addTraining(@RequestBody ArrayList<TrainingRecord> ts) {
		ts = (ArrayList<TrainingRecord>) ts.stream().filter(e -> e != null)
				.collect(java.util.stream.Collectors.toList());
		int ID_TRAINING = trainingRepository.getMaxIDTrainingRecord() + 1;
		int ID_SCHEMA = ts.get(0).getSCHEMA(); // Assuming all records have the same schema
		if (ts.get(0).getIS_SCHEMA() == 1)
			ID_SCHEMA = trainingRepository.getMaxIDSchema() + 1;
		for (TrainingRecord e : ts) {
			e.setID_TRAINING(ID_TRAINING);
			e.setSCHEMA(ID_SCHEMA);
			trainingRepository.save(e);
		}
		return ResponseEntity.status(200).build();
	}
	
	@PostMapping("/api/add/matches")
	public ResponseEntity<MatchRecord> addMatches(@RequestBody ArrayList<MatchRecord> matches) {
		ArrayList<MatchRecord> guests = matchRepository.getByNotMode(FootballApi.WEB_MODE);
		for(MatchRecord match : matches)
			matchRepository.save(match);
		return ResponseEntity.status(200).build();
	}
}
