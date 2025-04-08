package com.dsquare.page;

import java.util.Calendar;

import org.springframework.stereotype.Controller;

import com.dsquare.view.TrainingOverview;
import com.dsquare.view.TrainingsPast;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.view.GymView;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.combobox.ComboBox;

@Route(layout = Home.class, value = "gym")
@Controller
public class Gym extends HorizontalLayout {
	
	private static final long serialVersionUID = 3275850456945504655L;

	public Gym(ExerciseNamesServiceImpl exerciseNamesService) {
		add(new GymView(exerciseNamesService));
	}
	
}
