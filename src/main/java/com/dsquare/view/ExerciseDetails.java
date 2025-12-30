package com.dsquare.view;

import java.util.ArrayList;

import com.dsquare.event.ExerciseDetailsEvent;

import com.dsquare.db.ExerciseNames;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.Getter;


public class ExerciseDetails extends VerticalLayout {
	@Getter
	private String exerciseName;
	private ComboBox exerciseComboBox;
	public ExerciseDetails(ArrayList<ExerciseNames> exerciseNames) {
		this.setId("exercise-details-vl");
		exerciseComboBox = new ComboBox();
		exerciseComboBox.setId("exercise-details-combobox");
		exerciseComboBox.setItems(exerciseNames.stream().map(ExerciseNames::getName).toArray(String[]::new));
		exerciseComboBox.addValueChangeListener(setExerciseLisener());
		this.add(exerciseComboBox);
	}
	
	private ValueChangeListener setExerciseLisener() {
		ExerciseDetails ed = this;
		return new ValueChangeListener() {

			@Override
			public void valueChanged(ValueChangeEvent event) {
				if(event.getHasValue().isEmpty())
					return;
				exerciseName = event.getValue().toString();
				ComponentUtil.fireEvent(UI.getCurrent(),new ExerciseDetailsEvent(ed,false));
			}
		};
	}
}
