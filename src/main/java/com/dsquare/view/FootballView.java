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

@Data
public class FootballView extends VerticalLayout{

	private static final long serialVersionUID = -9215516004327144312L;
	private ComboBox seasonsComboBox,queueComboBox;
	private String selectedSeason,selectedQueue;
	
	public FootballView(List<MatchRecord> queueMatches, String[] seasons, Integer[] queues) {
		this.setId("football-view-vl");
		Div bellHorizontalDiv = new Div();
		bellHorizontalDiv.setId("bell-horizontal-div");
		selectedSeason = queueMatches.get(0).getSeason();
		seasonsComboBox = new ComboBox();
		seasonsComboBox.setClassName("football-view-combobox");
		seasonsComboBox.setItems(seasons);
		seasonsComboBox.setValue(seasons[0]);
		seasonsComboBox.addValueChangeListener(updateSeasonInView());
		queueComboBox = new ComboBox();
		queueComboBox.setClassName("football-view-combobox");
		queueComboBox.setItems(queues);
		queueComboBox.setValue(queueMatches.get(0).getQueue());
		queueComboBox.addValueChangeListener(updateQueueInView());
		HorizontalLayout comboBoxesLayout = new HorizontalLayout(seasonsComboBox,queueComboBox);
		comboBoxesLayout.setId("football-view-combobox-hl");
		HorizontalLayout matchesDiv = new HorizontalLayout();
		matchesDiv.setWidth(100, Unit.PERCENTAGE);
		VerticalLayout leftMatchesVl = new VerticalLayout();
		VerticalLayout rightMatchesVl = new VerticalLayout();
		leftMatchesVl.setWidth(50, Unit.PERCENTAGE);
		rightMatchesVl.setWidth(50, Unit.PERCENTAGE);
		for(int i=0;i<queueMatches.size();i++) {
			MatchView matchView = new MatchView(queueMatches.get(i));
			Div bellVerticalDiv = new Div();
			bellVerticalDiv.setId("bell-vertical-div");
			if(i%2==0)
				leftMatchesVl.add(matchView);
			else
				rightMatchesVl.add(matchView);
		}
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

}
