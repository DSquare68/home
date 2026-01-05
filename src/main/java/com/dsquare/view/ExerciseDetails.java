package com.dsquare.view;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.dsquare.db.TrainingRecord;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ExerciseDetails extends HorizontalLayout {

	public ExerciseDetails(ArrayList<TrainingRecord> trainingsWithExercise) {
		Double[] weights = (Double[]) trainingsWithExercise.stream()
								.mapToDouble(TrainingRecord::getWEIGHT)
								.distinct()
								.boxed()
								.toArray();
		
	}
	class WeightSet extends Div{
		Double weight;
		ArrayList<TrainingRecord> records;
		
		public WeightSet(Double weight, ArrayList<TrainingRecord> records) {
			this.setClassName("exercise-details-weight-set");
			this.weight = weight;
			this.records = records;
		}
	}
}
