package com.dsquare.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsquare.db.TrainingRecord;
import com.dsquare.model.Training;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.TrainingRepository;

@Service
public class TrainingServiceImpl {

	@Autowired
	private TrainingRepository trainingRepository;
	@Autowired
	private ExerciseNamesServiceImpl exerciseService;
	
	public void addTrainingRecord(TrainingRecord record) {
		trainingRepository.save(record);
	}
	
	public void addTraining(ArrayList<TrainingRecord> record) {
		trainingRepository.saveAll(record);
	}

	public ArrayList<Training> getSchemasDataTraining() {
		return TrainingRecord.toTraininings(trainingRepository.getAllSchemas(), exerciseService);
	}

	public ArrayList<Training> getTrainingsByIDSchema(int id) {
		return TrainingRecord.toTraininings(trainingRepository.getTrainingsPerSchema(id), exerciseService);
	}

	public ArrayList<TrainingRecord> getTrainingsWithExercise(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
