package com.dsquare.page;

import java.util.ArrayList;
import java.util.Calendar;

import com.dsquare.db.ExerciseNames;
import com.dsquare.db.TrainingRecord;
import com.dsquare.event.ExerciseDetailsEvent;
import com.dsquare.event.SchemaEvent;
import com.dsquare.event.TrainingCalendarSummaryEvent;
import com.dsquare.event.TrainingEvent;
import com.dsquare.model.Training;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;
import com.dsquare.view.ExerciseDetails;
import com.dsquare.view.ExerciseDetailsSettings;
import com.dsquare.view.GymTitle;
import com.dsquare.view.TrainingCalendarSummary;
import com.dsquare.view.TrainingCalendarSummarySettings;
import com.dsquare.view.TrainingCalendarSummarySettings;
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
	private ExerciseDetails exerciseDetails;
	private HorizontalLayout trainings;
	private Training schema, training;
	private TrainingView schemaView, trainingView;
	private GymTitle title;
	private ArrayList<Training> schemas;
	private ArrayList<ExerciseNames> exerciseNames;
	private Div exerciseDetailsDiv,trainingCalendarSummaryDiv;
	private TrainingCalendarSummary trainingCalendarSummary;
	private TrainingCalendarSummarySettings trainingCalendarSummarySettings;
	public Gym(ExerciseNamesServiceImpl namesService, TrainingServiceImpl trainingService){
		//super(namesService,trainingService);
		trainings = new HorizontalLayout();
		trainings.setId("trainings-hl");
		schemas = trainingService.getSchemasDataTraining();
		exerciseNames = namesService.getAllExerciseNames();
		title = new GymTitle(schemas);
		title.setTrainingReadPerSchema(trainingService,namesService);
		exerciseDetailsDiv = new Div();
		trainingCalendarSummaryDiv = new Div();
		trainingCalendarSummaryDiv.setWidth(100,Unit.PERCENTAGE);
		String[] years = trainingService.getYearsWithTrainings();
		String[] mounths = trainingService.getMountsWithTrainings();
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
		ComponentUtil.addListener(UI.getCurrent(),TrainingCalendarSummaryEvent.class,e->{
			if(this.trainingCalendarSummaryDiv.getChildren().filter(f->f.equals(trainingCalendarSummary)).findAny().isPresent()) {
				this.trainingCalendarSummaryDiv.remove(trainingCalendarSummary);
			}
			int year = e.getSource().getYear();
			int mount = e.getSource().getMount();
			ArrayList<TrainingRecord> trainingsWithExercise = trainingService.getTrainingsByYearAndMount(year,mount);
			
			trainingCalendarSummary=new TrainingCalendarSummary(trainingService,trainingsWithExercise,year,mount);
			trainingCalendarSummaryDiv.add(trainingCalendarSummary);
			
		});
		//trainingCalendarSummary=new TrainingCalendarSummary(trainingService,trainingService.getAllFromMount(Calendar.MONTH+1,Calendar.YEAR),Calendar.YEAR,Calendar.MONTH+1); //TODO remove on production
		//trainingCalendarSummaryDiv.add(trainingCalendarSummary);
		ExerciseDetailsSettings exerciseDetailsSettings = new ExerciseDetailsSettings(exerciseNames);
		TrainingCalendarSummarySettings trainingCalendarSummarySettings = new TrainingCalendarSummarySettings(years,mounths);
		add(new VerticalLayout(title,exerciseDetailsSettings,trainingCalendarSummarySettings,trainings,exerciseDetailsDiv,trainingCalendarSummaryDiv));
		
	}
}
