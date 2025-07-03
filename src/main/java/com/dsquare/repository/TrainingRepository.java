package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsquare.db.ExerciseNames;
import com.dsquare.db.TrainingRecord;

public interface TrainingRepository extends JpaRepository<TrainingRecord, Integer> {	

}
