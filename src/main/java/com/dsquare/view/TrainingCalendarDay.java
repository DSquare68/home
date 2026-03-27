package com.dsquare.view;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.dsquare.api.FootballApi;
import com.dsquare.db.TrainingRecord;
import com.dsquare.event.SeasonEvent;
import com.dsquare.service.TrainingServiceImpl;
import com.dsquare.view.CalendarWeek.ButtonDay;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.dsquare.event.CalendarSelectedDayEvent;

public class TrainingCalendarDay extends VerticalLayout {
	private Date date;
	private ArrayList<TrainingRecord> trainings;
	public TrainingCalendarDay(TrainingServiceImpl trainingService) {
		this.setId("training-calendar-day");
		this.setWidth("50%");
		ComponentUtil.addListener(UI.getCurrent(),CalendarSelectedDayEvent.class,e->{
			this.remove();
			date = e.getSource().getDate();
			if(date != null) 
				trainings = trainingService.getTrainingsByDay(date.getYear()-100,"/"+(date.getMonth()+1)+"/"+date.getDate());
			this.add();
		});
		
	}
	

}
