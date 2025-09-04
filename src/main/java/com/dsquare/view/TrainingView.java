package com.dsquare.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.dsquare.model.Training;
import com.dsquare.model.Training.Round;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TrainingView {
	ArrayList<Exercise> exercise;
	
	public TrainingView(Training training, Training lastTraining) {
		exercise = new ArrayList<Exercise>();
		for(String name : training.getExercises()) {
			exercise.add(new Exercise(name,training.getRounds().get(name)));
		}
	}
	private class Exercise extends VerticalLayout{
		HorizontalLayout hl;
		
		public Exercise(String name,ArrayList<Training.Round> rounds) {
			this.setId("exercise-vl");
			hl = new HorizontalLayout();
			for(Training.Round r : rounds) {
				hl.add(new Round(r));
			}
		}
	}
	private class Round extends VerticalLayout{
		public Training.Round round;
		
		public Round(Training.Round round) {
			this.round= round;
			this.setClassName("round-vl");
			Text lp = new Text(round.getRoundNumber()+"");
			HorizontalLayout data = setDataRound();
			this.add(lp,data);
		}

		private HorizontalLayout setDataRound() {
			HorizontalLayout data = new HorizontalLayout();
			Text weight = new Text(round.getWeight()+"");
			Text repeats = new Text(round.getReps()+"");
			data.add(weight,repeats);
			return data;
		}
		
	}
}
