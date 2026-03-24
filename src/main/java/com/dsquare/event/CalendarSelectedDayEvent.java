package com.dsquare.event;

import com.dsquare.view.CalendarWeek.ButtonDay;
import com.dsquare.view.ExerciseDetailsSettings;
import com.dsquare.view.TrainingCalendarSummary;
import com.vaadin.flow.component.ComponentEvent;

public class CalendarSelectedDayEvent extends ComponentEvent<ButtonDay>{
	
	public CalendarSelectedDayEvent(ButtonDay source, boolean fromClient) {
		super(source, fromClient);
	}

}
