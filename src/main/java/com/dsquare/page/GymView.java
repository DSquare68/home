package com.dsquare.page;

import com.dsquare.view.TrainingOverview;
import com.dsquare.view.TrainingsPast;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(layout = Home.class, value = "gym")
public class GymView extends HorizontalLayout {
	
	private static final long serialVersionUID = 3275850456945504655L;

	public GymView() {
		add(new TrainingsPast(),new TrainingOverview(null));
	}
	
}
