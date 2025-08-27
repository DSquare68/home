package com.dsquare.page;

import java.util.ArrayList;

import com.dsquare.model.Training;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;
import com.dsquare.view.GymTitle;
import com.dsquare.view.TrainingOverview;
import com.dsquare.view.TrainingsPast;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(layout = Home.class, value = "gym")
public class Gym extends Div{
	
	private static final long serialVersionUID = 3275850456945504655L;
	private Training schema;
	private GymTitle title;
	private ArrayList<Training> schemas;
	public Gym(ExerciseNamesServiceImpl namesService, TrainingServiceImpl trainingService){
		//super(namesService,trainingService);
		schemas = trainingService.getSchemasDataTraining();
		title = new GymTitle(schemas);
		title.setId("gym-title-vl");
		//setContent(title);
		add(title);
	}	
}
