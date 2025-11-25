package com.dsquare.page;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(layout = Home.class, value = "football")
public class Football extends Div{

	public Football() {
		this.setId("football");
		Div ekstraklasa = new Div("EKSTRAKLASA");
		ekstraklasa.setId("league-title");
		add(ekstraklasa);	
		
	}
}
