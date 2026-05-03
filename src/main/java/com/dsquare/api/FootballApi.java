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
import java.util.stream.Stream;

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
	private Document docEkstraklasa = null;
	private Document docLigaKonferencji = null;
	private Document docLigaEuropy = null;
	private Document docLigaMistrzow = null;
	public final static String WEB_MODE = "WEBSITE_DATA";
	public final static String ANDROID = "ANDROID";
	public final static String ANDROID_TIE_WIN  = "ANDROID_TIE_WIN";
	public final static String ANDROID_TIE_LOSE  = "ANDROID_TIE_LOSE";
	public final static String ANDROID_HOME_WIN  = "ANDROID_HOME_WIN";
	public final static String ANDROID_HOME_LOSE  = "ANDROID_HOME_LOSE";
	public final static String ANDROID_GUEST_WIN  = "ANDROID_GUEST_WIN";
	public final static String ANDROID_GUEST_LOSE  = "ANDROID_HOME_LOSE";
	
	public FootballApi(MatchServiceImpl service) {
		this.matchService = service;
		try {
			docEkstraklasa = Jsoup.connect("http://www.90minut.pl/liga/1/liga14072.html").get();
			docLigaKonferencji = Jsoup.connect("http://www.90minut.pl/liga/1/liga14079.html").get();
			docLigaEuropy = Jsoup.connect("http://www.90minut.pl/liga/1/liga14078.html").get();
			docLigaMistrzow = Jsoup.connect("http://www.90minut.pl/liga/1/liga14077.html").get();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Elements newsHeadlines = doc.getElementsByClass("event__match event__match--withRowLink event__match--static event__match--twoLine");
		
	}
	public void run() {
		//matchService.deleteAllWhereMode(WEB_MODE);
		//getEkstraklasaMatches();
		getLigaKonferencjiMatches(docLigaKonferencji);
	}
	private void getLigaKonferencjiMatches(Document doc) {
		String cup = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p[3]/table[@class='main2']/tbody/tr/td[@class='main']/b").get(0).text();
		String season = cup.split(" ")[2];
		List<MatchRecord> seasonMatches = matchService.getByCupAndSeason(season,cup,WEB_MODE);
		if(seasonMatches.size()>0) 
			return;
		ArrayList<ArrayList<MatchRecord>> matches = getEuropeLigesFromWeb(doc);
		for(ArrayList<MatchRecord> matchList : matches)
			matchList.sort((a,b)->Integer.compare(a.getGuestResult(), b.getGuestResult()));
		//matchService.executeUpdateLastQueue(14,matches.get(0).get(0).getSeason());
		//matchService.checkPredictionQueue(matches.get(0).get(0).getSeason(),14);
		List<MatchRecord> androidSeasonMatches = matchService.getByCupAndSeason(matches.get(0).get(0).getSeason(),cup,ANDROID);
		if(seasonMatches==null|| seasonMatches.size()==0) {
			for(ArrayList<MatchRecord> matchList : matches)
				matchService.addMatchesRecord(matchList);
		}
		/*
		}else {
			seasonMatches = seasonMatches.stream().filter(e-> e.getHomeResult() == -1 && e.getGuestResult() == -1 ).sorted((a,b)->Integer.compare(a.getQueue(),b.getQueue())).toList();
			if(seasonMatches.size()==0) return;
			int[] queue = seasonMatches.stream().mapToInt(e->e.getQueue()).distinct().toArray();
			for(int i=0; i<queue.length; i++) {
				if(matches.get(queue[i]-1).get(0).getHomeResult() == -1 && matches.get(queue[i]-1).get(0).getGuestResult() == -1)
					continue;
				for(MatchRecord toUpdate : seasonMatches) {
					MatchRecord m = matches.get(queue[i]-1).stream().filter((e)->e.getHome().equals(toUpdate.getHome()) && e.getGuest().equals(toUpdate.getGuest()) && e.getSeason().equals(toUpdate.getSeason())).findFirst().orElse(null);
					if(toUpdate!=null) {
						toUpdate.setHomeResult(m.getHomeResult());
						toUpdate.setGuestResult(m.getGuestResult());
						matchService.updateMatch(toUpdate);
					}
				}
			}
			List<Integer> allQueues = androidSeasonMatches.stream().mapToInt(e->e.getQueue()).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			for(int i : queue)
				if(allQueues.contains(i))
					allQueues.remove((Integer)i);
			if(allQueues.size()==0) return;
			for(Integer q : allQueues) {
				matchService.executeUpdateLastQueue(q,matches.get(0).get(0).getSeason());
				matchService.checkPredictionQueue(matches.get(0).get(0).getSeason(),q);
			}
		}
		*/
	}
	private ArrayList<ArrayList<MatchRecord>> getEuropeLigesFromWeb(Document doc) {
		newsHeadlines = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']");
		Elements queues  = newsHeadlines.select("p");
		int elimNumber = 1, roundNumber = 0, knockoutNumber = 0,queueNumber = 0,stage = 1;
		ArrayList<ArrayList<MatchRecord>> matches = new ArrayList<>();
		String cup = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p[3]/table[@class='main2']/tbody/tr/td[@class='main']/b").get(0).text();
		boolean isKonferencja = false, isRematch = false;
		if(cup.contains("Konferencjii"))
			isKonferencja = true;
		matches.add(new ArrayList<MatchRecord>());
		for(int i=4; i<queues.size(); i++) {
			if(queues.get(i).text().contains("elimina") || queues.get(i).text().contains("Kolejka") || queues.get(i).text().toLowerCase().contains("finał")) {
				matches.add(new ArrayList<MatchRecord>());
				queueNumber++;
			}
			if(queues.get(i).text().contains("elimina")) {
				stage = 1;
				elimNumber++;
				isRematch=false;
				continue;
			}
			else if(queues.get(i).text().contains("Kolejka")) {
				stage = 2;
				roundNumber++;
				isRematch=false;
				continue;
			}
			 else if(queues.get(i).text().toLowerCase().contains("finał")) {
				 stage = 3;
				 knockoutNumber++;
				 isRematch=false;
				 continue;
			 }
			Elements matchesInQueue = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p["+(i+1)+"]/table[@class='main']").select("tr");
			for(int j=0; j<matchesInQueue.size(); j++) {
				Elements matchDetails = matchesInQueue.get(j).select("td");
				MatchRecord match = new MatchRecord();
				if(matchDetails.size() == 1) isRematch=true;
				if(matchDetails.size() < 3) continue;
				match.setHome(matchDetails.get(1).select("b").isEmpty() ? matchDetails.get(1).text() : matchDetails.get(1).select("b").get(0).text());
				match.setGuest(matchDetails.get(3).select("b").isEmpty() ? matchDetails.get(3).text() : matchDetails.get(3).select("b").get(0).text());
				String result= matchDetails.get(2).select("a").isEmpty() ? (matchDetails.get(2).select("b").isEmpty() ? "" : matchDetails.get(2).select("b").get(0).text()) : matchDetails.get(2).select("a").get(0).select("b").get(0).text();
				if(result.length()>0) {
					String[] scores  = result.split("-");
					match.setHomeResult(Integer.valueOf(scores[0]));
					match.setGuestResult(Integer.valueOf(scores[1]));
				}else {
					match.setHomeResult(-1);
					match.setGuestResult(-1);
				}
				String date = matchDetails.get(5).select("td").isEmpty() ? "" : matchDetails.get(5).select("td").get(0).text();
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
				match.setRematch(isRematch);
				switch(stage) {
					case 1:
						match.setElimination(elimNumber);
						match.setRound(-1);
						match.setKnockout(-1);
						break;
					case 2:
						match.setElimination(-1);
						match.setRound(roundNumber);
						match.setKnockout(-1);
						match.setRematch(false);
						break;
					case 3:
						match.setElimination(-1);
						match.setRound(-1);
						match.setKnockout(knockoutNumber);
						break;
				}				
				matches.get(queueNumber).add(match);
			}
			
		}
		return matches;
		
		
	}
	private void getEkstraklasaMatches() {
		String[] data=docEkstraklasa.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p[3]/table[@class='main2']/tbody/tr[1]/td[@class='main']/b").get(0).text().split(" ");
		String season = data[1];
		String cup = data[0];
		List<MatchRecord> seasonMatches = matchService.getByCupAndSeason(season,cup,WEB_MODE);
		if(seasonMatches.size()>0) 
			return;
		ArrayList<ArrayList<MatchRecord>> matches = getFromWeb();
		for(ArrayList<MatchRecord> matchList : matches)
			matchList.sort((a,b)->Integer.compare(a.getGuestResult(), b.getGuestResult()));
		//matchService.executeUpdateLastQueue(14,matches.get(0).get(0).getSeason());
		//matchService.checkPredictionQueue(matches.get(0).get(0).getSeason(),14);
		List<MatchRecord> androidSeasonMatches = matchService.getByCupAndSeason(matches.get(0).get(0).getSeason(),cup,ANDROID);
		if(seasonMatches==null|| seasonMatches.size()==0) {
			for(ArrayList<MatchRecord> matchList : matches)
				matchService.addMatchesRecord(matchList);
		}else {
			seasonMatches = seasonMatches.stream().filter(e-> e.getHomeResult() == -1 && e.getGuestResult() == -1 ).sorted((a,b)->Integer.compare(a.getQueue(),b.getQueue())).toList();
			if(seasonMatches.size()==0) return;
			int[] queue = seasonMatches.stream().mapToInt(e->e.getQueue()).distinct().toArray();
			for(int i=0; i<queue.length; i++) {
				if(matches.get(queue[i]-1).get(0).getHomeResult() == -1 && matches.get(queue[i]-1).get(0).getGuestResult() == -1)
					continue;
				for(MatchRecord toUpdate : seasonMatches) {
					MatchRecord m = matches.get(queue[i]-1).stream().filter((e)->e.getHome().equals(toUpdate.getHome()) && e.getGuest().equals(toUpdate.getGuest()) && e.getSeason().equals(toUpdate.getSeason())).findFirst().orElse(null);
					if(toUpdate!=null) {
						toUpdate.setHomeResult(m.getHomeResult());
						toUpdate.setGuestResult(m.getGuestResult());
						matchService.updateMatch(toUpdate);
					}
				}
			}
			List<Integer> allQueues = androidSeasonMatches.stream().mapToInt(e->e.getQueue()).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			for(int i : queue)
				if(allQueues.contains(i))
					allQueues.remove((Integer)i);
			if(allQueues.size()==0) return;
			for(Integer q : allQueues) {
				matchService.executeUpdateLastQueue(q,matches.get(0).get(0).getSeason());
				matchService.checkPredictionQueue(matches.get(0).get(0).getSeason(),q);
			}
		}
		
	}
	private ArrayList<ArrayList<MatchRecord>> getFromWeb() {
		newsHeadlines = docEkstraklasa.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']");
		Elements queues  = newsHeadlines.select("p");
		int queueNumber = 0;
		ArrayList<ArrayList<MatchRecord>> matches = new ArrayList<>();
		String cup = docEkstraklasa.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p[3]/table[@class='main2']/tbody/tr[1]/td[@class='main']/b").get(0).text();
		for(int i=4; i<queues.size(); i++) {
			if(queues.get(i).text().contains("Kolejka")) {
				queueNumber++;
				matches.add(new ArrayList<MatchRecord>());
				i++;
				Elements matchesInQueue = docEkstraklasa.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']/p["+(i+1)+"]/table[@class='main']").select("tr");
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
						match.setElimination(-1);
						match.setRound(-1);
						match.setKnockout(-1);
					matches.get(queueNumber-1).add(match);
				}
			}
		}
		return matches;
	}
	
	private Date getMatchDate(String date) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		Hashtable<String, String> monthMap = new Hashtable<>();
		if(cal.MONTH<6)
			--year;
		monthMap.put("lipca", "07 "+year);
		monthMap.put("sierpnia", "08 "+year);
		monthMap.put("września", "09 "+year);
		monthMap.put("października", "10 "+year);
		monthMap.put("listopada", "11 "+year);
		monthMap.put("grudnia", "12 "+year);
		year = cal.get(Calendar.YEAR);
		if(cal.MONTH>6)
			++year;
		monthMap.put("stycznia", "01 "+year);
		monthMap.put("lutego", "02 "+year);
		monthMap.put("marca", "03 "+year);
		monthMap.put("kwietnia", "04 "+year);
		monthMap.put("maja", "05 "+year);
		monthMap.put("czerwca", "06 "+year);
		
		
		
		for(String month : monthMap.keySet()) {
			if(date.contains(month)) {
				date = date.replace(month, monthMap.get(month));
				break;
			}
		}
		if(date.charAt(date.length()-1)==' ')
			date = date.substring(0, date.length()-1);
		if(date.length()>10&&date.length()<17)
			date = "0"+date;
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy, HH:mm", Locale.ENGLISH);
		try {
			if(date==null || date.length()<11)
				return null;
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