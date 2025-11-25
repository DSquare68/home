package com.dsquare.view;

import com.dsquare.db.MatchRecord;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MatcheView extends VerticalLayout {

	public MatcheView(MatchRecord matchRecord) {
		this.setClassName("matches-view");
	}
}
