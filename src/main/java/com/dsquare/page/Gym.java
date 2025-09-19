package com.dsquare.page;

import java.util.ArrayList;

import com.dsquare.event.SchemaEvent;
import com.dsquare.event.TrainingEvent;
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
	private HorizontalLayout trainings;
	private Training schema, training;
	private TrainingView schemaView, trainingView;
	private GymTitle title;
	private ArrayList<Training> schemas;
	public Gym(ExerciseNamesServiceImpl namesService, TrainingServiceImpl trainingService){
		//super(namesService,trainingService);
		trainings = new HorizontalLayout();
		trainings.setId("trainings-hl");
		schemas = trainingService.getSchemasDataTraining();
		title = new GymTitle(schemas);
		title.setTrainingReadPerSchema(trainingService,namesService);
		ComponentUtil.addListener(UI.getCurrent(),SchemaEvent.class,e->{
			this.trainings.removeAll();
			schema = e.getSource().getSchema();
			Training  lastTraining = new Training();
			schemaView = new TrainingView(schema,lastTraining);
			schemaView.setId("schema-view-vl");
			this.trainings.add(schemaView);
			schemaView.setWidth(40,Unit.PERCENTAGE);
		});
		ComponentUtil.addListener(UI.getCurrent(),TrainingEvent.class,e->{
			if(this.trainings.getChildren().filter(f->f.equals(trainingView)).findAny().isPresent())
				this.trainings.remove(trainingView);
			training = e.getSource().getSelectedTraining();
			Training  lastTraining = e.getSource().getPreviousTraining();
			trainingView = new TrainingView(training,lastTraining);
			trainingView.setId("training-view-vl");
			this.trainings.add(trainingView);
			trainingView.setWidth(40,Unit.PERCENTAGE);
		});
		//title.setOnSchemaSelected();
		//setContent(title);
		add(title);
		add(trainings);
		HorizontalLayout yl = new HorizontalLayout();
	}
}
