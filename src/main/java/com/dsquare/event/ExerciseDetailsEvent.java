package com.dsquare.event;

import com.dsquare.view.ExerciseDetails;
import com.dsquare.view.ExerciseDetailsSettings;
import com.dsquare.view.FootballView;
import com.vaadin.flow.component.ComponentEvent;

public class ExerciseDetailsEvent extends ComponentEvent<ExerciseDetailsSettings>{
	
	public ExerciseDetailsEvent(ExerciseDetailsSettings source, boolean fromClient) {
		super(source, fromClient);
	}

}
