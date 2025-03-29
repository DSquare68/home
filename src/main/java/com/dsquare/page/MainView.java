package com.dsquare.page;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route
@AnonymousAllowed
@PreserveOnRefresh
@PageTitle("Home")
public class MainView extends VerticalLayout{

	private static final long serialVersionUID = -3350266054280237668L;

	MainView() {
		this.setId("home");
		Div label = new Div("D^2 HOME");
		Button b = new Button("Enter");
		b.setId("enter-button");
		b.addClickListener(e->UI.getCurrent().navigate("home"));
		label.setId("welcome-div");
		add(label);
		add(b);
	}
}