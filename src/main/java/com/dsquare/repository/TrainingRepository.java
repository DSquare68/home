package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dsquare.db.ExerciseNames;
import com.dsquare.db.TrainingRecord;

public interface TrainingRepository extends JpaRepository<TrainingRecord, Integer> {

	@Query(value = "SELECT MAX(ID_TRAINING) FROM ADMIN.TRAININGS", nativeQuery = true)
	int getMaxIDTrainingRecord();

	@Query(value = "SELECT MAX(SCHEMA) FROM ADMIN.TRAININGS", nativeQuery = true)
	int getMaxIDSchema();	

}
