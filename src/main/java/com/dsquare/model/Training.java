package com.dsquare.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.dsquare.db.TrainingRecord;
import com.dsquare.service.TrainingServiceImpl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
public class Training {
	 private int ID;
	    private String name;
	    private Date date;
	    private LocalTime timeOfTrainig;
	    private ArrayList<String> exercises;
	    private HashMap<String,ArrayList<Round>> rounds;
	    private boolean templete;

	    public Training(int _ID, ArrayList<String> exercises, HashMap<String, ArrayList<Round>> rounds) {
	        this.ID = _ID;
	        this.exercises = exercises;
	        this.rounds = rounds;
	    }
	    public Training(String name){
	        this.name = name;
	        templete = false;
	        exercises = new ArrayList<>();
	        rounds = new HashMap<>();
	    }

	    
	    public class Round {
	        int roundNumber,reps;
	        double weight;

	        public Round(int roundNumber, int reps, double weight) {
	            this.roundNumber = roundNumber;
	            this.reps = reps;
	            this.weight = weight;
	        }

	        public int getRoundNumber() {
	            return roundNumber;
	        }

	        public void setRoundNumber(int roundNumber) {
	            this.roundNumber = roundNumber;
	        }

	        public int getReps() {
	            return reps;
	        }

	        public void setReps(int reps) {
	            this.reps = reps;
	        }

	        public double getWeight() {
	            return weight;
	        }

	        public void setWeight(double weight) {
	            this.weight = weight;
	        }
	    }
}
