package com.dsquare.view;

import java.util.ArrayList;

import com.dsquare.model.Exercise;
import com.dsquare.model.Training;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TrainingView extends VerticalLayout {
	private ArrayList<ExerciseView> exercises ; 
	public TrainingView(Training training) {
		exercises = new ArrayList<ExerciseView>();
		for(Exercise e : training.getExercises()) {
			exercises.add(new ExerciseView(e));
		}
	}
}
