package com.dsquare.view;

import java.util.ArrayList;

import com.dsquare.db.TrainingRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.Getter;

public class TrainingCalendarSummarySettings extends HorizontalLayout {
	@Getter
	private int year;
	@Getter
	private int mount;
	public TrainingCalendarSummarySettings(String [] years, String [] mounths) {
		this.setId("training-calendar-summary-settigns");
		Button show = new Button("Pokaż");
		ComboBox comboBoxYear = new ComboBox();
		ComboBox comboBoxMounth= new ComboBox();
		Div trainings = new Div("Treningi");
		comboBoxYear.setItems(years);
		comboBoxMounth.setItems(mounths);
		comboBoxYear.setClassName("combo-box-TCS-settings");
		comboBoxMounth.setClassName("combo-box-TCS-settings");
		trainings.setId("trainings-label-TCS-settings");
		show.setId("training-button-TCS-settings");
		add(trainings,comboBoxMounth, comboBoxYear, show);
	}
}
