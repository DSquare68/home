package com.dsquare.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.dsquare.db.TrainingRecord;
import com.dsquare.model.ExerciseDetailsData;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
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
		ExerciseDetailsData[] data= new ExerciseDetailsData[weights.length];
		int k=0;
		for(Object w:weights) {
			data[k++] = new ExerciseDetailsData((double)w,trainingsWithExercise.stream()
													.filter(e->e.getWEIGHT()==(double)w)
													.collect(Collectors.toCollection(ArrayList::new)),
												allCount);
		}
		for(int i=0;i<weights.length;i++) {
			this.add(new WeightSet(data[i]));
		}
	}
	class WeightSet extends Div{
		Double weight;
		int[] reps;
		
		public WeightSet(ExerciseDetailsData data) {
			this.setClassName("exercise-details-weight-set");
			this.weight = data.getWeight();
			this.reps = data.getReps();
			this.setHeight("300px");
			this.setWidth(150*reps.length+"px");
			DecimalFormat df = new DecimalFormat("#0.00");
			Span weightSpan = new Span(df.format(weight)+" kg");
			weightSpan.setClassName("exercise-details-span-blue");
			Span percentSpan = new Span(df.format(data.getPercentOfWeight()));
			percentSpan.setClassName("exercise-details-span-blue");
			Span percentTextSpan = new Span(" % of total\n");
			percentTextSpan.setClassName("exercise-details-span-white");
			Span lineBreak = new Span(" | ");
			Div weightDiv = new Div();
			weightDiv.add(weightSpan,lineBreak,percentSpan,percentTextSpan);
			Div[] repsDivs = new Div[reps.length];
			for(int i=0;i<reps.length;i++) {
				Span repSpan = new Span(reps[i]+"");
				repSpan.setClassName("exercise-details-span-blue");
				Span repTextSpan = new Span(" reps ");
				repTextSpan.setClassName("exercise-details-span-white");
				Span lpCount = new Span(data.getRepsCount()[i]+"");
				lpCount.setClassName("exercise-details-span-blue");
				Span lpCountSpan = new Span(" LP\n");
				lpCountSpan.setClassName("exercise-details-span-white");
				Span weightPercentSpan = new Span(df.format(data.getPercentRepsInWeight()[i]));
				weightPercentSpan.setClassName("exercise-details-span-blue");
				Span percentInWeightSpan = new Span(" % in weight\n");
				percentInWeightSpan.setClassName("exercise-details-span-white");
				Span totalPercentSpan = new Span(df.format(data.getPercentOfRepsInTotal()[i]));
				totalPercentSpan.setClassName("exercise-details-span-blue");
				Span percentInTotalSpan = new Span(" % of total");
				percentInTotalSpan.setClassName("exercise-details-span-white");
				repsDivs[i] = new Div();
				repsDivs[i].add(repSpan,repTextSpan,lpCount,lpCountSpan,weightPercentSpan,percentInWeightSpan,totalPercentSpan,percentInTotalSpan);
				repsDivs[i].setHeight(125 + "px");
				repsDivs[i].setWidth(1025 + "px");
				repsDivs[i].setClassName("exercise-details-reps-div");
			}
			Div repsContainer = new Div();
			repsContainer.add(repsDivs);
			repsContainer.setClassName("exercise-details-reps-container");
			add(weightDiv,repsContainer);
			
		}
	}
}
