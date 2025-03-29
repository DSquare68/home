package com.dsquare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Round {
	@Id
	int id;
	int exercise_id;
	int roundNumber;
	int amount;
	double weight;
}
