package com.dsquare.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsquare.api.FootballApi;
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
	    int queue = -1;
	    Integer queueInt = matchRespository.findQueueByDate(today.getTime(),webMode);
	    if (queueInt != null) 
	    	queue = queueInt.intValue();
	    else {
	    	queueInt = matchRespository.findQueueByDate(today.getTime(),FootballApi.WEB_MODE);
	    	queue = queueInt.intValue();
	    	return matchRespository.findQueueBySeason(season,queue,FootballApi.WEB_MODE);
	    }
		//queue =14; //TODO remove hardcode
		return matchRespository.findQueueBySeason(season,queue,webMode);
	}

	public void executeUpdateLastQueue(int queue,String season) {
		matchRespository.updateLastQueue(queue,season);
	}

	public void checkPredictionQueue(String season, int queue) {
		matchRespository.checkPredictionQueue(season,queue);
		
	}

	public List<MatchRecord> getQueueByLPQueue(String season, String selectedQueue) {
		List<MatchRecord> matches = matchRespository.findQueueBySeason(season,Integer.valueOf(selectedQueue),FootballApi.ANDROID);;
		if(matches!=null&&matches.size()>0)
			return matches;
		else
			return matchRespository.findQueueBySeason(season,Integer.valueOf(selectedQueue),FootballApi.WEB_MODE);
	}

	public String[] getAllSeasons() {
		return matchRespository.findAllSeasons();
	}

}
