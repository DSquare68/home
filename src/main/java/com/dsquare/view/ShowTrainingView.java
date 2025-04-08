package com.dsquare.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.dsquare.db.ExerciseNames;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ShowTrainingView extends VerticalLayout {
	
	
	public ShowTrainingView(ExerciseNamesServiceImpl exerciseNamesService) {
		
	}

}
