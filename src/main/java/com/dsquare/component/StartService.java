package com.dsquare.component;

import java.util.Calendar;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsquare.api.InitData;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.MatchServiceImpl;
import com.dsquare.service.TrainingServiceImpl;

import lombok.Getter;

@Component("startServiceTimer")
public class StartService {

	@Autowired
	private ExerciseNamesServiceImpl namesService;
	@Autowired
	private TrainingServiceImpl trainingService;
	@Autowired
	private MatchServiceImpl matchService;
	@Getter
	private long periodMillis,initialDelayMillis;
	@Getter
	private Runnable task;
	
	public StartService() {
		time();
		createTask();
	}
	
	public void createTask() {
		 task = () -> {
			try {
				new InitData(namesService,trainingService,matchService).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		
	}
	public void time() {
		// Schedule a task to run at a specific start date/time and then every 7 days.
		Calendar c = Calendar.getInstance();
		// Example: 2 December 2025 at 21:30:00 (Calendar months are 0-based -> DECEMBER == 11)
		c.set(2026, Calendar.JANUARY, 26, 23, 45, 0);
		long targetMillis = c.getTimeInMillis();
		long now = System.currentTimeMillis();
		periodMillis = TimeUnit.DAYS.toMillis(7);
		initialDelayMillis = targetMillis - now;
		// If the target time is in the past, advance it to the next occurrence (add 7-day periods)
		if (initialDelayMillis < 0) {
			long skipped = (-initialDelayMillis + periodMillis - 1) / periodMillis; // ceil
			initialDelayMillis += skipped * periodMillis;
		}

	}
}
