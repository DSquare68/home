package com.dsquare.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dsquare.db.ExerciseNamesBase;
import com.dsquare.db.TrainingRecord;
import com.dsquare.db.ExerciseNames;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.ExerciseRepository;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;

@Service
public class InitData {

	private ExerciseNamesServiceImpl  namesService;
	private TrainingServiceImpl  trainingService;
	public InitData(ExerciseNamesServiceImpl ExerciseNamesServiceImpl, TrainingServiceImpl TrainingServiceImpl) {
		this.trainingService = TrainingServiceImpl;
		this.namesService = ExerciseNamesServiceImpl;
	}
	//@Transactional //(readOnly = false, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public void run() {
		//trainingService.addTrainingRecord(new TrainingRecord(1,1,1,1,1.0,1,1, new Date(), "Test"));
		Long amountIDs=namesService.repo.findMaxId();
		if(amountIDs == null) amountIDs = 0L;
		if(amountIDs < ExerciseNamesBase.nameTrainings.length/2) {
			ArrayList<ExerciseNames> e = ExerciseNames.init(ExerciseNamesBase.nameTrainings);
			namesService.insertAll(e);
		}
	}
}
