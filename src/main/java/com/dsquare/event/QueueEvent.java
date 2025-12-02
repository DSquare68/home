package com.dsquare.event;

import com.dsquare.view.FootballView;
import com.vaadin.flow.component.ComponentEvent;

public class QueueEvent extends ComponentEvent<FootballView>{
	
	public QueueEvent(FootballView source, boolean fromClient) {
		super(source, fromClient);
	}

}
