package com.dsquare.page;

import java.util.ArrayList;

import com.dsquare.event.SchemaEvent;
import com.dsquare.model.Training;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;
import com.dsquare.view.GymTitle;
import com.dsquare.view.TrainingOverview;
import com.dsquare.view.TrainingView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(layout = Home.class, value = "gym")
public class Gym extends Div{
	
	private static final long serialVersionUID = 3275850456945504655L;
	private Training schema, training;
	private TrainingView schemaView, trainingView;
	private GymTitle title;
	private ArrayList<Training> schemas;
	public Gym(ExerciseNamesServiceImpl namesService, TrainingServiceImpl trainingService){
		//super(namesService,trainingService);
		schemas = trainingService.getSchemasDataTraining();
		title = new GymTitle(schemas);
		title.setTrainingReadPerSchema(trainingService,namesService);
		ComponentUtil.addListener(UI.getCurrent(),SchemaEvent.class,e->{
			schema = e.getSource().getSchema();
			schemaView = new TrainingView(schema,null);
			this.add(schemaView);
			schemaView.setWidth(40,Unit.PERCENTAGE);
		});
		//title.setOnSchemaSelected();
		//setContent(title);
		add(title);
		HorizontalLayout yl = new HorizontalLayout();
	}
}
