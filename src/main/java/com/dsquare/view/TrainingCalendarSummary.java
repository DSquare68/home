package com.dsquare.view;

import java.util.ArrayList;

import com.dsquare.db.TrainingRecord;
import com.vaadin.flow.component.html.Div;

public class TrainingCalendarSummary extends Div {

	public TrainingCalendarSummary(ArrayList<TrainingRecord> trainingsWithExercise) {
		this.setId("training-calendar-summary");
	}

}
