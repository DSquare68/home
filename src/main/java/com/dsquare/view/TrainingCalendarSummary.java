package com.dsquare.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Map<Date, ArrayList<Integer>> durationMap = new LinkedHashMap<>();
		ArrayList<ArrayList<Integer>> durations = new ArrayList<>();
		int ID_training = 0;
		for (TrainingRecord record : trainings) {
			Date date = record.getDATE_TRAINING();
			Date weekStart = startOfWeekMonday(date);
			weeksMap.computeIfAbsent(weekStart, k -> new ArrayList<>()).add(date);
			if(ID_training != record.getID_TRAINING()) {
				ID_training = record.getID_TRAINING();
				durationMap.computeIfAbsent(weekStart, k -> new ArrayList<>()).add(timeToInt(record.getTIME_TRAINING()));
			}
		}
		weeks = new ArrayList<>(weeksMap.values());
		durations = new ArrayList<>(durationMap.values());
		for(int i=0; i<weeks.size(); i++) {
			calendarWeeks.add(new CalendarWeek(weeks.get(i),durations.get(i)));
		}
		calendarWeeks.setHeight(calendarWeeks.getChildren().count()*75+"px");
		calendarWeeks.setId("calendar-weeks");
		this.add(calendarWeeks);
	}
	private Integer timeToInt(String time_TRAINING) {
		if (time_TRAINING == null) return 0;
		String s = time_TRAINING.trim().toLowerCase();
		try {
			// HH:mm or H:mm or HH:mm:ss
			Pattern p1 = Pattern.compile("^(\\d{1,2}):(\\d{2})(?::\\d{2})?$");
			Matcher m1 = p1.matcher(s);
			if (m1.matches()) {
				int h = Integer.parseInt(m1.group(1));
				int min = Integer.parseInt(m1.group(2));
				return h * 60 + min;
			}
		}catch (NumberFormatException e) {
			System.out.println("Invalid time format: " + time_TRAINING);
		}
		return 0;
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
