package com.dsquare.event;

import com.dsquare.page.Football;
import com.dsquare.view.FootballView;
import com.vaadin.flow.component.ComponentEvent;

public class CupEvent extends ComponentEvent<Football>{
	
	public CupEvent(Football source, boolean fromClient) {
		super(source, fromClient);
	}

}
