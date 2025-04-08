package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dsquare.db.ExerciseNames;

@Repository
public interface ExerciseNamesRepository extends JpaRepository<ExerciseNames, Integer> {	
	
	@Query(value = "SELECT MAX(ID) FROM ADMIN.exercise_names", nativeQuery = true)
    Long findMaxId();

	@Query(value="INSERT INTO ADMIN.EXERCISE_NAMES (NAME) VALUES (:#{#name.name})", nativeQuery = true)
	void insert(@Param("name") ExerciseNames name);
}
