package com.dsquare.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dsquare.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {	
	
	@Query(value = "SELECT MAX('ID') FROM ADMIN.TRAININGS", nativeQuery = true)
    Long findMaxId();

	
}
