package com.dsquare.home.page;

import com.dsquare.view.CircleProgress;
import com.dsquare.view.HomePage;
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
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("home")
@PageTitle("Home")
//@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class Home extends AppLayout  {
	
	private static final long serialVersionUID = 2216332923141238067L;

	Home() {
		DrawerToggle toggle = new DrawerToggle();
		SideNav nav = getSideNav();
		Scroller scroller = new Scroller(nav);
		H1 title = new H1("HOME");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
		addToNavbar(toggle, title);
		addToDrawer(scroller);
		setContent(new HomePage());
		//this.setContent(new Span("MEOW"));
		//this.showRouterLayoutContent(new HomePage());
	}

	private SideNav getSideNav() {
		SideNav sideNav = new SideNav();
		StreamResource imageResource = new StreamResource("gym.png",
		        () -> getClass().getResourceAsStream("/images/gym.png"));
		Image gym = new Image(imageResource, "Gym");
        sideNav.addItem(
                new SideNavItem(null,"/gym", gym));
        sideNav.setWidth(8, Unit.PERCENTAGE);
        return sideNav;
	}
}
