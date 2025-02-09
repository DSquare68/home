package com.dsquare.view;

import com.dsquare.home.page.Home;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.ThemeVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(layout = Home.class)
public class HomePage extends VerticalLayout {

	private static final long serialVersionUID = -8852819231764919403L;

	public HomePage() {
		this.setId("main-vl");
		this.setSizeFull();
		CircleProgress timeBar = new CircleProgress();
		timeBar.setId("time-bar");
		timeBar.setClassName("bar-circle");
		CircleProgress stateBar = new CircleProgress();
		stateBar.setId("state-bar");
		stateBar.setClassName("bar-circle");
		timeBar.setTimeBar();
		HorizontalLayout time = new HorizontalLayout();
		time.setId("time-hl");
		time.add(timeBar,stateBar);
		add(time);
	}
}
