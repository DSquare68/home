package com.dsquare.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import lombok.Data;

@Data
public class SeasonAndQueueView extends HorizontalLayout{

	private Div seasonDiv,queueDiv;
	
	public SeasonAndQueueView(String season, String queue) {
		this.setId("season-and-queue-hl");
		seasonDiv = new Div(season);
		queueDiv = new Div(queue);
		queueDiv.setId("queue-div");
		seasonDiv.setId("season-div");
		seasonDiv.setClassName("season-and-queue-div");
		queueDiv.setClassName("season-and-queue-div");
		this.add(seasonDiv,queueDiv);
	}
}
