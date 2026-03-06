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

	@Query(value = "SELECT * FROM ADMIN.TRAININGS where SCHEMA=?1 and IS_SCHEMA <> 1  order by DATE_TRAINING desc ,ID  asc", nativeQuery = true)
	ArrayList<TrainingRecord> getTrainingsPerSchema(int id);

	@Query(value = "SELECT * FROM ADMIN.TRAININGS where ID_EXERCISE_NAME=?1 and IS_SCHEMA <> 1  order by DATE_TRAINING desc ,ID  asc", nativeQuery = true)
	ArrayList<TrainingRecord> getTrainingsWithExercise(int id);

	@Query(value = "SELECT DISTINCT EXTRACT(YEAR FROM DATE_TRAINING) FROM ADMIN.TRAININGS where IS_SCHEMA <> 1 order by EXTRACT(YEAR FROM DATE_TRAINING) desc", nativeQuery = true)
	String[] getYearsWithTrainings();

	@Query(value = "SELECT DISTINCT EXTRACT(MONTH FROM DATE_TRAINING) FROM ADMIN.TRAININGS where where IS_SCHEMA <> 1 order by EXTRACT(MONTH FROM DATE_TRAINING) desc", nativeQuery = true)
	String[] getMountsWithTrainings();

	@Query(value = "SELECT * FROM ADMIN.TRAININGS where EXTRACT(MONTH FROM DATE_TRAINING)=?1 and IS_SCHEMA <> 1 order by DATE_TRAINING desc ,ID  asc", nativeQuery = true)
	ArrayList<TrainingRecord> getTrainingsByMount(int mount);

	@Query(value = "SELECT * FROM ADMIN.TRAININGS where EXTRACT(YEAR FROM DATE_TRAINING)=?1 and IS_SCHEMA <> 1 order by DATE_TRAINING desc ,ID  asc", nativeQuery = true)
	ArrayList<TrainingRecord> getTrainingsByYear(int year);

	@Query(value = "SELECT * FROM ADMIN.TRAININGS where EXTRACT(YEAR FROM DATE_TRAINING)=?1 and EXTRACT(MONTH FROM DATE_TRAINING)=?2 and IS_SCHEMA <> 1 order by DATE_TRAINING desc ,ID  asc", nativeQuery = true)
	ArrayList<TrainingRecord> getTrainingsByYearAndMount(int year, int mount);
	

}
