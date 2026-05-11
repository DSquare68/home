package com.dsquare.page;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dsquare.api.FootballApi;
import com.dsquare.db.MatchRecord;
import com.dsquare.event.CupEvent;
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
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import lombok.Getter;

@Route(layout = Home.class, value = "football")
public class Football extends Div{
	
	
	public ComboBox cupsComboBox;
	private static final long serialVersionUID = 8461698117936164465L;
	private String selectedSeason, selectedQueue;
	@Getter
	private String selectedCup;
	private ArrayList<MatchRecord> queueMatches;
	private ArrayList<String> queues, seasons;
	
	public Football(MatchServiceImpl  matchService) {
		this.setId("football");
		//new FootballApi(matchService).run();
		Calendar cal = Calendar.getInstance();
		String season;
		if(cal.get(Calendar.MONTH)>=7)
			season = cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.YEAR)+1);
		else
			season =cal.get(Calendar.YEAR)-1+"/"+cal.get(Calendar.YEAR);
		String[] cups = matchService.getCups(FootballApi.WEB_MODE);
		selectedCup = cups[0];
		queues= matchService.getQueuesOfCup(cups[0], FootballApi.WEB_MODE);
		queueMatches = matchService.getQueueByLPQueue(season,selectedCup,queues.get(0)); 
		selectedSeason = season;
		selectedQueue = queues.get(0);
		if(queueMatches==null)
			return;
		
		
		cupsComboBox = new ComboBox();
		cupsComboBox.setItems(cups);
		cupsComboBox.setValue(cups[0]);
		cupsComboBox.addValueChangeListener(updateCupInView());
		cupsComboBox.setId("league-title");
		boolean showpredictions = false; 
		seasons = matchService.getAllSeasons();
		final FootballView footballView = new FootballView();
		Button showpredictionsDiv = new Button("Show predictions");
		showpredictionsDiv.setId("show-predictions-disabled");
		
		showpredictionsDiv.addClickListener(e->
			{
				boolean finalShowpredictions = !showpredictions;
				if(finalShowpredictions) {
					showpredictionsDiv.setId("show-predictions-enabled");
				}else {
					showpredictionsDiv.setId("show-predictions-disabled");
				}
				this.remove(footballView);
				 ArrayList<MatchRecord> predictionMatches = null;// TODO add new function matchService.getPredictions(selectedSeason, selectedCup,selectedQueue);
				footballView.setPredictionMatches(predictionMatches);
				this.add(footballView);
		});
		//ekstraklasa.add(showpredictionsDiv);
		SeasonAndQueueView seasonAndQueueView = new SeasonAndQueueView("Season: "+season,queues.get(0).split(" ")[0]+":"+queues.get(0).split(" ")[1]);
		footballView.setSeasons(seasons);
		footballView.setQueues(queues);
		footballView.getQueueComboBox().setValue(queues.get(0));
		footballView.getSeasonsComboBox().setValue(season);
		footballView.setQueueMatches(queueMatches);
		ComponentUtil.addListener(UI.getCurrent(),SeasonEvent.class,e->{
			selectedSeason = e.getSource().getSelectedSeason();
			queues = matchService.getQueuesOfCup(cups[0], FootballApi.WEB_MODE);
			seasonAndQueueView.getSeasonDiv().setText("Season: "+selectedSeason);
			footballView.setQueues(queues);
			footballView.getQueueComboBox().setValue(queues.get(0));
		});
		ComponentUtil.addListener(UI.getCurrent(),QueueEvent.class,e->{
			selectedQueue = e.getSource().getSelectedQueue();
			queueMatches = matchService.getQueueByLPQueue(selectedSeason,selectedCup,selectedQueue); //TODO maby by selected queue show after change season
			seasonAndQueueView.getQueueDiv().setText(selectedQueue);
			footballView.setQueueMatches(queueMatches);
		});
		ComponentUtil.addListener(UI.getCurrent(), CupEvent.class, e->{
			selectedCup = e.getSource().getSelectedCup();
			seasons = matchService.getSeasonsInCup(selectedCup,FootballApi.WEB_MODE); //TODO maby by selected queue show after change season
			footballView.setSeasons(seasons);
			footballView.getSeasonsComboBox().setValue(seasons.get(0));
		});
		add(cupsComboBox,seasonAndQueueView,footballView);	
	}
	
	private ValueChangeListener updateCupInView() {
		Football football = this;
		return new ValueChangeListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChanged(ValueChangeEvent value) {
				if(value.getHasValue().isEmpty())
					return;
				selectedCup = (String) value.getValue();
				ComponentUtil.fireEvent(UI.getCurrent(), new CupEvent(football,false));
				
			}
		};
	}
}
