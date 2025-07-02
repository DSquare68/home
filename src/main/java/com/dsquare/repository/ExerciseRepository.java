package com.dsquare.repository;

import org.springframework.stereotype.Repository;

import com.dsquare.db.Exercise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {	
	
	@Query(value = "SELECT MAX('ID') FROM ADMIN.Exercise", nativeQuery = true)
    Long findMaxId();

}
