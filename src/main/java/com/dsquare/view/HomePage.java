package com.dsquare.view;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class HomePage extends VerticalLayout {

	private static final long serialVersionUID = -8852819231764919403L;

	public HomePage() {
		this.setId("home-page");
		CircleProgress timeBar = new CircleProgress();
		CircleProgress stateBar = new CircleProgress();
		timeBar.setTimeBar();
		HorizontalLayout time = new HorizontalLayout();
		time.add(timeBar,stateBar);
		add(time);
	}
}
