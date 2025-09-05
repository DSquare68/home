package com.dsquare.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.dsquare.model.Training;
import com.dsquare.model.Training.Round;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TrainingView extends VerticalLayout{
	ArrayList<Exercise> exercise;
	
	public TrainingView(Training training, Training lastTraining) {
		this.setClassName("training-view-vl");
		Div nameTraining = new Div(training.getName());
		add(nameTraining);
		exercise = new ArrayList<Exercise>();
		for(String name : training.getExercises()) {
			exercise.add(new Exercise(name,training.getRounds().get(name)));
			this.add(exercise.get(exercise.size()-1));
		}
	}
	private class Exercise extends VerticalLayout{
		HorizontalLayout hl;
		
		public Exercise(String name,ArrayList<Training.Round> rounds) {
			this.addClassName("exercise-hl");
			hl = new HorizontalLayout();
			Div nameT = new Div(name);
			for(Training.Round r : rounds) {
				hl.add(new Round(r));
			}
			this.add(nameT,hl);
		}
	}
	private class Round extends HorizontalLayout{
		public Training.Round round;
		
		public Round(Training.Round round) {
			this.round= round;
			this.setClassName("round-vl");
			Div lp = new Div(round.getRoundNumber()+"");
			lp.setClassName("lp-Div");
			VerticalLayout data = setDataRound();
			this.add(lp,data);
		}

		private VerticalLayout setDataRound() {
			VerticalLayout data = new VerticalLayout();
			data.setClassName("data-round-vl");
			Div weight = new Div(round.getWeight()+"");
			Div repeats = new Div(round.getReps()+"");
			data.add(weight,repeats);
			return data;
		}
		
	}
}
