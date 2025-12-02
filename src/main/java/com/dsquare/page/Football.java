package com.dsquare.page;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dsquare.api.FootballApi;
import com.dsquare.db.MatchRecord;
import com.dsquare.event.QueueEvent;
import com.dsquare.event.SchemaEvent;
import com.dsquare.event.SeasonEvent;
import com.dsquare.model.Training;
import com.dsquare.service.MatchServiceImpl;
import com.dsquare.view.FootballView;
import com.dsquare.view.SeasonAndQueueView;
import com.dsquare.view.TrainingView;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(layout = Home.class, value = "football")
public class Football extends Div{
	
	private static final long serialVersionUID = 8461698117936164465L;
	private FootballView footballView;
	private String selectedSeason, selectedQueue;
	private ArrayList<MatchRecord> seasonMatches;
	private List<MatchRecord> queueMatches;
	
	public Football(MatchServiceImpl  matchService) {
		this.setId("football");
		Calendar cal = Calendar.getInstance();
		String season;
		if(cal.get(Calendar.MONTH)>=7)
			season = cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.YEAR)+1);
		else
			season =cal.get(Calendar.YEAR)-1+"/"+cal.get(Calendar.YEAR);
		seasonMatches = matchService.getBySeason(season);
		queueMatches = matchService.getQueueBySeason(season,FootballApi.ANDROID);
		Integer[] queues = seasonMatches.stream().map(MatchRecord::getQueue).distinct().toList().toArray(new Integer[seasonMatches.stream().map(MatchRecord::getQueue).distinct().toList().size()]);
		Div ekstraklasa = new Div("EKSTRAKLASA");
		ekstraklasa.setId("league-title");
		SeasonAndQueueView seasonAndQueueView = new SeasonAndQueueView("Season: "+season,"Queue matches: "+queueMatches.get(0).getQueue()+"/"+seasonMatches.get(seasonMatches.size()-1).getQueue());
		final String[] seasons = matchService.getAllSeasons();
		footballView = new FootballView(queueMatches,seasons,queues);

		ComponentUtil.addListener(UI.getCurrent(),SeasonEvent.class,e->{
			this.remove(footballView);
			selectedSeason = e.getSource().getSelectedSeason();
			seasonMatches = matchService.getBySeason(season);
			queueMatches = matchService.getQueueBySeason(season,FootballApi.ANDROID); //TODO maby by selected queue show after change season
			seasonAndQueueView.getSeasonDiv().setText("Season: "+selectedSeason);
			seasonAndQueueView.getQueueDiv().setText(queueMatches.get(0).getQueue()+"/"+seasonMatches.get(seasonMatches.size()-1).getQueue());
			footballView = new FootballView(queueMatches,seasons,queues);
			this.add(footballView);
		});
		ComponentUtil.addListener(UI.getCurrent(),QueueEvent.class,e->{
			this.remove(footballView);
			selectedSeason = e.getSource().getSelectedSeason();
			selectedQueue = e.getSource().getSelectedQueue();
			queueMatches = matchService.getQueueByLPQueue(season,selectedQueue); //TODO maby by selected queue show after change season
			seasonAndQueueView.getSeasonDiv().setText("Season: "+selectedSeason);
			seasonAndQueueView.getQueueDiv().setText(queueMatches.get(0).getQueue()+"/"+seasonMatches.get(seasonMatches.size()-1).getQueue());
			footballView = new FootballView(queueMatches,seasons,queues);
			this.add(footballView);
		});
		add(ekstraklasa,seasonAndQueueView,footballView);	
	}
}
