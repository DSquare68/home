package com.dsquare.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthData {

	int numberOfTrainings;
	int totalDuration, month, year, totalReps;
	double totalWeight;
}
