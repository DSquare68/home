package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsquare.model.ExerciseNames;
import com.dsquare.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {	

}
