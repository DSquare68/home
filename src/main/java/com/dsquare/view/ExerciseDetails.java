package com.dsquare.view;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.dsquare.db.TrainingRecord;

public class ExerciseDetails {

	public ExerciseDetails(ArrayList<TrainingRecord> trainingsWithExercise) {
		ArrayList<Double> weights = (ArrayList<Double>) trainingsWithExercise.stream()
								.mapToDouble(TrainingRecord::getWEIGHT)
								.distinct()
								.boxed() // Convert IntStream to Stream<Integer>
								.collect(Collectors.toList());
	}

}
