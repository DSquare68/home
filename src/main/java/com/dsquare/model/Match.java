package com.dsquare.model;

import java.util.Date;

import lombok.Data;

@Data
public class Match {

	private int ID;
	private String homeTeam;
	private String awayTeam;
	private Date date;
	private int homeScore;
	private int awayScore;
	private String league;
	
}
