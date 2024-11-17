package com.dsquare.view;

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

import com.dsquare.home.page.Home;
import com.dsquare.threads.CirclePThread;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.shared.ThemeVariant;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;

import jakarta.annotation.PostConstruct;

@Route(layout = Home.class)
@Theme("dark")
@Push
public class HomePage extends VerticalLayout {
	
	private Calendar cal = Calendar.getInstance();
	private static final long serialVersionUID = -8852819231764919403L;
	private CircleProgress timeBar;
	private CirclePThread thread;
	
	public HomePage() {
		this.setId("main-vl");
		this.setSizeFull();
		timeBar = new CircleProgress();
		timeBar.setId("time-bar");
		timeBar.setClassName("bar-circle");
		CircleProgress stateBar = new CircleProgress();
		stateBar.setId("state-bar");
		stateBar.setClassName("bar-circle");
		HorizontalLayout time = new HorizontalLayout();
		time.setId("time-hl");
		time.add(timeBar,stateBar);
		add(time);
	}
	@Override
    protected void onAttach(AttachEvent attachEvent) {
		thread = new CirclePThread(attachEvent.getUI(),timeBar);
        thread.start();
    }
	@Override
	public void onDetach(DetachEvent de) {
		thread.interrupt();
	}
}
