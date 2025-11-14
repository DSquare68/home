package com.dsquare.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsquare.db.MatchRecord;
import com.dsquare.repository.MatchRespository;

@Service
public class MatchServiceImpl {
	@Autowired
	private MatchRespository matchRespository;
	
	public void addMatchRecord(MatchRecord record) {
		matchRespository.save(record);
	}
	
	public void addMatchesRecord(ArrayList<MatchRecord> matches) {
		matchRespository.saveAll(matches);
	}
	public ArrayList<MatchRecord> getBySeason(String season) {
		return matchRespository.findBySeason(season);
	}
	public void deleteAll() {
		matchRespository.deleteAll();
		
	}

	public void updateMatch(MatchRecord toUpdate) {
		matchRespository.save(toUpdate);
		
	}

	public List<MatchRecord> getQueueBySeason(String season, String webMode) {
	    Calendar today = Calendar.getInstance();
	    today.add(Calendar.DATE, -7);
		int queue = matchRespository.findQueueByDate(today.getTime(),webMode); //TODO remove +1 on production
		
		return matchRespository.findQueueBySeason(season,queue,webMode);
	}

	public void executeUpdateLastQueue(int queue,String season) {
		matchRespository.updateLastQueue(queue,season);
	}

	public void checkPredictionQueue(String season, int queue) {
		matchRespository.checkPredictionQueue(season,queue);
		
	}

}
