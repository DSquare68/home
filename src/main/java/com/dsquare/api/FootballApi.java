package com.dsquare.api;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import lombok.Setter;

public class FootballApi {

	public FootballApi() {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.90minut.pl/liga/1/liga14072.html").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Elements newsHeadlines = doc.getElementsByClass("event__match event__match--withRowLink event__match--static event__match--twoLine");
		Elements newsHeadlines = doc.getElementsByClass("main");
		System.out.println("ADFA");
	}
}