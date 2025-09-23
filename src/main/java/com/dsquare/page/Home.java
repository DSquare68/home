package com.dsquare.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.dsquare.api.FootballApi;
import com.dsquare.api.InitData;
import com.dsquare.repository.ExerciseNamesRepository;
import com.dsquare.repository.ExerciseRepository;
import com.dsquare.service.ExerciseNamesServiceImpl;
import com.dsquare.service.TrainingServiceImpl;
import com.dsquare.view.CircleProgress;
import com.dsquare.view.HomePage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.UIScope;

@Route("home")
@PageTitle("Home")
@UIScope
@AnonymousAllowed
@Controller
public class Home extends AppLayout  {
	
	private static final long serialVersionUID = 2216332923141238067L;
	
	Home(ExerciseNamesServiceImpl namesService, TrainingServiceImpl trainingService){
		new InitData(namesService,trainingService).run();
		DrawerToggle toggle = new DrawerToggle();
		SideNav nav = getSideNav();
		Scroller scroller = new Scroller(nav);
		Button title = new Button("HOME");
		title.addClickListener((b)->UI.getCurrent().navigate("home"));
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
		addToNavbar(toggle, title);
		addToDrawer(scroller);
		setContent(new HomePage());
	}

	private SideNav getSideNav() {
		SideNav sideNav = new SideNav();
		Image gym = new Image("images/gym.png", "Gym");
		Image football = new Image("images/ball.png", "Football");
        sideNav.addItem(
                new SideNavItem(null,"/gym", gym)
                , new SideNavItem(null,"/football",football));
        return sideNav;
	}
}
