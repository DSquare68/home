package com.dsquare.view;

import java.util.ArrayList;

import com.dsquare.model.Exercise;
import com.dsquare.model.Round;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ExerciseView extends HorizontalLayout {
	private H3 name;
	public ExerciseView(Exercise e) {
		name = new H3(e.getName());
		HorizontalLayout rounds = new HorizontalLayout();
		for (int i = 0; i < e.getRounds().size(); i++) {
			rounds.add(new RoundView(e.getRounds().get(i)));
		}
		add(name, rounds);
	}

	private class RoundView extends HorizontalLayout {
		private HorizontalLayout set;
		
		public RoundView(Round r) {
			H3 amount = new H3(String.valueOf(r.getAmount()));
			H3 weight = new H3(String.valueOf(r.getWeight()));
			set = new HorizontalLayout(amount, weight);
			add(set);
		}
	}
}
