package com.dsquare.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import org.springframework.data.geo.Circle;

import com.vaadin.flow.component.progressbar.ProgressBar;

public class CircleProgress  extends ProgressBar {

	private static final long serialVersionUID = 3275850456945504655L;
	private final long DAY_TIME = 86400;
	public Thread timeThread;
	public CircleProgress(){

	}
	public void setTimeBar() {
		
	}
	public void stopTimeThread() {
		timeThread.interrupt();
	}
	public void setTaskBar() {
		
	}
}
