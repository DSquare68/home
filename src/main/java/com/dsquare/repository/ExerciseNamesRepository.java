package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsquare.db.ExerciseNames;


public interface ExerciseNamesRepository extends JpaRepository<ExerciseNames, Integer> {	
	
	@Query(value = "SELECT MAX(ID) FROM ADMIN.exercise_names", nativeQuery = true)
    Long findMaxId();

	@Query(value="INSERT INTO ADMIN.EXERCISE_NAMES (NAME) VALUES (:#{#name.name})", nativeQuery = true)
	void insert(@Param("name") ExerciseNames name);
	
	Long findIDByName(String name);

	ExerciseNames getExerciseIdByName(String exercise);
}
