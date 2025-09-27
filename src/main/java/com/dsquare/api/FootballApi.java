package com.dsquare.api;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
		ArrayList<ArrayList<MatchRecord>> matches = getFromWeb();
		for(ArrayList<MatchRecord> queue : matches) {
			for(MatchRecord match : queue) {
				//if(matchRepository.findByHomeAndGuestAndDate(match.getHome(), match.getGuest(), match.getDate())==null) {
					matchService.addMatchRecord(match);
			//	}
			}
		}
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
							match.setDate_of_match(Date.from(getMatchDate((date)).atStartOfDay().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toInstant()));
						}else {
							match.setDate_of_match(new Date(0));
						}
						match.setCup(cup);
						String cupS = match.getCup();
						String[] cupArr = cupS.split(" ");
						match.setSeason(cupArr[cupArr.length-1]);
					matches.get(queueNumber-1).add(match);
				}
			}
		}
		return matches;
	}
	
	private LocalDate getMatchDate(String date) {
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm", Locale.ENGLISH);
		LocalDate dateTime = LocalDate.parse(date, formatter);
		return dateTime;
	}
	public ArrayList<ArrayList<MatchRecord>> getMatchesForWeek() {
		ArrayList<ArrayList<MatchRecord>> res = new ArrayList<>();
		
		return res;
	}
}