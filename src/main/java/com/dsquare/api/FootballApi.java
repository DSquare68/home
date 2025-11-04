package com.dsquare.api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.Formatter;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.db.MatchRecord;
import com.dsquare.repository.MatchRespository;
import com.dsquare.service.MatchServiceImpl;

import jakarta.annotation.PostConstruct;
import lombok.Setter;

@RestController
public class FootballApi {

	private MatchServiceImpl matchService;
	private Elements newsHeadlines;
	private Document doc = null;
	public final static String WEB_MODE = "WEBSITE_DATA";
	public final static String ANDROID_TIE_WIN  = "ANDROID_TIE_WIN";
	public final static String ANDROID_TIE_LOSE  = "ANDROID_TIE_LOSE";
	public final static String ANDROID_HOME_WIN  = "ANDROID_HOME_WIN";
	public final static String ANDROID_HOME_LOSE  = "ANDROID_HOME_LOSE";
	public final static String ANDROID_GUEST_WIN  = "ANDROID_GUEST_WIN";
	public final static String ANDROID_GUEST_LOSE  = "ANDROID_HOME_LOSE";
	
	public FootballApi(MatchServiceImpl service) {
		this.matchService = service;
		try {
			doc = Jsoup.connect("http://www.90minut.pl/liga/1/liga14072.html").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Elements newsHeadlines = doc.getElementsByClass("event__match event__match--withRowLink event__match--static event__match--twoLine");
		
	}
	public void run() {
		//matchService.deleteAll();
		ArrayList<ArrayList<MatchRecord>> matches = getFromWeb();
		List<MatchRecord> seasonMatches = matchService.getQueueBySeason(matches.get(0).get(0).getSeason(),FootballApi.WEB_MODE);
		if(seasonMatches==null|| seasonMatches.size()==0) {
			for(ArrayList<MatchRecord> matchList : matches)
				matchService.addMatchesRecord(matchList);
		}else {
			seasonMatches = seasonMatches.stream().filter(e-> e.getHomeResult() == -1 && e.getGuestResult() == -1 ).sorted((a,b)->Integer.compare(a.getQueue(),b.getQueue())).toList();
			if(seasonMatches.size()==0) return;
			int queue = seasonMatches.get(0).getQueue();
			if(matches.get(queue-1).get(0).getHomeResult() != -1 && matches.get(queue-1).get(0).getGuestResult() != -1) {
				for(MatchRecord toUpdate : seasonMatches) {
					MatchRecord m = matches.get(queue-1).stream().filter((e)->e.getHome().equals(toUpdate.getHome()) && e.getGuest().equals(toUpdate.getGuest()) && e.getDate_of_match().equals(toUpdate.getDate_of_match())).findFirst().orElse(null);
					if(toUpdate!=null) {
						toUpdate.setHomeResult(m.getHomeResult());
						toUpdate.setGuestResult(m.getGuestResult());
						matchService.updateMatch(toUpdate);
					}
				}
				matchService.executeUpdateLastQueue(queue,matches.get(0).get(0).getSeason());
			}
		}
		matchService.executeUpdateLastQueue(13,matches.get(0).get(0).getSeason());
	}
	private ArrayList<ArrayList<MatchRecord>> getFromWeb() {
		newsHeadlines = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']");
		Elements queues  = newsHeadlines.select("p");
		int queueNumber = 0;
		ArrayList<ArrayList<MatchRecord>> matches = new ArrayList<>();
		String cup = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p[3]/table[@class='main2']/tbody/tr[1]/td[@class='main']/b").get(0).text();
		for(int i=4; i<queues.size(); i++) {
			if(queues.get(i).text().contains("Kolejka")) {
				queueNumber++;
				matches.add(new ArrayList<MatchRecord>());
				i++;
				Elements matchesInQueue = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p["+(i+1)+"]/table[@class='main']").select("tr");
				for(int j=0; j<matchesInQueue.size(); j++) {
					MatchRecord match = new MatchRecord();
					Elements matchDetails = matchesInQueue.get(j).select("td");
					if(matchDetails.size() < 2) continue;
						match.setHome(matchDetails.get(0).select("b").isEmpty() ? matchDetails.get(0).text() : matchDetails.get(0).select("b").get(0).text());
						match.setGuest(matchDetails.get(2).select("b").isEmpty() ? matchDetails.get(2).text() : matchDetails.get(2).select("b").get(0).text());
						String result = matchDetails.get(1).select("a").isEmpty() ? "" : matchDetails.get(1).select("a").get(0).select("b").get(0).text(); 
						if(result.length()>0) {
							String[] scores  = result.split("-");
							match.setHomeResult(Integer.valueOf(scores[0]));
							match.setGuestResult(Integer.valueOf(scores[1]));
						}else {
							match.setHomeResult(-1);
							match.setGuestResult(-1);
						}
						String date = matchDetails.get(3).select("td").isEmpty() ? "" : matchDetails.get(3).select("td").get(0).text();
						if(date.length()>0) {
							if(date.contains("("))
								date = date.substring(0, date.indexOf("("));
							match.setDate_of_match(getMatchDate(date));
						}else {
							match.setDate_of_match(new Date(0));
						}
						match.setCup(cup);
						match.setMode_of_data(WEB_MODE);
						String cupS = match.getCup();
						String[] cupArr = cupS.split(" ");
						match.setSeason(cupArr[cupArr.length-1]);
						match.setQueue(queueNumber);
					matches.get(queueNumber-1).add(match);
				}
			}
		}
		return matches;
	}
	
	private Date getMatchDate(String date) {
		Hashtable<String, String> monthMap = new Hashtable<>();
		monthMap.put("stycznia", "01 2025");
		monthMap.put("lutego", "02  2025");
		monthMap.put("marca", "03 2025");
		monthMap.put("kwietnia", "04 2025");
		monthMap.put("maja", "05 2025");
		monthMap.put("czerwca", "06 2025");
		monthMap.put("lipca", "07 2025");
		monthMap.put("sierpnia", "08 2025");
		monthMap.put("września", "09 2025");
		monthMap.put("października", "10 2025");
		monthMap.put("listopada", "11 2025");
		monthMap.put("grudnia", "12 2025");
		
		for(String month : monthMap.keySet()) {
			if(date.contains(month)) {
				date = date.replace(month, monthMap.get(month));
				break;
			}
		}
		if(date.charAt(date.length()-1)==' ')
			date = date.substring(0, date.length()-1);
		if(date.length()<17)
			date = "0"+date;
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy, HH:mm", Locale.ENGLISH);
		try {
			return  formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<ArrayList<MatchRecord>> getMatchesForWeek() {
		ArrayList<ArrayList<MatchRecord>> res = new ArrayList<>();
		
		return res;
	}
}