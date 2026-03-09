package com.dsquare.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.dsquare.db.TrainingRecord;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TrainingCalendarSummary extends Div {

	public TrainingCalendarSummary(ArrayList<TrainingRecord> trainings) {
		this.setId("training-calendar-summary");
		VerticalLayout calendarWeeks = new VerticalLayout();
		calendarWeeks.setWidth("50%");
		calendarWeeks.setId("calendar-weeks");
		trainings.stream().sorted((e,r) -> e.getDATE_TRAINING().compareTo(r.getDATE_TRAINING()));
		ArrayList<ArrayList<Date>> weeks = new ArrayList<>();
		Map<Date, ArrayList<Date>> weeksMap = new LinkedHashMap<>();
		for (TrainingRecord record : trainings) {
			Date date = record.getDATE_TRAINING();
			Date weekStart = startOfWeekMonday(date);
			weeksMap.computeIfAbsent(weekStart, k -> new ArrayList<>()).add(date);
		}
		weeks = new ArrayList<>(weeksMap.values());
		for(ArrayList<Date> week: weeks) {
			calendarWeeks.add(new CalendarWeek(week));
		}
		calendarWeeks.setHeight(calendarWeeks.getChildren().count()*250+"px");
		calendarWeeks.setId("calendar-weeks");
		this.add(calendarWeeks);
	}
	private Date startOfWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// zero time components
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		int diff = dow - Calendar.MONDAY;
		if (diff < 0) diff += 7;
		cal.add(Calendar.DAY_OF_MONTH, -diff);
		return cal.getTime();
	}
}
