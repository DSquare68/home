package com.dsquare.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.dsquare.db.TrainingRecord;
import com.dsquare.event.TrainingCalendarSummaryEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import lombok.Getter;

import com.dsquare.event.CalendarSelectedDayEvent;

public class CalendarWeek extends HorizontalLayout {

	
	public class ButtonDay extends Button {
		@Getter
		private String day;
		public ButtonDay(String day) {
			this.day = day;	
			ButtonDay ed = this;
			this.setText(day);
			this.setClassName("calendar-day-label-no-trening");
			this.setEnabled(false);
			this.addClickListener(e -> {
				this.setClassName("calendar-day-label-trening-selected");
				ComponentUtil.fireEvent(UI.getCurrent(),new CalendarSelectedDayEvent(ed,false));
			});
		}

	}

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
			ButtonDay dayLabel = new ButtonDay(day);
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
