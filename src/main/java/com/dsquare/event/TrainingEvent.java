package com.dsquare.event;

import com.dsquare.view.GymTitle;
import com.vaadin.flow.component.ComponentEvent;

public class TrainingEvent extends ComponentEvent<GymTitle> {

	public TrainingEvent(GymTitle source, boolean fromClient) {
		super(source, fromClient);
	}
}
