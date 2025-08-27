package com.dsquare.view;

import java.util.ArrayList;
import java.util.List;

import com.dsquare.model.Training;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class GymTitle extends HorizontalLayout{

	private Div titlePage;
	private ComboBox schemas;
	private ComboBox trainingsPerSchema;
	private Div trainingName;
	public GymTitle(ArrayList<Training> trainingSchemas) {
		titlePage = new Div("Trening's Overview");
		titlePage.setClassName("title-gym-div");
		trainingName = new Div("Choose training");
		trainingName.setClassName("title-gym-div");
		schemas = new ComboBox();
		schemas.setClassName("title-gym-combobox");
		trainingsPerSchema = new ComboBox();
		trainingsPerSchema.setClassName("title-gym-combobox");
		ArrayList<String> schemasData = new ArrayList<>();
		trainingSchemas.forEach(e->schemasData.add(e.getDate()+" "+e.getName()));
		schemas.setItems(schemasData);
		VerticalLayout vl = new VerticalLayout(schemas,trainingsPerSchema);
		vl.setId("title-gym-combobox-vl");
		add(titlePage,vl,trainingName);
	}
}
