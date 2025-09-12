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
	private String[] emptyName = {"a","b","c","d","e","f","g","h","j","k","l"};
	public TrainingView(Training training, Training lastTraining) {
		this.setClassName("training-view-vl");
		Div nameTraining = new Div(training.getName());
		nameTraining.setClassName("name-training-div");
		add(nameTraining);
		exercise = new ArrayList<Exercise>();
		String nameLastTraining;
		int k=0;
		for(String name : training.getExercises()) {
			if(lastTraining.getName().equals("Empty"))
				nameLastTraining = emptyName[k++];
			else 
				nameLastTraining = name;
			exercise.add(new Exercise(name,training.getRounds().get(name),lastTraining.getRounds().get(nameLastTraining)));
			this.add(exercise.get(exercise.size()-1));
		}
	}
	private class Exercise extends VerticalLayout{
		HorizontalLayout hl;
		
		public Exercise(String name,ArrayList<Training.Round> rounds, ArrayList<Training.Round> lastRounds) {
			this.addClassName("exercise-hl");
			hl = new HorizontalLayout();
			Div nameT = new Div(name);
			nameT.setClassName("name-exercise-div");
			for(int i=0;i<rounds.size();i++) {
				hl.add(new Round(rounds.get(i),lastRounds.get(i)));
			}
			this.add(nameT,hl);
		}
	}
	private class Round extends HorizontalLayout{
		public Training.Round round;
		
		public Round(Training.Round round, Training.Round lastRound) {
			this.round= round;
			this.setClassName("round-vl");
			Div lp = new Div(round.getRoundNumber()+"");
			lp.setClassName("lp-div");
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
