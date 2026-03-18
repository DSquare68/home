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

	
	public CalendarWeek(ArrayList<Date> dates, ArrayList<Integer> duration) {
		this.setClassName("calendar-week");
		String[] days = {"M","Tw","W","Th","F","Sa","Su"};
		Div dateLabel = new Div();
		dateLabel.setClassName("calendar-date-label");
		int dayInt = dates.get(0).getDay();
		Date dateMonday = dates.get(0);
		dateMonday.setDate(dateMonday.getDate()-(dayInt==0? 6 : dayInt-1));
		Date dateSunday = new Date(dateMonday.getTime());
		dateSunday.setDate(dateMonday.getDate()+6);
		dateLabel.setText(dateMonday.getDate()+"."+ (dateMonday.getMonth()+1)+"-"+dateSunday.getDate()+"."+(dateSunday.getMonth()+1));
		this.add(dateLabel);
		for(String day: days) {
			Div dayLabel = new Div(day);
			dayLabel.setClassName("calendar-day-label-no-trening");
			this.add(dayLabel);
		}
		for(Date date: dates) {
			int d = date.getDay()==0? 6 : date.getDay()-1;
			this.getChildren().toList().get(1+d).setClassName("calendar-day-label-trening");
		}
		int sum = duration.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
		Div durationLabel = new Div(sum/60+"h "+sum%60+"m");
		durationLabel.setClassName("calendar-duration-label");
		this.add(durationLabel);
	}
}
