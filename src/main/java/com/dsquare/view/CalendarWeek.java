package com.dsquare.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.dsquare.db.TrainingRecord;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CalendarWeek extends HorizontalLayout {

	
	public CalendarWeek(ArrayList<Date> dates) {
		this.setClassName("calendar-week");
		String[] days = {"M","Tw","W","Th","F","Sa","Su"};
		
		for(String day: days) {
			Div dayLabel = new Div(day);
			dayLabel.setClassName("calendar-day-label-no-trening");
			this.add(dayLabel);
		}
		for(Date date: dates) {
			int d = date.getDay()==0? 6 : date.getDay()-1;
			this.getChildren().toList().get(d).setClassName("calendar-day-label-trening");
		}
	}
}
