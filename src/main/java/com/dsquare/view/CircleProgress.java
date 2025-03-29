package com.dsquare.view;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.server.VaadinSession;

import lombok.Getter;

public class CircleProgress  extends ProgressBar {
	private static final long serialVersionUID = 3275850456945504655L;
	@Getter
	private Thread thread;
	public CircleProgress(){
	}
	
	public void setTaskBar(UI ui) {
		//thread = new Thread(()-> {
		//});
	}

}
