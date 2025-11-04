package com.dsquare.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
import com.dsquare.service.MatchServiceImpl;
import com.dsquare.service.TrainingServiceImpl;

@Service
public class InitData {

	private ExerciseNamesServiceImpl  namesService;
	private TrainingServiceImpl  trainingService;
	private MatchServiceImpl  matchService;
	public InitData(ExerciseNamesServiceImpl ExerciseNamesServiceImpl, TrainingServiceImpl TrainingServiceImpl, MatchServiceImpl MatchServiceImpl) {
		this.trainingService = TrainingServiceImpl;
		this.namesService = ExerciseNamesServiceImpl;
		this.matchService = MatchServiceImpl;
	}
	//@Transactional //(readOnly = false, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public void run() {
		initExerciseNames();
		initQueueMatch();
	}
	private void initQueueMatch() {
		Calendar c = Calendar.getInstance();
		c.set(2025,10,27,23,59,59);
		Timer t = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				try {
					new FootballApi(matchService).run();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.schedule(task, c.getTime(), 7*24*60*60*1000);
		
	}
	private void initExerciseNames() {
		Long amountIDs=namesService.repo.findMaxId();
		if(amountIDs == null) amountIDs = 0L;
		if(amountIDs < ExerciseNamesBase.nameTrainings.length/2) {
			ArrayList<ExerciseNames> e = ExerciseNames.init(ExerciseNamesBase.nameTrainings);
			namesService.insertAll(e);
		}
	}
}
