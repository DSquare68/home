package com.dsquare.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dsquare.api.FootballApi;
import com.dsquare.db.MatchRecord;
import com.dsquare.service.MatchServiceImpl;

@Component
public class Shedules {

	@Autowired
	private MatchServiceImpl matchService;
	
	@Scheduled(fixedRate = 604800000)
	public void checkResultsOfQueue() {
		new FootballApi(matchService).run();
	}

}
