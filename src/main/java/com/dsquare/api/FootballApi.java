package com.dsquare.api;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.db.MatchRecord;

import jakarta.annotation.PostConstruct;
import lombok.Setter;

public class FootballApi {
	
	private Elements newsHeadlines;
	
	public FootballApi() {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.90minut.pl/liga/1/liga14072.html").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Elements newsHeadlines = doc.getElementsByClass("event__match event__match--withRowLink event__match--static event__match--twoLine");
		newsHeadlines = doc.selectXpath("/html/body/table[2]/tbody/tr[1]/td[@class='main']");
		Elements queues  = newsHeadlines.select("p");
		System.out.println(queues.get(8).text());
	}
	public ArrayList<ArrayList<MatchRecord>> getMatchesForWeek() {
		ArrayList<ArrayList<MatchRecord>> res = new ArrayList<>();
		
		return res;
	}
}