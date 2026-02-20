package com.dsquare.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import com.dsquare.db.ExerciseNamesBase;
import com.dsquare.db.ExerciseNames;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.MatchServiceImpl;
import com.dsquare.service.TrainingServiceImpl;

@Service
public class InitData {

	private ExerciseNamesServiceImpl  namesService;
	private TrainingServiceImpl  trainingService;
	private MatchServiceImpl  matchService;
	private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

	public InitData(ExerciseNamesServiceImpl ExerciseNamesServiceImpl, TrainingServiceImpl TrainingServiceImpl, MatchServiceImpl MatchServiceImpl) {
		this.trainingService = TrainingServiceImpl;
		this.namesService = ExerciseNamesServiceImpl;
		this.matchService = MatchServiceImpl;
	}

	@Transactional
	@Scheduled(cron = "0 30 23 ? * MON")
	public void run() {
		initExerciseNames();
		initQueueMatch();
	}

	private void initQueueMatch() {
		new FootballApi(matchService).run();	
	}

	private void initExerciseNames() {
		Long amountIDs = namesService.repo.findMaxId();
		if (amountIDs == null) amountIDs = 0L;
		if (amountIDs < ExerciseNamesBase.nameTrainings.length/2) {
			ArrayList<ExerciseNames> e = ExerciseNames.init(ExerciseNamesBase.nameTrainings);
			namesService.insertAll(e);
		}
	}
}