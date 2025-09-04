package com.dsquare.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dsquare.db.ExerciseNames;
import com.dsquare.db.TrainingRecord;
import com.dsquare.model.Training;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class GymTitle extends HorizontalLayout{

	private Div titlePage;
	private ComboBox schemas;
	private ComboBox trainingsPerSchemaComboBox;
	private Div trainingName;
	private ArrayList<Training> trainingsPerSchema= new ArrayList<>();
	private HashMap<String,Training> schemasMap = new HashMap<>();
	private HashMap<String,Training> trainingMap = new HashMap<>();
	public GymTitle(ArrayList<Training> trainingSchemas) {
		this.setId("title-gym-hl");
		
		titlePage = new Div("Trening's Overview");
		titlePage.setClassName("title-gym-div");
		trainingName = new Div("Choose training");
		trainingName.setClassName("title-gym-div");
		schemas = new ComboBox();
		schemas.setClassName("title-gym-combobox");
		
		trainingsPerSchemaComboBox = new ComboBox();
		trainingsPerSchemaComboBox.setClassName("title-gym-combobox");
		ArrayList<String> schemasData = new ArrayList<>();
		trainingSchemas.forEach(e->{
			String schemaString = e.getDate()+" "+e.getName();
			schemasData.add(schemaString);
			schemasMap.put(schemaString, e);
		});
		schemas.setItems(schemasData);
		VerticalLayout vl = new VerticalLayout(schemas,trainingsPerSchemaComboBox);
		vl.setId("title-gym-combobox-vl");
		add(titlePage,vl,trainingName);
	}
	@SuppressWarnings("unchecked")
	public void setTrainingReadPerSchema(TrainingServiceImpl trainingService, ExerciseNamesServiceImpl namesService) {
		schemas.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChanged(ValueChangeEvent event) {
				Training t = schemasMap.get(event.getValue().toString());
				new Thread(() -> {trainingsPerSchema = trainingService.getTrainingsByIDSchema(t.getID());
					ArrayList<String> schemasData = new ArrayList<>();
						trainingsPerSchema.forEach(e->{
						String schemaString = e.getDate()+" "+e.getName();
						schemasData.add(schemaString);
						trainingMap.put(schemaString, e);
					});
						schemas.setItems(schemasData);
				}).start();
			}
		});
	}
}
