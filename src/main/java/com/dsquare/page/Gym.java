package com.dsquare.page;

import com.dsquare.model.Training;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;
import com.dsquare.view.TrainingOverview;
import com.dsquare.view.TrainingsPast;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(layout = Home.class, value = "gym")
public class Gym extends Home{
	
	private static final long serialVersionUID = 3275850456945504655L;
	private Training schema;
	public Gym(ExerciseNamesServiceImpl namesService, TrainingServiceImpl trainingService){
		super(namesService,trainingService);
	}	
}
