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

import jakarta.transaction.Transactional;

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

	public ArrayList<MatchRecord> getByCupAndSeason(String season,String cup,String mode) {
		return matchRespository.findByCupAndSeason(season,cup,mode);
	}

	public void deleteAll() {
		matchRespository.deleteAll();

	}

	public void updateMatch(MatchRecord toUpdate) {
		matchRespository.save(toUpdate);

	}

	public List<MatchRecord> getQueueBySeason(String season,String cup, String webMode) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -7);
		int queue = -1;
		Integer queueInt = matchRespository.findQueueByDate(today.getTime(),cup, webMode);
		if (queueInt != null)
			queue = queueInt.intValue();
		else {
			queueInt = matchRespository.findQueueByDate(today.getTime(),cup, FootballApi.WEB_MODE);
			if (queueInt != null)
				queue = queueInt.intValue();
			else
				return null;
			return matchRespository.findQueueBySeason(season,cup, queue, FootballApi.WEB_MODE);
		}
		// queue =14; //TODO remove hardcode
		return matchRespository.findQueueBySeason(season,cup, queue, webMode);
	}

	public void executeUpdateLastQueue(int queue, String season) {
		matchRespository.updateLastQueue(queue, season);
	}

	public void checkPredictionQueue(String season, int queue) {
		matchRespository.checkPredictionQueue(season, queue);

	}

	public List<MatchRecord> getQueueByLPQueue(String season, String cup, String selectedQueue) {
		String[] queueParts = selectedQueue.split(" ");
		List<MatchRecord> matches= new ArrayList<>();
		if(queueParts[0].equals("Queue"))
			matches = matchRespository.findQueueBySeasonAndCup(season,cup,Integer.valueOf(queueParts[1]), FootballApi.WEB_MODE);
		else if(queueParts[0].equals("Elimination"))
			if(queueParts.length==3 && queueParts[2].equals("Rematch"))
				matches = matchRespository.findEliminationBySeasonAndCup(season,cup,Integer.valueOf(queueParts[1]),true, FootballApi.WEB_MODE);
			else
				matches = matchRespository.findEliminationBySeasonAndCup(season,cup,Integer.valueOf(queueParts[1]),false, FootballApi.WEB_MODE);
		else if(queueParts[0].equals("Round"))
			matches = matchRespository.findRoundBySeasonAndCup(season,cup,Integer.valueOf(queueParts[1]), FootballApi.WEB_MODE);
		else if(queueParts[0].equals("Knockout"))
			if(queueParts.length==3 && queueParts[2].equals("Rematch"))
				matches = matchRespository.findKnockoutBySeasonAndCup(season,cup,Integer.valueOf(queueParts[1]),true, FootballApi.WEB_MODE);
			else
				matches = matchRespository.findKnockoutBySeasonAndCup(season,cup,Integer.valueOf(queueParts[1]),false, FootballApi.WEB_MODE);
		return matches;
	}

	public String[] getAllSeasons() {
		return matchRespository.findAllSeasons();
	}

	public void deleteAllWhereMode(String webMode) {
		matchRespository.deleteAllWhereMode(webMode);
	}

	public String[] getCups(String webMode) {
		return matchRespository.findCupsDistinct(webMode);
	}

	public ArrayList<String> getQueuesOfCup(String cup, String webMode) {
		ArrayList<String[]> queues =  matchRespository.findQueueOfCup(cup, webMode);
		ArrayList<String> queuesString = new ArrayList<>();
		for(String[] queue: queues) {
			String queueString = "";
			if(!queue[0].equals("-1"))
				queueString += "Queue "+queue[0];
			else if(!queue[1].equals("-1"))
				queueString += "Elimination "+queue[1]+(Boolean.parseBoolean(queue[4])?" Rematch":"");
			else if(!queue[2].equals("-1"))
				queueString += "Round "+queue[2];
			else if(!queue[3].equals("-1"))
				queueString += "Knockout "+queue[3]+(Boolean.parseBoolean(queue[4])?" Rematch":"");
			queuesString.add(queueString);
		}
		return queuesString;
	}

}
