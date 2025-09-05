package com.dsquare.event;

import com.dsquare.view.GymTitle;
import com.vaadin.flow.component.ComponentEvent;

public class SchemaEvent extends ComponentEvent<GymTitle> {

	public SchemaEvent(GymTitle source, boolean fromClient) {
		super(source, fromClient);
	}

}
