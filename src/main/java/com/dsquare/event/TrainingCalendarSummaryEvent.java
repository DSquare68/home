package com.dsquare.event;

import com.dsquare.view.FootballView;
import com.dsquare.view.TrainingCalendarSummary;
import com.dsquare.view.TrainingCalendarSummarySettings;
import com.vaadin.flow.component.ComponentEvent;

public class TrainingCalendarSummaryEvent extends ComponentEvent<TrainingCalendarSummarySettings>{

		public TrainingCalendarSummaryEvent(TrainingCalendarSummarySettings source, boolean fromClient) {
			super(source, fromClient);
		}
	}

