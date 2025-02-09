package com.dsquare.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dsquare.model.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {	
	
	
}
