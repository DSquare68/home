package com.dsquare.page;

import java.util.ArrayList;

import com.dsquare.db.ExerciseNames;
import com.dsquare.db.TrainingRecord;
import com.dsquare.event.ExerciseDetailsEvent;
import com.dsquare.event.SchemaEvent;
import com.dsquare.event.TrainingEvent;
import com.dsquare.model.Training;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;
import com.dsquare.view.ExerciseDetails;
import com.dsquare.view.ExerciseDetailsSettings;
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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(layout = Home.class, value = "gym")
public class Gym extends Div{
	
	private static final long serialVersionUID = 3275850456945504655L;
	private HorizontalLayout trainings;
	private Div exercise;
	private Training schema, training;
	private TrainingView schemaView, trainingView;
	private GymTitle title;
	private ArrayList<Training> schemas;
	private ArrayList<ExerciseNames> exerciseNames;
	public Gym(ExerciseNamesServiceImpl namesService, TrainingServiceImpl trainingService){
		//super(namesService,trainingService);
		trainings = new HorizontalLayout();
		trainings.setId("trainings-hl");
		schemas = trainingService.getSchemasDataTraining();
		exerciseNames = namesService.getAllExerciseNames();
		title = new GymTitle(schemas);
		title.setTrainingReadPerSchema(trainingService,namesService);
		exerciseDetails = new ExerciseDetails();
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
		ComponentUtil.addListener(UI.getCurrent(),ExerciseDetailsEvent.class,e->{
			String exercise = e.getSource().getExerciseName();
			int id = namesService.getExerciseIdByName(exercise);
			ArrayList<TrainingRecord> trainingsWithExercise = trainingService.getTrainingsWithExercise(id);
			ExerciseDetails exerciseDetails = new ExerciseDetails(trainingsWithExercise);
			
		});
		ExerciseDetailsSettings exerciseDetails = new ExerciseDetailsSettings(exerciseNames);
		//title.setOnSchemaSelected();
		//setContent(title);
		add(new VerticalLayout(title,exerciseDetails),trainings);
		
	}
}
