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
	private FootballView footballView;
	private String selectedSeason, selectedQueue;
	@Getter
	private String selectedCup;
	private ArrayList<MatchRecord> seasonMatches;
	private List<MatchRecord> queueMatches;
	private ArrayList<String> queues;
	
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
		seasonMatches = matchService.getByCupAndSeason(season,selectedCup,FootballApi.WEB_MODE);
		queueMatches = matchService.getQueueBySeason(season,selectedCup,FootballApi.ANDROID);
		selectedSeason = season;
		selectedQueue = String.valueOf(queueMatches.get(0).getQueue());
		if(queueMatches==null)
			return;
		
		queues= matchService.getQueuesOfCup(cups[0], FootballApi.WEB_MODE);
		cupsComboBox = new ComboBox();
		cupsComboBox.setItems(cups);
		cupsComboBox.setValue(cups[0]);
		cupsComboBox.addValueChangeListener(updateCupInView());
		cupsComboBox.setId("league-title");
		boolean showpredictions = false; 
		final String[] seasons = matchService.getAllSeasons();
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
				
				footballView = new FootballView(queueMatches,seasons,queues,finalShowpredictions);
				this.add(footballView);
		});
		//ekstraklasa.add(showpredictionsDiv);
		SeasonAndQueueView seasonAndQueueView = new SeasonAndQueueView("Season: "+season,queues.get(0).split(" ")[0]+":"+queues.get(0).split(" ")[1]);
		footballView = new FootballView(queueMatches,seasons,queues,showpredictions);

		ComponentUtil.addListener(UI.getCurrent(),SeasonEvent.class,e->{
			this.remove(footballView);
			selectedSeason = e.getSource().getSelectedSeason();
			seasonMatches = matchService.getByCupAndSeason(season,selectedCup,FootballApi.WEB_MODE);
			queueMatches = matchService.getQueueBySeason(season,selectedCup,FootballApi.ANDROID); //TODO maby by selected queue show after change season
			queues = matchService.getQueuesOfCup(cups[0], FootballApi.WEB_MODE);
			seasonAndQueueView.getSeasonDiv().setText("Season: "+selectedSeason);
			seasonAndQueueView.getQueueDiv().setText(queues.get(0));
			footballView = new FootballView(queueMatches,seasons,queues,showpredictions);
			this.add(footballView);
		});
		ComponentUtil.addListener(UI.getCurrent(),QueueEvent.class,e->{
			this.remove(footballView);
			selectedSeason = e.getSource().getSelectedSeason();
			selectedQueue = e.getSource().getSelectedQueue();
			queueMatches = matchService.getQueueByLPQueue(selectedSeason,selectedCup,selectedQueue); //TODO maby by selected queue show after change season
			seasonAndQueueView.getSeasonDiv().setText("Season: "+selectedSeason);
			seasonAndQueueView.getQueueDiv().setText(selectedQueue);
			footballView = new FootballView(queueMatches,seasons,queues,showpredictions);
			this.add(footballView);
		});
		ComponentUtil.addListener(UI.getCurrent(), CupEvent.class, e->{
			this.remove(footballView);
			selectedCup = e.getSource().getSelectedCup();
			queueMatches = matchService.getQueueBySeason(season,selectedCup,FootballApi.WEB_MODE); //TODO maby by selected queue show after change season
			queues = matchService.getQueuesOfCup(selectedCup, FootballApi.WEB_MODE);
			seasonAndQueueView.getSeasonDiv().setText("Season: "+selectedSeason);
			seasonAndQueueView.getQueueDiv().setText(queues.get(0).split(" ")[0]+":"+queues.get(0).split(" ")[1]);
			footballView = new FootballView(queueMatches,seasons,queues,showpredictions);
			this.add(footballView);
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
