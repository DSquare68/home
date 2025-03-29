package com.dsquare.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dsquare.db.ExerciseNamesBase;
import com.dsquare.db.ExerciseNames;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.ExerciseRepository;
import com.dsquare.service.ExerciseNamesServiceImpl;

@Service
public class InitData {

	private ExerciseNamesServiceImpl  namesService;
	public InitData(ExerciseNamesServiceImpl ExerciseNamesServiceImpl) {
		this.namesService = ExerciseNamesServiceImpl;
	}
	//@Transactional //(readOnly = false, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public void run() {
		Long amountIDs=namesService.repo.findMaxId();
		if(amountIDs == null) amountIDs = 0L;
		if(amountIDs < ExerciseNamesBase.nameTrainings.length/2) {
			ArrayList<ExerciseNames> e = ExerciseNames.init(ExerciseNamesBase.nameTrainings);
			namesService.insertAll(e);
		}
	}
}
