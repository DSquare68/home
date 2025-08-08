package com.dsquare.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsquare.db.TrainingRecord;
import com.dsquare.repository.TrainingRepository;

@Service
public class TrainingServiceImpl {

	@Autowired
	private TrainingRepository trainingRepository;
	
	public void addTrainingRecord(TrainingRecord record) {
		trainingRepository.save(record);
	}
	
	public void addTraining(ArrayList<TrainingRecord> record) {
		trainingRepository.saveAll(record);
	}
}
