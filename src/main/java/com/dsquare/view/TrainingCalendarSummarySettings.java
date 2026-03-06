package com.dsquare.view;

import java.util.ArrayList;

import com.dsquare.db.TrainingRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.Getter;

public class TrainingCalendarSummarySettings extends VerticalLayout {
	@Getter
	private int year;
	@Getter
	private int mount;
	public TrainingCalendarSummarySettings(String [] years, String [] mounts) {
		this.setId("training-calendar-summary-settigns");
		Button show = new Button("Pokaż");
		ComboBox comboBoxYear = new ComboBox();
		ComboBox comboBoxMount = new ComboBox();
		comboBoxYear.setItems(years);
		comboBoxMount.setItems(mounts);
	}
}
