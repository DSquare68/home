package com.dsquare.event;

import com.dsquare.view.ExerciseDetails;
import com.dsquare.view.FootballView;
import com.vaadin.flow.component.ComponentEvent;

public class ExerciseDetailsEvent extends ComponentEvent<ExerciseDetails>{
	
	public ExerciseDetailsEvent(ExerciseDetails source, boolean fromClient) {
		super(source, fromClient);
	}

}
