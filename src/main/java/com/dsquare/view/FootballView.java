package com.dsquare.view;

import com.dsquare.event.SeasonEvent;
import com.dsquare.event.QueueEvent;

import java.util.ArrayList;
import java.util.List;

import com.dsquare.db.MatchRecord;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.Data;
import lombok.Getter;

@Data
public class FootballView extends VerticalLayout{

	private static final long serialVersionUID = -9215516004327144312L;
	private ComboBox seasonsComboBox,queueComboBox;
	private String selectedSeason,selectedQueue;
	private ArrayList<MatchRecord> queueMatches, predictionMatches;
	private boolean showPredictions;
	private ArrayList<String> seasons, queues;
	private HorizontalLayout matchesDiv;
	
	public FootballView() {
		this.setId("football-view-vl");
		Div bellHorizontalDiv = new Div();
		bellHorizontalDiv.setId("bell-horizontal-div");
		seasonsComboBox = new ComboBox();
		seasonsComboBox.setClassName("football-view-combobox");
		seasonsComboBox.addValueChangeListener(updateSeasonInView());
		queueComboBox = new ComboBox();
		queueComboBox.setClassName("football-view-combobox");
		queueComboBox.addValueChangeListener(updateQueueInView());
		HorizontalLayout comboBoxesLayout = new HorizontalLayout(seasonsComboBox,queueComboBox);
		comboBoxesLayout.setId("football-view-combobox-hl");
		matchesDiv = new HorizontalLayout();
		matchesDiv.setWidth(100, Unit.PERCENTAGE);
		VerticalLayout leftMatchesVl = new VerticalLayout();
		VerticalLayout rightMatchesVl = new VerticalLayout();
		leftMatchesVl.setWidth(50, Unit.PERCENTAGE);
		rightMatchesVl.setWidth(50, Unit.PERCENTAGE);
		matchesDiv.setId("matches-div");
		matchesDiv.add(leftMatchesVl, rightMatchesVl);
		// TODO Auto-generated constructor stub
		add(comboBoxesLayout,matchesDiv);
		
	}

	private ValueChangeListener updateQueueInView() {
		FootballView footballView = this;
		return new ValueChangeListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChanged(ValueChangeEvent value) {
				if(value.getHasValue().isEmpty())
					return;
				selectedQueue = String.valueOf(value.getValue());
				ComponentUtil.fireEvent(UI.getCurrent(), new QueueEvent(footballView,false));
				
			}
		};
	}

	private ValueChangeListener updateSeasonInView() {
		FootballView footballView = this;
		return new ValueChangeListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChanged(ValueChangeEvent value) {
				if(value.getHasValue().isEmpty())
					return;
				selectedSeason = (String) value.getValue();
				ComponentUtil.fireEvent(UI.getCurrent(), new SeasonEvent(footballView,false));
				
			}
		};
	}
	public void setQueues(ArrayList<String> queues) {
		this.queues = queues;
		queueComboBox.setItems(queues);
	}
	
	public void setSeasons(ArrayList<String> seasons) {
		this.seasons = seasons;
		seasonsComboBox.setItems(seasons);
	}
	
	public void setQueueMatches(ArrayList<MatchRecord> queueMatches) {
		this.queueMatches = queueMatches;
		VerticalLayout left =(VerticalLayout) matchesDiv.getComponentAt(0);
		VerticalLayout right =(VerticalLayout) matchesDiv.getComponentAt(1);
		for(int i=0;i<queueMatches.size();i++) {
			MatchView matchView = new MatchView(queueMatches.get(i));
			Div bellVerticalDiv = new Div();
			bellVerticalDiv.setId("bell-vertical-div");
			if(i%2==0)
				left.add(matchView);
			else
				right.add(matchView);
		}
	}
	
}
