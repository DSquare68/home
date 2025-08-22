package com.dsquare.view;

import java.util.ArrayList;
import java.util.List;

import com.dsquare.model.Training;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class GymTitle extends HorizontalLayout{

	private Text titlePage;
	private ComboBox schemas;
	private ComboBox trainingsPerSchema;
	private Label trainingName;
	public GymTitle(ArrayList<Training> trainingSchemas) {
		titlePage = new Text("Trening's Owerview"); 
		schemas = new ComboBox();
		trainingsPerSchema = new ComboBox();
		ArrayList<String> schemasData = new ArrayList<>();
		trainingSchemas.forEach(e->schemasData.add(e.getDate()+" "+e.getName()));
		schemas.setItems(schemasData);
	}
}
