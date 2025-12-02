package com.dsquare.view;

import java.text.SimpleDateFormat;
import com.dsquare.db.MatchRecord;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MatchView extends VerticalLayout {

	public MatchView(MatchRecord matches) {
		this.setClassName("match-view-vl");
		Div homeDiv = new Div(matches.getHome());
		homeDiv.setClassName("match-div");
		Div guestDiv = new Div(matches.getGuest());
		guestDiv.setClassName("match-div");
		Div dateDiv = new Div(new SimpleDateFormat("yyyy-MM-dd").format(matches.getDate_of_match()));
		dateDiv.setClassName("date-div");
		Div vsDiv = new Div(" VS ");
		vsDiv.setClassName("vs-div");
		Div vs2Div = new Div(" : ");
		vs2Div.setClassName("vs-div");
		Div scoreHomeDiv = new Div(String.valueOf(matches.getHomeResult()));
		scoreHomeDiv.setClassName("score-div");
		Div scoreGuestDiv = new Div(String.valueOf(matches.getGuestResult()));
		scoreGuestDiv.setClassName("score-div");
		Div isWinDiv = new Div();
		if(matches.getMode_of_data().contains("Win"))
			isWinDiv.setText("WIN");
		else if(matches.getMode_of_data().contains("Loss"))
			if(matches.getMode_of_data().contains("HOME"))
				isWinDiv.setText("LOSE HOME");
			else if (matches.getMode_of_data().contains("GUEST"))
				isWinDiv.setText("LOSE GUEST");
			else
				isWinDiv.setText("LOSS TIE");
		isWinDiv.setClassName("iswin-div");
		HorizontalLayout clubsHL = new HorizontalLayout(homeDiv,vsDiv,guestDiv);
		clubsHL.setClassName("clubs-results-hl");
		HorizontalLayout resultsHL =new HorizontalLayout(scoreHomeDiv,vs2Div,scoreGuestDiv);
		resultsHL.setClassName("clubs-results-hl");		
		this.add(dateDiv,clubsHL,resultsHL,isWinDiv);
	}
}
