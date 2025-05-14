package com.dsquare.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsquare.model.Training;
import com.dsquare.repository.TrainingRepository;

import jakarta.transaction.Transactional;

@Service
public class TrainingService {

	@Autowired
	TrainingRepository trainingRepository;
	
	@Transactional
	public void addTraining(ArrayList<Training> trainings) {
		for(Training training : trainings) {
			trainingRepository.save(training);
		}
	}

}
