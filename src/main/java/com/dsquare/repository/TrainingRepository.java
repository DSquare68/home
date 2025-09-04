package com.dsquare.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dsquare.db.ExerciseNames;
import com.dsquare.db.TrainingRecord;

public interface TrainingRepository extends JpaRepository<TrainingRecord, Integer> {

	@Query(value = "SELECT MAX(ID_TRAINING) FROM ADMIN.TRAININGS", nativeQuery = true)
	int getMaxIDTrainingRecord();

	@Query(value = "SELECT MAX(SCHEMA) FROM ADMIN.TRAININGS", nativeQuery = true)
	int getMaxIDSchema();

	@Query(value = "SELECT * FROM ADMIN.TRAININGS where IS_SCHEMA=1 order by ID asc", nativeQuery = true)
	ArrayList<TrainingRecord> getAllSchemas();

	@Query(value = "SELECT * FROM ADMIN.TRAININGS where ID_TRAINING=:id order by ID asc", nativeQuery = true)
	ArrayList<TrainingRecord> getTrainingsPerSchema(int id);	

}
