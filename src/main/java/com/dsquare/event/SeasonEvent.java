package com.dsquare.event;

import com.dsquare.view.FootballView;
import com.vaadin.flow.component.ComponentEvent;

public class SeasonEvent extends ComponentEvent<FootballView>{

	public SeasonEvent(FootballView source, boolean fromClient) {
		super(source, fromClient);
	}
}
