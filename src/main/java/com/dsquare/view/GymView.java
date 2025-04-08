package com.dsquare.view;

import java.util.Calendar;

import com.dsquare.service.ExerciseNamesServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GymView extends VerticalLayout {
	private String[] mounthLabel = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	public VerticalLayout main = new VerticalLayout();
	public ShowTrainingView addPanel ;
	public GymView(ExerciseNamesServiceImpl exerciseNamesService) {
		this.addPanel = new ShowTrainingView(exerciseNamesService);
		addChooseDate(addAddTraining());
	}
	private Button addAddTraining() {
		Button result = new Button("Add training");
		result.addClickListener(e->{
			main.removeAll();
			main.add(addPanel);}
		);
		return result;
	}
	private void addChooseDate(Button addTraining) {
		ComboBox mounth = new ComboBox();
		mounth.setItems(mounthLabel);
		ComboBox year = new ComboBox();
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		String[] yearsLabel = new String[yearNow-2022+1];
		for (int i = 0; i <= yearNow-2022; i++) {
			yearsLabel[i] = String.valueOf((i+2022)+"");
		}
		year.setItems(yearsLabel);
		ComboBox trainings = new ComboBox();
		HorizontalLayout chooseDate = new HorizontalLayout(addTraining, mounth, year, trainings);
		add(chooseDate);
	}
}
