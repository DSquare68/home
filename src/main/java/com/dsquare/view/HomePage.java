package com.dsquare.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.page.Home;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.shared.ThemeVariant;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;

import jakarta.annotation.PostConstruct;

@Route(layout = Home.class)
@PreserveOnRefresh
public class HomePage extends VerticalLayout {
	
	private Calendar cal = Calendar.getInstance();
	private static final long serialVersionUID = -8852819231764919403L;
	private ProgressBar timeBar;
	private ProgressBar stateBar;
	H4 timeLabel,stateLabel;
	
	public HomePage() {
		this.setId("main-vl");
		this.setSizeFull();
		timeBar = new ProgressBar();
		timeBar.setId("time-bar");
		timeBar.setClassName("bar");
		stateBar = new ProgressBar();
		stateBar.setId("state-bar");
		stateBar.setClassName("bar");
		setStateAndTimeLabel();
		HorizontalLayout timeAndState = new HorizontalLayout();
		timeAndState.setId("time-hl");
		VerticalLayout time = new VerticalLayout();
		time.setClassName("barAndLabel-vl");
		VerticalLayout state = new VerticalLayout();
		state.setClassName("barAndLabel-vl");
		time.add(timeBar,timeLabel);
		state.add(stateBar,stateLabel);
		HorizontalLayout topMainPage = new HorizontalLayout();
		topMainPage.setId("top-main-page");
		topMainPage.add(time,state);
		add(topMainPage);
	}
	private void setStateAndTimeLabel() {
		timeLabel = new H4();
		stateLabel = new H4();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int m = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String date = sdf.format(cal.getTime());
		timeLabel.setClassName("bar-label");
		timeLabel.setText(h+":" + m + ":" + s);
		stateLabel.setClassName("bar-label");
		stateLabel.setText(date);
		
	}

}
