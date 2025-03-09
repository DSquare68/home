package com.dsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsquare.model.Exercise;

@Repository
public interface UserRepository extends JpaRepository<Exercise, Integer> {	

}
