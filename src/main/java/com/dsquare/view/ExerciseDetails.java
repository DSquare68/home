package com.dsquare.view;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.dsquare.db.TrainingRecord;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ExerciseDetails extends HorizontalLayout {

	public ExerciseDetails(ArrayList<TrainingRecord> trainingsWithExercise) {
		this.setId("exercise-details-layout");
		int allCount = trainingsWithExercise.size();
		Object[] weights = trainingsWithExercise.stream()
								.mapToDouble(TrainingRecord::getWEIGHT)
								.distinct()
								.boxed()
								.toArray();
		int count = trainingsWithExercise.size();
		int[][] repsPerWeight = new int[weights.length][];
		int[][] repsPerWeightCount = new int[weights.length][];
		int j=0,k=0;
		for(Object w : weights) {
			ArrayList<TrainingRecord> recordsForWeight = trainingsWithExercise.stream().filter(e->e.getWEIGHT()==(double)w).collect(Collectors.toCollection(ArrayList::new));
			repsPerWeight[j] = recordsForWeight.stream().mapToInt(TrainingRecord::getREPEAT).distinct().toArray();
			for(int rep : repsPerWeight[j]) {
				repsPerWeightCount[j][k++] =(int) trainingsWithExercise.stream()
											.filter(e->e.getWEIGHT()==(double)w&&e.getREPEAT()==rep)
											.count();
			}
			j++;
		}
		for(int i=0;i<weights.length;i++) {
			this.add(new WeightSet((double)weights[i],repsPerWeight[i],repsPerWeightCount[i]));
		}
	}
	class WeightSet extends Div{
		Double weight;
		int[] reps;
		
		public WeightSet(Double weight, int[] reps,int[] repsCount) {
			this.setClassName("exercise-details-weight-set");
			this.weight = weight;
			this.reps = reps;
			int len = reps.length;
			this.setHeight("300px");
			this.setWidth(150*len+"px");
			Div weightDiv = new Div(weight+" kg");
			Div[] repsDivs = new Div[len];
			for(int i=0;i<len;i++) {
				repsDivs[i] = new Div(reps[i]+" reps");
				repsDivs[i].setHeight(125 + "px");
				repsDivs[i].setWidth(125 + "px");
				repsDivs[i].setClassName("exercise-details-reps-div");
			}
			Div repsContainer = new Div();
			repsContainer.add(repsDivs);
			repsContainer.setClassName("exercise-details-reps-container");
			add(weightDiv,repsContainer);
			
		}
	}
}
